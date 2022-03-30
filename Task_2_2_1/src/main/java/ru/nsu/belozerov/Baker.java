package ru.nsu.belozerov;

import java.util.Random;

public class Baker implements Consumer, Producer {
    public final DataQueue orderQueue;
    private final String orderProduceStatus;

    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    private int deliveryCounter;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    public Baker(DataQueue orderQueue, DataQueue deliveryQueue) {
        orderConsumeStatus = "Cooking";
        orderProduceStatus = "On the Way";
        this.orderQueue = orderQueue;
        this.deliveryQueue = deliveryQueue;
        runFlag = true;
    }

    @Override
    public void run() {
        while (getFlag()) {
            consumer();
            producer();
        }
    }

    @Override
    public void consumer() {
        if (orderQueue.isEmpty()) {
            try {
                orderQueue.waitOnEmpty();
            } catch (InterruptedException ignored) {
            }
        }
        if (!runFlag) {
            return;
        }
        Order order = orderQueue.remove();
        deliveryCounter = order.getOrderNumber();
        orderQueue.notifyAllForFull();
        try {
            Thread.sleep(random.nextInt(processingTime));
        } catch (InterruptedException ignored) {
        }
    }

    public Order generateDelivery() {
        Order order = new Order();
        order.setOrderStatus(orderConsumeStatus);
        order.setOrderNumber(deliveryCounter);
        System.out.println("Order[" + deliveryCounter + "] is " + orderConsumeStatus);
        return order;
    }

    public boolean getFlag() {
        return runFlag;
    }

    @Override
    public void stopConsume() {
        runFlag = false;
        orderQueue.notifyAllForEmpty();
    }

    @Override
    public void producer() {
        while (deliveryQueue.isFull()) {
            try {
                deliveryQueue.waitOnFull();
            } catch (InterruptedException e) {
                break;
            }
        }
        if (!getFlag()) {
            return;
        }
        Order delivery = generateDelivery();
        changeOrderStatus(delivery, orderProduceStatus);
        deliveryQueue.add(delivery);
        deliveryQueue.notifyAllForEmpty();
        try {
            Thread.sleep(random.nextInt(processingTime));
        } catch (InterruptedException ignored) {
        }
    }

    public void changeProcessingTime(int time) {
        processingTime = time;
    }

    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
        System.out.println("Order[" + order.getOrderNumber() + "] is " + status);
    }

    @Override
    public void stopProduce() {
        runFlag = false;
        deliveryQueue.notifyAllForFull();
        orderQueue.notifyAllForEmpty();
    }
}
