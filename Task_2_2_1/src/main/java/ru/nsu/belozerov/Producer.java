package ru.nsu.belozerov;

public class Producer implements Runnable {
    private final DataQueue dataQueue;
    private volatile boolean runFlag;
    private int orderCounter;
    private final String orderStatus;

    public Producer(DataQueue dataQueue, String orderStatus) {
        this.dataQueue = dataQueue;
        this.orderStatus = orderStatus;
        runFlag = true;
        orderCounter = 0;
    }

    @Override
    public void run() {
        produce();
    }

    private void produce() {
        while (runFlag) {
            Order order = generateOrder();
            while (dataQueue.isFull()) {
                try {
                    dataQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            dataQueue.add(order);
            dataQueue.notifyAllForEmpty();
        }
    }

    private Order generateOrder() {
        Order order = new Order();
        orderCounter++;
        order.setOrderStatus(orderStatus);
        order.setOrderNumber(orderCounter);
        return order;
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForFull();
    }
}