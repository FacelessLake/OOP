package ru.nsu.belozerov;

/**
 * Interface for making subjects that will produce something - it could be pizza or an order
 */
public interface Producer extends Runnable {
    /**
     * Starts producing process
     */
    void run();

    /**
     * The process itself
     */
    void producer();

    /**
     * Method to stop producing
     */
    void stopProduce();

}