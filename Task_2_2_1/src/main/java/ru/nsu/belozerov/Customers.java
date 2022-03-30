package ru.nsu.belozerov;

import java.util.Random;

public class Customers implements Producer {
    public final DataQueue orderQueue;
    private int orderCounter;
    private final String orderProduceStatus;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    public Customers(DataQueue orderQueue) {
        orderProduceStatus = "Processing";
        this.orderQueue = orderQueue;
        runFlag = true;
    }

    @Override
    public void run() {
        while (getFlag()) {
            producer();
        }
    }

    public boolean getFlag() {
        return runFlag;
    }

    @Override
    public void producer() {
        while (orderQueue.isFull()) {
            try {
                orderQueue.waitOnFull();
            } catch (InterruptedException e) {
                break;
            }
        }
        if (!getFlag()) {
            return;
        }
        Order order = generateOrder();
        orderQueue.add(order);
        orderQueue.notifyAllForEmpty();
        try {
            Thread.sleep(random.nextInt(processingTime));
        } catch (InterruptedException ignored) {
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
        orderQueue.notifyAllForFull();
    }
}

