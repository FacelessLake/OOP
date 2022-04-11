package ru.nsu.belozerov;

public class Field {
    private int size;
    private Tile[][] field;

    public Field(int size) {
        resetField(size);
    }

    public void resetField(int size) {
        this.size = size;
        field = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new Tile(i, j, TileType.EMPTY);
            }
        }
    }
}
