package ru.nsu.belozerov;

public class Baker extends ProducerConsumer {

    public Baker(DataQueue orderQueue, DataQueue deliveryQueue) {
        super(orderQueue, deliveryQueue, "Cooking", "On the Way");
    }

    @Override
    public void run() {
        consumeProduceRun();
    }
}
