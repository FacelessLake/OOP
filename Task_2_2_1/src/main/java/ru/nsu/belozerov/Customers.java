package ru.nsu.belozerov;

public class Customers extends ProducerConsumer {
    public Customers(DataQueue orders) {
        super(orders, "Processing");
    }

    @Override
    public void run() {
        produceRun();
    }
}

