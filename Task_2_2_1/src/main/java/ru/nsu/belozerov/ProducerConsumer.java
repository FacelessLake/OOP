package ru.nsu.belozerov;

import java.util.Random;

public class ProducerConsumer implements Runnable {
    public final DataQueue producerQueue;
    private final DataQueue consumerQueue;
    private volatile boolean runProducerFlag;
    private volatile boolean runConsumerFlag;
    private int orderCounter;
    private final String orderProduceStatus;
    private final String orderConsumeStatus;
    public int processingTime = 0;
    public final Random random = new Random();

    public ProducerConsumer(DataQueue consumerQueue, DataQueue producerQueue, String orderConsumeStatus, String orderProduceStatus) {
        this.producerQueue = producerQueue;
        this.consumerQueue = consumerQueue;
        this.orderProduceStatus = orderProduceStatus;
        this.orderConsumeStatus = orderConsumeStatus;
        runConsumerFlag = true;
        runProducerFlag = true;
        orderCounter = 0;
    }

    public ProducerConsumer(DataQueue dataQueue, String orderStatus) {
        this.producerQueue = dataQueue;
        this.consumerQueue = dataQueue;
        this.orderProduceStatus = orderStatus;
        this.orderConsumeStatus = orderStatus;
        runConsumerFlag = true;
        runProducerFlag = true;
        orderCounter = 0;
    }

    @Override
    public void run() {
        produce();
    }

    @SuppressWarnings("BusyWait")
    public void produce() {
        while (runProducerFlag) {
            Order order = generateOrder();
            while (producerQueue.isFull()) {
                try {
                    producerQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runProducerFlag) {
                break;
            }
            producerQueue.add(order);
            producerQueue.notifyAllForEmpty();
            try {
                Thread.sleep(random.nextInt(processingTime));
            } catch (InterruptedException e) {
                if (!runProducerFlag) {
                    break;
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public void consume() {
        while (runConsumerFlag) {
            if (consumerQueue.isEmpty()) {
                try {
                    consumerQueue.waitOnEmpty();
                } catch (InterruptedException ignored) {
                }
            }
            if (!runConsumerFlag) {
                break;
            }
            Order order = consumerQueue.remove();
            changeOrderStatus(order);
            consumerQueue.notifyAllForFull();
            try {
                Thread.sleep(random.nextInt(processingTime));
            } catch (InterruptedException e) {
                if (!runConsumerFlag) {
                    break;
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public void consumeProduce() {
        while (runConsumerFlag) {
            if (consumerQueue.isEmpty()) {
                try {
                    consumerQueue.waitOnEmpty();
                } catch (InterruptedException ignored) {
                }
            }
            if (!runConsumerFlag) {
                break;
            }
            Order order = consumerQueue.remove();
            changeOrderStatus(order);
            consumerQueue.notifyAllForFull();
            try {
                Thread.sleep(random.nextInt(processingTime));
            } catch (InterruptedException e) {
                if (!runConsumerFlag) {
                    break;
                }
            }
            Order delivery = generateDelivery(order.getOrderNumber());
            while (producerQueue.isFull()) {
                try {
                    producerQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runProducerFlag) {
                break;
            }
            producerQueue.add(delivery);
            producerQueue.notifyAllForEmpty();
            try {
                Thread.sleep(random.nextInt(processingTime));
            } catch (InterruptedException e) {
                if (!runProducerFlag) {
                    break;
                }
            }
        }
    }

    public Order generateOrder() {
        Order order = new Order();
        orderCounter++;
        order.setOrderStatus(orderProduceStatus);
        order.setOrderNumber(orderCounter);
        System.out.println("Order[" + orderCounter + "] is " + orderProduceStatus);
        return order;
    }

    public Order generateDelivery(int deliveryCounter) {
        Order order = new Order();
        order.setOrderStatus(orderProduceStatus);
        order.setOrderNumber(deliveryCounter);
        System.out.println("Order[" + deliveryCounter + "] is " + orderProduceStatus);
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
        runProducerFlag = false;
        producerQueue.notifyAllForFull();
    }

    public void stopConsume() {
        runConsumerFlag = false;
        consumerQueue.notifyAllForEmpty();
    }
}