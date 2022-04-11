package ru.nsu.belozerov;

public class Tile {
    private TileType type;
    private int row;
    private int line;

    public Tile(int row, int line) {
        type = TileType.EMPTY;
        this.line = line;
        this.row = row;
    }

    public Tile(int row, int line, TileType type) {
        this.type = type;
        this.line = line;
        this.row = row;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCoordinates(int row, int line) {
        this.line = line;
        this.row = row;
    }
}
