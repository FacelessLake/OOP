package ru.nsu.belozerov;

public class Baker extends ProducerConsumer {

    public Baker(DataQueue consumerQueue, DataQueue producerQueue) {
        super(consumerQueue, producerQueue, "Cooking");
    }
}
