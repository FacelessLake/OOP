package ru.nsu.belozerov;


public interface Consumer extends Runnable {

    void run();

    void consumer();

    void stopConsume();
}
