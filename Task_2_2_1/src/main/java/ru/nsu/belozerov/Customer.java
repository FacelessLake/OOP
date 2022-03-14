package ru.nsu.belozerov;

public class Customer extends Producer{
    public Customer(DataQueue orders) {
        super(orders, "Processing");
    }
}
