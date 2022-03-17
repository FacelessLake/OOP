package ru.nsu.belozerov;

import java.util.Random;

public class ProducerConsumer implements Runnable {
    private final DataQueue producerQueue;
    private final DataQueue consumerQueue;
    private volatile boolean runFlag;
    private int orderCounter;
    private final String orderProduceStatus;
    private final String orderConsumeStatus;
    private int processingTime = 0;
    private final Random random = new Random();

    public ProducerConsumer(DataQueue consumerQueue, DataQueue producerQueue, String orderProduceStatus, String orderConsumeStatus) {
        this.producerQueue = producerQueue;
        this.consumerQueue = consumerQueue;
        this.orderProduceStatus = orderProduceStatus;
        this.orderConsumeStatus = orderConsumeStatus;
        runFlag = true;
        orderCounter = 0;
    }

    public ProducerConsumer(DataQueue dataQueue, String orderStatus) {
        this.producerQueue = dataQueue;
        this.consumerQueue = dataQueue;
        this.orderProduceStatus = orderStatus;
        this.orderConsumeStatus = orderStatus;
        runFlag = true;
        orderCounter = 0;
    }

    @Override
    public void run() {
        consume();
        produce();
    }

    @SuppressWarnings("BusyWait")
    public void produce() {
        while (runFlag) {
            Order order = generateOrder();
            while (producerQueue.isFull()) {
                try {
                    producerQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            producerQueue.add(order);
            producerQueue.notifyAllForEmpty();
            try {
                Thread.sleep(this.random.nextInt(this.processingTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public void consume() {
        while (runFlag) {
            Order order;
            if (consumerQueue.isEmpty()) {
                try {
                    consumerQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            order = consumerQueue.remove();
            consumerQueue.notifyAllForFull();
            changeOrderStatus(order);
            try {
                Thread.sleep(this.random.nextInt(this.processingTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Order generateOrder() {
        Order order = new Order();
        orderCounter++;
        order.setOrderStatus(orderProduceStatus);
        order.setOrderNumber(orderCounter);
        System.out.println("Order[" + orderCounter + "] is " + orderProduceStatus);
        return order;
    }

    public void changeProcessingTime(int time) {
        processingTime = time;
    }

    public void changeOrderStatus(Order order) {
        order.setOrderStatus(orderConsumeStatus);
        System.out.println("Order[" + order.getOrderNumber() + "] is " + orderConsumeStatus);
    }

    public void stopProduce() {
        runFlag = false;
        producerQueue.notifyAllForFull();
    }

    public void stopConsume() {
        runFlag = false;
        consumerQueue.notifyAllForEmpty();
    }
}