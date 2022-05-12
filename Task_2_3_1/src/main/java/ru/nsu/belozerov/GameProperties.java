package ru.nsu.belozerov;

public class GameProperties {
    private int winCondition;
    private int foodCnt;
    private int squareSize;
    private int windowWidthPixels;
    private int windowHeightPixels;

    public GameProperties(){
        winCondition = 15;
        foodCnt = 3;
        squareSize = 50;
        windowWidthPixels = 1000;
        windowHeightPixels = 1000;
    }

    public int getFoodCnt() {
        return foodCnt;
    }

    public void setFoodCnt(int foodCnt) {
        this.foodCnt = foodCnt;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(int winCondition) {
        this.winCondition = winCondition;
    }

    public int getWindowHeightPixels() {
        return windowHeightPixels;
    }

    public void setWindowHeightPixels(int windowHeightPixels) {
        this.windowHeightPixels = windowHeightPixels;
    }

    public int getWindowWidthPixels() {
        return windowWidthPixels;
    }

    public void setWindowWidthPixels(int windowWidthPixels) {
        this.windowWidthPixels = windowWidthPixels;
    }
}
