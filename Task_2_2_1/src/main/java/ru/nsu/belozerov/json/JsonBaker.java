package ru.nsu.belozerov.json;

/**
 * Structure for taking baker's data from json
 */
public class JsonBaker {
    private int cookingTime;

    /**
     * Cooking time is the only parameter of baker
     *
     * @param cookingTime - how long does it take for this baker to make a pizza
     */
    public JsonBaker(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Sets cooking time
     *
     * @param cookingTime - how long does it take for this baker to make a pizza
     */
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Returns cooking time
     *
     * @return how long does it take for this baker to make a pizza
     */
    public int getCookingTime() {
        return cookingTime;
    }
}
