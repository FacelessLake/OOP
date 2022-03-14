package ru.nsu.belozerov;

public class Consumer implements Runnable {

    private final DataQueue dataQueue;
    private volatile boolean runFlag;
    private final String orderStatus;

    public Consumer(DataQueue dataQueue, int queueSize, String orderStatus) {
        this.dataQueue = dataQueue;
        this.orderStatus = orderStatus;
        runFlag = true;
    }

    @Override
    public void run() {
        consume();
    }

    private void consume() {
        while (runFlag) {
            Order order;
            if (dataQueue.isEmpty()) {
                try {
                    dataQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            order = dataQueue.remove();
            dataQueue.notifyAllForFull();
            changeOrderStatus(order);
        }
    }

    private void changeOrderStatus(Order order) {
        order.setOrderStatus(orderStatus);
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForEmpty();
    }
}
