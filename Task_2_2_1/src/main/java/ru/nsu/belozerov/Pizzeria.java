package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private final List<Baker> bakers;
    private final int bakersAmount;
    private final int bakersSpeed;
    private final int storageSize;
    private final List<DeliveryGuy> deliverers;
    private final int deliverersAmount;
    private final int deliverersSpeed;
    private final int trunkSize;
    private final Customers customers;
    private final int ordersDelay;
    private final DataQueue ordersQueue;
    private final DataQueue deliveryQueue;
    private final int SLEEP_TIME = 1000;


    public Pizzeria(int bakersAmount, int bakersSpeed, int deliverersAmount, int deliverersSpeed, int storageSize, int trunkSize, int ordersDelay) {
        this.bakersAmount = bakersAmount;
        this.bakersSpeed = bakersSpeed;
        this.deliverersAmount = deliverersAmount;
        this.deliverersSpeed = deliverersSpeed;
        this.storageSize = storageSize;
        this.trunkSize = trunkSize;
        this.ordersDelay = ordersDelay;

        deliveryQueue = new DataQueue(storageSize);
        deliverers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            DeliveryGuy deliverer = new DeliveryGuy(deliveryQueue);
            deliverer.changeProcessingTime(deliverersSpeed);
            deliverers.add(deliverer);
        }

        ordersQueue = new DataQueue();
        bakers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            Baker baker = new Baker(ordersQueue, deliveryQueue);
            baker.changeProcessingTime(bakersSpeed);
            bakers.add(baker);
        }
        customers = new Customers(ordersQueue);
        customers.changeProcessingTime(ordersDelay);
    }

    public void pizzeriaStart() {
        Thread customersThread = new Thread(this.customers);
        customersThread.start();
        this.bakers.stream().map(Thread::new).forEach(Thread::start);
        this.deliverers.stream().map(Thread::new).forEach(Thread::start);
    }

    public void pizzeriaStop() throws InterruptedException {
        this.customers.stopProduce();
        Thread.sleep(this.SLEEP_TIME);
        this.bakers.forEach(Baker::stopConsume);
        this.bakers.forEach(Baker::stopProduce);
        Thread.sleep(this.SLEEP_TIME);
        this.deliverers.forEach(DeliveryGuy::stopProduce);
    }
}
