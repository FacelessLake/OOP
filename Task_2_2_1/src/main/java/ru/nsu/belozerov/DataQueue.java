package ru.nsu.belozerov;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue that allow to transfer pizza from beginning to it's end
 */
public class DataQueue {
    private final Queue<Order> queue = new LinkedList<>();
    private final int maxSize;
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();

    /**
     * Could be used without upper limit
     */
    DataQueue() {
        this.maxSize = Integer.MAX_VALUE;
    }

    /**
     * Could have maximum size
     *
     * @param maxSize - the maximum allowed number of elements in the queue
     */
    DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Waits until queue will be available to interact
     *
     * @throws InterruptedException - can be thrown if the thread is interrupted while waiting
     */
    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.wait();
        }
    }

    /**
     * Notifies all the treads that queue is full
     */
    public void notifyAllForFull() {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.notifyAll();
        }
    }

    /**
     * Waits until queue will be available to interact
     *
     * @throws InterruptedException - can be thrown if the thread is interrupted while waiting
     */
    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.wait();
        }
    }

    /**
     * Notifies all the treads that queue is empty
     */
    public void notifyAllForEmpty() {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.notify();
        }
    }

    /**
     * Allows additions of the new orders to the queue
     *
     * @param order - order you want to add
     */
    public void add(Order order) {
        synchronized (queue) {
            queue.add(order);
        }
    }

    /**
     * Allow getting order from queue, removing it from there
     *
     * @return order
     */
    public Order remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    /**
     * Checking whether the queue is full
     *
     * @return true - if full, false - otherwise
     */
    public boolean isFull() {
        synchronized (queue) {
            return queue.size() == maxSize;
        }
    }

    /**
     * Checking whether the queue is empty
     *
     * @return true - if empty, false - otherwise
     */
    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}