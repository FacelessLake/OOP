package ru.nsu.belozerov;

public interface Producer extends Runnable {

    void run();

    void producer();

    void stopProduce();

}