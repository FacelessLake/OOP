package ru.nsu.belozerov;

public class ProducerConsumer implements Runnable {
    private final DataQueue producerQueue;
    private final DataQueue consumerQueue;
    private volatile boolean runFlag;
    private int orderCounter;
    private final String orderStatus;

    public ProducerConsumer(DataQueue consumerQueue, DataQueue producerQueue, String orderStatus) {
        this.producerQueue = producerQueue;
        this.consumerQueue = consumerQueue;
        this.orderStatus = orderStatus;
        runFlag = true;
        orderCounter = 0;
    }

    public ProducerConsumer(DataQueue dataQueue, String orderStatus) {
        this.producerQueue = dataQueue;
        this.consumerQueue = dataQueue;
        this.orderStatus = orderStatus;
        runFlag = true;
        orderCounter = 0;
    }

    @Override
    public void run() {
        consume();
        produce();
    }

    private void produce() {
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
        }
    }

    private void consume() {
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
        }
    }

    private Order generateOrder() {
        Order order = new Order();
        orderCounter++;
        order.setOrderStatus(orderStatus);
        order.setOrderNumber(orderCounter);
        return order;
    }


    public void changeOrderStatus(Order order) {
        order.setOrderStatus(orderStatus);
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