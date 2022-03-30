package ru.nsu.belozerov;

import java.util.Random;

/**
 * Class that simulates bakers activities at the pizzeria
 */
public class Baker implements Consumer, Producer {
    public final DataQueue orderQueue;
    private final String orderProduceStatus;

    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    private int deliveryCounter;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    /**
     * Bakers act as consumer of user's orders as well as producer of pizzas
     *
     * @param orderQueue    - from this queue baker receive orders
     * @param deliveryQueue - this queue is used to transfer finished pizzas to deliverers
     */
    public Baker(DataQueue orderQueue, DataQueue deliveryQueue) {
        orderConsumeStatus = "Cooking";
        orderProduceStatus = "On the Way";
        this.orderQueue = orderQueue;
        this.deliveryQueue = deliveryQueue;
        runFlag = true;
    }

    /**
     * Starts consuming-producing process
     */
    @Override
    public void run() {
        while (getFlag()) {
            consumer();
            producer();
        }
    }

    /**
     * The consuming process itself
     */
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

    /**
     * This method creates new Order exemplar, marking it as in progress
     *
     * @return order with appropriate number and status
     */
    public Order generateDelivery() {
        Order order = new Order();
        order.setOrderStatus(orderConsumeStatus);
        order.setOrderNumber(deliveryCounter);
        System.out.println("Order[" + deliveryCounter + "] is " + orderConsumeStatus);
        return order;
    }

    /**
     * This method allows getting flag indicating whether the program is running or not.
     *
     * @return the flag
     */
    public boolean getFlag() {
        return runFlag;
    }

    /**
     * Stops consuming orders from customers
     */
    @Override
    public void stopConsume() {
        runFlag = false;
        orderQueue.notifyAllForEmpty();
    }

    /**
     * The producing process itself
     */
    @Override
    public void producer() {
        while (deliveryQueue.isFull()) {
            if (!getFlag()) {
                return;
            }
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

    /**
     * This method can be used in order to change the maximum amount of time, that baker can spend on pizza cooking
     *
     * @param time - how long pizza cooking takes
     */
    public void changeProcessingTime(int time) {
        processingTime = time;
    }

    /**
     * This method can be used to change the status of an order
     *
     * @param order  - the order you want to change
     * @param status - the new status you want to set
     */
    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
        System.out.println("Order[" + order.getOrderNumber() + "] is " + status);
    }

    /**
     * Stops producing new pizzas
     */
    @Override
    public void stopProduce() {
        runFlag = false;
        deliveryQueue.notifyAllForFull();
        orderQueue.notifyAllForEmpty();
    }
}
