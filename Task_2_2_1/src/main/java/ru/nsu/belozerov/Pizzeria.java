package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private final List<Baker> bakers;
    private final int bakersAmount;
    private final int storageSize;
    private final List<DeliveryGuy> deliverers;
    private final int deliverersAmount;
    private final int trunkSize;
    private final DataQueue ordersQueue;
    private final DataQueue deliveryQueue;


    public Pizzeria(int bakersAmount, int deliverersAmount, int storageSize, int trunkSize) {
        this.bakersAmount = bakersAmount;
        this.deliverersAmount = deliverersAmount;
        this.storageSize = storageSize;
        this.trunkSize = trunkSize;

        deliveryQueue = new DataQueue(storageSize);
        deliverers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            DeliveryGuy deliverer = new DeliveryGuy(deliveryQueue);
            deliverers.add(deliverer);
        }

        ordersQueue = new DataQueue();
        bakers = new ArrayList<>();
        for (int i = 0; i < deliverersAmount; i++) {
            Baker baker = new Baker(ordersQueue, deliveryQueue);
            bakers.add(baker);
        }
    }
}
