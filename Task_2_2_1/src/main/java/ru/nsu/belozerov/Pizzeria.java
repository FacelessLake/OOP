package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Pizzeria {
    private final List<Baker> bakers;
    private final int bakersAmount;
    private final int storageSize;
    private final List<DeliveryGuy> deliverers;
    private final int deliverersAmount;
    private final int trunkSize;
    private final DataQueue ordersQueue;


    public Pizzeria(int bakersAmount, int deliverersAmount, int storageSize, int trunkSize){
        this.bakersAmount = bakersAmount;
        this.deliverersAmount = deliverersAmount;
        this.storageSize = storageSize;
        this.trunkSize = trunkSize;
        bakers = new ArrayList<>();
        deliverers = new ArrayList<>();
        ordersQueue = new DataQueue(storageSize);
    }
}
