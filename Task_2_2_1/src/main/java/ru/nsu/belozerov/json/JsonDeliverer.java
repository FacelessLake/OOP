package ru.nsu.belozerov.json;

public class JsonDeliverer {
    private int deliveryTime;
    private int trunkSize;

    public JsonDeliverer(int deliveryTime, int trunkSize) {
        this.deliveryTime = deliveryTime;
        this.trunkSize = trunkSize;
    }

    public void setSpeed(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getSpeed() {
        return deliveryTime;
    }

    public void setTrunkSize(int trunkSize) {
        this.trunkSize = trunkSize;
    }

    public int getTrunkSize() {
        return trunkSize;
    }
}
