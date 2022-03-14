package ru.nsu.belozerov;

public class DeliveryGuy extends ProducerConsumer {
    public DeliveryGuy(DataQueue deliveryQueue) {
        super(deliveryQueue, "On the way");
    }
}
