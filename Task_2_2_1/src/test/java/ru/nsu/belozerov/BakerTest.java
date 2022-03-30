package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BakerTest {

    @Test
    void bakerTest() throws InterruptedException {
        DataQueue ordersQueue = new DataQueue(3);
        DataQueue deliveryQueue = new DataQueue(3);
        Order order = new Order();
        order.setOrderStatus("mem");
        order.setOrderNumber(15);
        while (!ordersQueue.isFull()) {
            ordersQueue.add(order);
        }
        Baker baker = new Baker(ordersQueue, deliveryQueue);
        baker.changeProcessingTime(1000);
        Thread bakerThread = new Thread(baker);
        bakerThread.start();
        while (!deliveryQueue.isFull()) {
        }
        Thread.sleep(3000);
        baker.stopConsume();
        baker.stopProduce();
        while (!deliveryQueue.isEmpty()) {
            order = deliveryQueue.remove();
            assertEquals("On the Way", order.getOrderStatus());
            assertEquals(15, order.getOrderNumber());
        }
    }
}