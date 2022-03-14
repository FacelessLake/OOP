package ru.nsu.belozerov;

public class Customer extends ProducerConsumer {
    public Customer(DataQueue orders) {
        super(orders, "Processing");
    }
}
