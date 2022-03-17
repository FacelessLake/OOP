package ru.nsu.belozerov;

public class DeliveryGuy extends ProducerConsumer {
    public DeliveryGuy(DataQueue deliveryQueue) {
        super(deliveryQueue, "Delivered");
    }

    @Override
    public void run() {
        consume();
    }
}
