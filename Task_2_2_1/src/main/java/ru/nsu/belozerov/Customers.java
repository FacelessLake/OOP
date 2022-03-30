package ru.nsu.belozerov;

import java.util.Random;

/**
 * Generator of orders for pizzeria
 */
public class Customers implements Producer {
    public final DataQueue orderQueue;
    private int orderCounter;
    private final String orderProduceStatus;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    /**
     * Customers act as producers of orders here
     *
     * @param orderQueue - the queue of orders received from customers
     */
    public Customers(DataQueue orderQueue) {
        orderProduceStatus = "Processing";
        this.orderQueue = orderQueue;
        runFlag = true;
    }

    /**
     * Launch the order-generating process
     */
    @Override
    public void run() {
        while (getFlag()) {
            producer();
        }
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
     * The producer process itself
     */
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

    /**
     * This method creates new Order exemplar, marking it as Processing one
     *
     * @return order with appropriate number and status
     */
    public Order generateOrder() {
        Order order = new Order();
        orderCounter++;
        order.setOrderStatus(orderProduceStatus);
        order.setOrderNumber(orderCounter);
        System.out.println("Order[" + orderCounter + "] is " + orderProduceStatus);
        return order;
    }

    /**
     * Allows changing the delay between making new orders
     *
     * @param time - delay
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
        orderQueue.notifyAllForFull();
    }
}

