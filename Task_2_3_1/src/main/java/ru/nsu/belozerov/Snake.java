package ru.nsu.belozerov;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Directions direction;
    private int length;
    private final int column;
    private final int row;
    private Tile head;
    private final Field field;
    private final List<Tile> body = new ArrayList<>();


    public Snake(int column, int row, Directions direction, Field field) {
        this.column = column;
        this.row = row;
        this.direction = direction;
        this.field = field;
        length = 2;
    }


    public void move(Directions changeDirection) {
        Tile bodyTile = field.getTile(head.getColumn(), head.getRow());
        bodyTile.setType(TileType.SNAKE_BODY);
        bodyTile.setRotation(direction);
        bodyTile.setDirection(changeDirection);
        body.add(bodyTile);
        int headRow = head.getRow();
        int headColumn = head.getColumn();
        switch (changeDirection) {
            case UP -> {
                direction = Directions.UP;
                headRow = head.getRow() - 1;
            }
            case RIGHT -> {
                direction = Directions.RIGHT;
                headColumn = head.getColumn() + 1;
            }
            case DOWN -> {
                direction = Directions.DOWN;
                headRow = head.getRow() + 1;
            }
            case LEFT -> {
                direction = Directions.LEFT;
                headColumn = head.getColumn() - 1;
            }
        }
        head.setRow(headRow);
        head.setColumn(headColumn);
        if (body.size() > length) {
            Tile tile = body.remove(0);
            tile.setType(TileType.EMPTY);
            tile.setRotation(null);
            body.get(0).setType(TileType.SNAKE_TAIL);
        }
    }

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

    public void setHead(Tile head) {
        this.head = head;
    }

    public Tile getHead() {
        return head;
    }

    public List<Tile> getBody() {
        return body;
    }

    public int getLength() {
        return length;
    }

    public void grow() {
        length += 3;
    }
}
