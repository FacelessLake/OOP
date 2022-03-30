package ru.nsu.belozerov.json;

public class JsonPizzeria {
    private final int bakersAmount;
    private final int deliverersAmount;
    private final int storageSize;
    private final int ordersDelay;
    private final JsonBaker[] bakers;
    private final JsonDeliverer[] deliverers;

    public JsonPizzeria(int bakersAmount, int deliverersAmount, int storageSize, int ordersDelay, JsonBaker[] bakers, JsonDeliverer[] deliverers) {
        this.bakersAmount = bakersAmount;
        this.deliverersAmount = deliverersAmount;
        this.storageSize = storageSize;
        this.ordersDelay = ordersDelay;
        this.bakers = bakers;
        this.deliverers = deliverers;
    }

    public int[] getBakersSpeeds() {
        int[] bakersSpeeds = new int[bakersAmount];
        for (int i = 0; i < bakersAmount; i++) {
            bakersSpeeds[i] = bakers[i].getCookingTime();
        }
        return bakersSpeeds;
    }

    public int[] getDeliverersSpeeds() {
        int[] deliverersSpeeds = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            deliverersSpeeds[i] = deliverers[i].getSpeed();
        }
        return deliverersSpeeds;
    }

    public int[] getTrunkSizes() {
        int[] trunkSizes = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            trunkSizes[i] = deliverers[i].getTrunkSize();
        }
        return trunkSizes;
    }

    public int getBakersAmount() {
        return bakersAmount;
    }

    public int getDeliverersAmount() {
        return deliverersAmount;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public int getOrdersDelay() {
        return ordersDelay;
    }
}
