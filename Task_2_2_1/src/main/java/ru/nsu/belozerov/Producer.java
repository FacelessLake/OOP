package ru.nsu.belozerov;

public interface Producer<T> extends Runnable {

    void run();

    void producer(T order);

    void stopProduce();

}