package ru.nsu.belozerov;

public class DeliveryGuy extends Consumer {
    public DeliveryGuy(DataQueue deliveryQueue, int trunkSize) {
        super(deliveryQueue, trunkSize, "On the way");
        deliveryQueue = new DataQueue(trunkSize);
    }
}
