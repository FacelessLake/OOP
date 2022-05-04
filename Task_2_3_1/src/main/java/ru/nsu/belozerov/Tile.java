package ru.nsu.belozerov;

public class Tile {
    private TileType type;
    private int row;
    private int column;
    private int size;

    public Tile(int row, int column, int size) {
        type = TileType.EMPTY;
        this.column = column;
        this.row = row;
        this.size = size;
    }

    public Tile(int row, int column, int size, TileType type) {
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

    public void setCoordinates(int row, int line) {
        this.column = line;
        this.row = row;
    }
}
