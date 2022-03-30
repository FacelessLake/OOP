package ru.nsu.belozerov;

/**
 * Interface for making subjects that will consume elements
 */
public interface Consumer extends Runnable {
    /**
     * Starts the consuming process
     */
    void run();

    /**
     * The process itself
     */
    void consumer();

    /**
     * Method to stop consuming
     */
    void stopConsume();
}
