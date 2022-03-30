package ru.nsu.belozerov.json;

public class JsonBaker {
    private int cookingTime;

    public JsonBaker(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
