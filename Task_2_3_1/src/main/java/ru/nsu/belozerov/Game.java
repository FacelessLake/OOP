package ru.nsu.belozerov;

public class Game {
    public Game(int width, int height, int startRow, int startColumn, Directions startDirection) {
        //Field field = new Field(width, height);
        Snake snake = new Snake(startRow, startColumn, startDirection);
    }


}
