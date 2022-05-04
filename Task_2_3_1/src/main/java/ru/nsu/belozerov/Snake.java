package ru.nsu.belozerov;

public class Snake {
    private Directions direction;
    private int row;
    private int column;
    private int length;

    public Snake(int row, int column, Directions direction) {
        this.direction = direction;
        this.row = row;
        this.column = column;
        length = 3;
    }

//    public void move() {
//        switch (direction) {
//            case UP -> line--;
//            case DOWN -> line++;
//            case LEFT -> row--;
//            case RIGHT -> row++;
//        }
//    }
//
//    public void rotate(Directions direction) {
//        this.direction = direction;
//    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public void grow() {
        length++;
    }
}
