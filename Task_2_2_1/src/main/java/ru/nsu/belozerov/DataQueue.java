package ru.nsu.belozerov;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private final Queue<Order> queue = new LinkedList<>();
    private final int maxSize;
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();

    DataQueue() {
        this.maxSize = Integer.MAX_VALUE;
    }

    DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.wait();
        }
    }

    public void notifyAllForFull() {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.notifyAll();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.wait();
        }
    }

    public void notifyAllForEmpty() {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.notify();
        }
    }

    public void add(Order order) {
        synchronized (queue) {
            queue.add(order);
        }
    }

    public Order remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    public boolean isFull() {
        synchronized (queue) {
            return queue.size() == maxSize;
        }
    }

    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}