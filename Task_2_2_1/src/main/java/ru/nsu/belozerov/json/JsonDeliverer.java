package ru.nsu.belozerov.json;

/**
 * Structure for taking deliverer's data from json
 */
public class JsonDeliverer {
    private int deliveryTime;
    private int trunkSize;

    /**
     * Deliverer has two necessary parameters
     *
     * @param deliveryTime - how long does it take for this deliverer to deliver a pizza
     * @param trunkSize    - size of the trunk of the deliverer's car
     */
    public JsonDeliverer(int deliveryTime, int trunkSize) {
        this.deliveryTime = deliveryTime;
        this.trunkSize = trunkSize;
    }

    /**
     * Sets delivery time
     *
     * @param deliveryTime - how long does it take for this deliverer to deliver a pizza
     */
    public void setSpeed(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * Gets delivery time
     *
     * @return how long does it take for this deliverer to deliver a pizza
     */
    public int getSpeed() {
        return deliveryTime;
    }

    /**
     * Sets trunk size
     *
     * @param trunkSize - size of the trunk of the deliverer's car
     */
    public void setTrunkSize(int trunkSize) {
        this.trunkSize = trunkSize;
    }

    /**
     * Gets trunk size
     *
     * @return size of the trunk of the deliverer's car
     */
    public int getTrunkSize() {
        return trunkSize;
    }
}
