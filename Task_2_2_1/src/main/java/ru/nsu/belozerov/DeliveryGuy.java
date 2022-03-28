package ru.nsu.belozerov;

import java.util.ArrayDeque;
import java.util.Random;

public class DeliveryGuy implements Consumer {
    private final DataQueue deliveryQueue;
    private final String orderConsumeStatus;
    private final ArrayDeque<Order> trunk = new ArrayDeque<>();
    private final int trunkSize;

    private volatile boolean runFlag;
    private final Random random = new Random();
    private int processingTime = 0;

    public DeliveryGuy(DataQueue deliveryQueue, int trunkSize) {
        orderConsumeStatus = "Delivered";
        this.deliveryQueue = deliveryQueue;
        this.trunkSize = trunkSize;
        runFlag = true;
    }

    @Override
    public void run() {
        while (getFlag()) {
            consumer();
        }
    }

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

    public boolean getFlag() {
        return runFlag;
    }

    @Override
    public void stopConsume() {
        runFlag = false;
        deliveryQueue.notifyAllForEmpty();
    }


    public void changeProcessingTime(int time) {
        processingTime = time;
    }

    public void changeOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
        System.out.println("Order[" + order.getOrderNumber() + "] is " + status);
    }
}
