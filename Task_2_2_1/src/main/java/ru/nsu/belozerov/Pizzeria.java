package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private final List<Baker> bakers;
    private final List<DeliveryGuy> deliverers;
    private final Customers customers;


    public Pizzeria(int bakersAmount, int[] bakersProductivity, int deliverersAmount, int[] deliverersProductivity, int storageSize, int[] trunkSizes, int ordersDelay) {

        DataQueue deliveryQueue = new DataQueue(storageSize);
        deliverers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            DeliveryGuy deliverer = new DeliveryGuy(deliveryQueue, trunkSizes[i]);
            deliverer.changeProcessingTime(deliverersProductivity[i]);
            deliverers.add(deliverer);
        }

        DataQueue ordersQueue = new DataQueue();
        bakers = new ArrayList<>();
        for (int i = 0; i < bakersAmount; i++) {
            Baker baker = new Baker(ordersQueue, deliveryQueue);
            baker.changeProcessingTime(bakersProductivity[i]);
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
        Thread.sleep(15 * 1000);
        bakers.forEach(Baker::stopConsume);
        bakers.forEach(Baker::stopProduce);
        Thread.sleep(30 * 1000);
        deliverers.forEach(DeliveryGuy::stopConsume);
    }
}
