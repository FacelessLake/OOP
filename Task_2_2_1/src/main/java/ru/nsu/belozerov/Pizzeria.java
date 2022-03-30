package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizzeria that takes produce pizzas
 */
public class Pizzeria {
    private final List<Baker> bakers;
    private final List<DeliveryGuy> deliverers;
    private final Customers customers;

    /**
     * Main workers of pizzeria is bakers, that produced pizzas and deliverers that deliver orders to customers
     *
     * @param bakersAmount           - amount of bakers at the pizzeria
     * @param bakersProductivity     - amount of time that baker spends to make one pizza
     * @param deliverersAmount       - amount of deliverers at the pizzeria
     * @param deliverersProductivity -  amount of time that deliverer spends to deliver one pizza
     * @param storageSize            - size of the storage from which deliverers take their orders
     * @param trunkSizes             - size of trunk of deliverer's car
     * @param ordersDelay            - maximum delay between two orders
     */
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

    /**
     * Launch threads of bakers and deliverers
     */
    public void pizzeriaStart() {
        Thread customersThread = new Thread(customers);
        customersThread.start();
        bakers.stream().map(Thread::new).forEach(Thread::start);
        deliverers.stream().map(Thread::new).forEach(Thread::start);
    }

    /**
     * Stops the pizzerias work
     *
     * @throws InterruptedException - can be thrown if the thread is interrupted while sleeping
     */
    public void pizzeriaStop() throws InterruptedException {
        customers.stopProduce();
        Thread.sleep(15 * 1000);
        bakers.forEach(Baker::stopConsume);
        bakers.forEach(Baker::stopProduce);
        Thread.sleep(30 * 1000);
        deliverers.forEach(DeliveryGuy::stopConsume);
    }
}
