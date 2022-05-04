package ru.nsu.belozerov;

public class Tile {
    private TileType type;
    private int row;
    private int column;
    private int size;
    private Directions direction;
    private Directions rotation;

    public Tile(int column, int row,  int size) {
        type = TileType.EMPTY;
        this.column = column;
        this.row = row;
        this.size = size;
    }

    public Tile(int column, int row,  int size, TileType type) {
        this.type = type;
        this.column = column;
        this.row = row;
        this.size = size;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setRotation(Directions rotation) {
        this.rotation = rotation;
    }

    public Directions getRotation() {
        return rotation;
    }

    public void setCoordinates(int column, int row) {
        this.column = column;
        this.row = row;
    }
}
