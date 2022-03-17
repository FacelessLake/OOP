package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private final List<Baker> bakers;
    private final List<DeliveryGuy> deliverers;
    private final Customers customers;


    public Pizzeria(int bakersAmount, int bakersSpeed, int deliverersAmount, int deliverersSpeed, int storageSize, int trunkSize, int ordersDelay) {

        DataQueue deliveryQueue = new DataQueue(storageSize);
        deliverers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            DeliveryGuy deliverer = new DeliveryGuy(deliveryQueue);
            deliverer.changeProcessingTime(deliverersSpeed);
            deliverers.add(deliverer);
        }

        DataQueue ordersQueue = new DataQueue();
        bakers = new ArrayList<>();
        for (int i = 0; i < bakersAmount; i++) {
            Baker baker = new Baker(ordersQueue, deliveryQueue);
            baker.changeProcessingTime(bakersSpeed);
            bakers.add(baker);
        }
        customers = new Customers(ordersQueue);
        customers.changeProcessingTime(ordersDelay);
    }

    public void pizzeriaStart() {
        Thread customersThread = new Thread(customers);
        customersThread.start();
        bakers.stream().map(Thread::new).forEach(Thread::start);
        deliverers.stream().map(Thread::new).forEach(Thread::start);
    }

    public void pizzeriaStop() throws InterruptedException {
        customers.stopProduce();
        int SLEEP_TIME = 1000;
        Thread.sleep(SLEEP_TIME);
        bakers.forEach(Baker::stopConsume);
        Thread.sleep(SLEEP_TIME);
        bakers.forEach(Baker::stopProduce);
        Thread.sleep(SLEEP_TIME);
        deliverers.forEach(DeliveryGuy::stopProduce);
    }
}
