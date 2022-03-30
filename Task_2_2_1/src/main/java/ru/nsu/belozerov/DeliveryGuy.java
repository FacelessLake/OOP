package ru.nsu.belozerov;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * Deliverer of the pizzas
 */
public class DeliveryGuy implements Consumer {
    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    private final ArrayDeque<Order> trunk = new ArrayDeque<>();
    private final int trunkSize;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    /**
     * Takes as many pizzas from storage, as his trunk could afford
     *
     * @param deliveryQueue - queue of cooked pizzas
     * @param trunkSize     - amount of pizzas that deliverer can take once
     */
    public DeliveryGuy(DataQueue deliveryQueue, int trunkSize) {
        orderConsumeStatus = "Delivered";
        this.deliveryQueue = deliveryQueue;
        this.trunkSize = trunkSize;
        runFlag = true;
    }

    /**
     * Starts consuming orders from the storage
     */
    @Override
    public void run() {
        while (getFlag()) {
            consumer();
        }
    }

    /**
     * The consuming process itself
     */
    @SuppressWarnings("BusyWait")
    @Override
    public void consumer() {
        if (deliveryQueue.isEmpty()) {
            try {
                deliveryQueue.waitOnEmpty();
            } catch (InterruptedException ignored) {
            }
        }
        if (!runFlag) {
            return;
        }
        for (int i = 0; i < trunkSize; i++) {
            if (!deliveryQueue.isEmpty()) {
                Order order = deliveryQueue.remove();
                trunk.add(order);
            }
        }
        while (!trunk.isEmpty()) {
            try {
                Thread.sleep(random.nextInt(processingTime));
            } catch (InterruptedException ignored) {
            }
            changeOrderStatus(trunk.remove(), orderConsumeStatus);
            deliveryQueue.notifyAllForFull();
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
     * Stops delivering the pizzas
     */
    @Override
    public void stopConsume() {
        runFlag = false;
        deliveryQueue.notifyAllForEmpty();
    }

    /**
     * This method can be used to change the maximum amount of time, that deliverer can spend on pizza delivery
     *
     * @param time - how long it takes to deliver the pizza
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
}
