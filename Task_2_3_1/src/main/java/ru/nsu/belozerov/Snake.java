package ru.nsu.belozerov;

public class Snake {
    private Directions direction;
    private int row;
    private int line;
    private int length;

    public Snake(int row, int line, Directions direction) {
        this.direction = direction;
        this.row = row;
        this.line = line;
        length = 1;
    }

    public void move() {
        switch (direction) {
            case UP -> line--;
            case DOWN -> line++;
            case LEFT -> row--;
            case RIGHT -> row++;
        }
    }

    public void rotate(Directions direction) {
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getLine() {
        return line;
    }

    public Directions getDirection() {
        return direction;
    }

    public void grow() {
        length++;
    }
}
