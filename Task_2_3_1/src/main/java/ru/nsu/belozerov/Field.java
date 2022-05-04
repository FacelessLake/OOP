package ru.nsu.belozerov;

public class Field {
    private int width;
    private int height;
    private int tileSize;
    private Tile[][] field;

    public Field(int width, int height, int tileSize) {
        resetField (width, height, tileSize);
    }

    public void resetField(int width, int height, int tileSize) {
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        field = new Tile[width/tileSize][height/tileSize];
        for (int i = 0; i < width/tileSize; i++) {
            for (int j = 0; j < height/tileSize; j++) {
                field[i][j] = new Tile(i, j, tileSize, TileType.EMPTY);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Tile getTile(int x, int y){
        return field[x][y];
    }
}
