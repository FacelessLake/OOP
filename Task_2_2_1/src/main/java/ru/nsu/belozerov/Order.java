package ru.nsu.belozerov;

/**
 * Order that passing through stages of completion
 */
public class Order {
    private int orderNumber;
    private String orderStatus;

    /**
     * Returns order number
     *
     * @return number of an order
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets order number
     *
     * @param orderNumber - number you want to set to an order
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns order status
     *
     * @return status of an order
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets order status
     *
     * @param orderStatus - status you want to set to an order
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}