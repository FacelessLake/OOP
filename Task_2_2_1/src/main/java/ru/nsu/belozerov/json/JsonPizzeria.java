package ru.nsu.belozerov.json;

/**
 * Structure for taking data about pizzeria and it's workers from json file
 */
public class JsonPizzeria {
    private final int bakersAmount;
    private final int deliverersAmount;
    private final int storageSize;
    private final int ordersDelay;
    private final JsonBaker[] bakers;
    private final JsonDeliverer[] deliverers;

    /**
     * Main workers of pizzeria is bakers, that produced pizzas and deliverers that deliver orders to customers
     *
     * @param bakersAmount-     amount of bakers at the pizzeria
     * @param deliverersAmount- amount of deliverers at the pizzeria
     * @param storageSize       - size of the storage from which deliverers take their order
     * @param ordersDelay-      maximum delay between two orders
     * @param bakers            - array of the bakers and their stats
     * @param deliverers        - array of the deliverers and their stats
     */
    public JsonPizzeria(int bakersAmount, int deliverersAmount, int storageSize, int ordersDelay, JsonBaker[] bakers, JsonDeliverer[] deliverers) {
        this.bakersAmount = bakersAmount;
        this.deliverersAmount = deliverersAmount;
        this.storageSize = storageSize;
        this.ordersDelay = ordersDelay;
        this.bakers = bakers;
        this.deliverers = deliverers;
    }

    /**
     * Returns and array of baker's speeds
     *
     * @return array of baker's speeds
     */
    public int[] getBakersSpeeds() {
        int[] bakersSpeeds = new int[bakersAmount];
        for (int i = 0; i < bakersAmount; i++) {
            bakersSpeeds[i] = bakers[i].getCookingTime();
        }
        return bakersSpeeds;
    }

    /**
     * Returns and array of deliverer's speeds
     *
     * @return array of deliverer's speeds
     */
    public int[] getDeliverersSpeeds() {
        int[] deliverersSpeeds = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            deliverersSpeeds[i] = deliverers[i].getSpeed();
        }
        return deliverersSpeeds;
    }

    /**
     * Returns trunk size of each deliverer's car
     *
     * @return array of trunk sizes
     */
    public int[] getTrunkSizes() {
        int[] trunkSizes = new int[deliverersAmount];
        for (int i = 0; i < deliverersAmount; i++) {
            trunkSizes[i] = deliverers[i].getTrunkSize();
        }
        return trunkSizes;
    }

    /**
     * Returns amount of bakers
     *
     * @return bakers amount
     */
    public int getBakersAmount() {
        return bakersAmount;
    }

    /**
     * Returns amount of deliverers
     *
     * @return deliverers amount
     */
    public int getDeliverersAmount() {
        return deliverersAmount;
    }

    /**
     * Returns size of storage
     *
     * @return storage size
     */
    public int getStorageSize() {
        return storageSize;
    }

    /**
     * Returns delay between two orders from customers
     *
     * @return a delay
     */
    public int getOrdersDelay() {
        return ordersDelay;
    }
}
