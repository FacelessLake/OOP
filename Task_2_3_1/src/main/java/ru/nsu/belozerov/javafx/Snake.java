package ru.nsu.belozerov.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.belozerov.Directions;
import ru.nsu.belozerov.Field;
import ru.nsu.belozerov.Tile;
import ru.nsu.belozerov.TileType;

import java.util.ArrayList;

public class Snake {
    private Directions direction;
    private int length;
    private final int tileSize;
    private final int column;
    private final int row;
    private Tile head;
    private final ArrayList<Tile> body = new ArrayList<>();
    private AnimatedImage headImage;
    private final Image[] headRight = new Image[3];
    private final Image[] headDown = new Image[3];
    private final Image[] headUp = new Image[3];
    private final Image[] headLeft = new Image[3];


    public Snake(int column, int row, Directions direction, int tileSize) {
        this.column = column;
        this.row = row;
        this.tileSize = tileSize;
        this.direction = direction;
        length = 3;
    }

    public AnimatedImage makeMainSnake() {

        for (int i = 0; i < 3; i++) {
            headRight[i] = new Image("snake_right" + (i + 1) + ".png");
            headDown[i] = new Image("snake_down" + (i + 1) + ".png");
            headUp[i] = new Image("snake_up" + (i + 1) + ".png");
            headLeft[i] = new Image("snake_left" + (i + 1) + ".png");
        }

        double duration = 0.00009;
        int columnBody = column;
        int columnTale = column;
        int rowBody = row;
        int rowTale = row;
        switch (direction) {
            case UP -> {
                headImage = new AnimatedImage(headUp, duration);
                rowBody = row + 1;
                rowTale = row + 2;
            }
            case DOWN -> {
                headImage = new AnimatedImage(headDown, duration);
                rowBody = row - 1;
                rowTale = row - 2;
            }
            case LEFT -> {
                headImage = new AnimatedImage(headLeft, duration);
                columnBody = column + 1;
                columnTale = column + 2;
            }
            case RIGHT -> {
                headImage = new AnimatedImage(headRight, duration);
                columnBody = column - 1;
                columnTale = column - 2;
            }
        }
        head = new Tile(column, row, tileSize, TileType.SNAKE_HEAD);
        Tile tileBody = new Tile(columnBody, rowBody, tileSize, TileType.SNAKE_BODY);
        tileBody.setDirection(direction);
        body.add(tileBody);
        Tile tileTale = new Tile(columnTale, rowTale, tileSize, TileType.SNAKE_TAIL);
        tileTale.setDirection(direction);
        body.add(tileTale);
        return headImage;
    }

    public Field drawMainSnake(GraphicsContext gc, Field field) {
        for (Tile tile : body) {
            Image bodyImage = new Image("body_hor.png");
            if (tile.getType() == TileType.SNAKE_BODY) {
                switch (tile.getDirection()) {
                    case UP -> {
                        bodyImage = new Image("body_vert.png");
                        if (tile.getRotation() == Directions.RIGHT) {
                            bodyImage = new Image("rotate-1.png");
                        }
                        if (tile.getRotation() == Directions.LEFT) {
                            bodyImage = new Image("rotate-4.png");
                        }
                    }
                    case DOWN -> {
                        bodyImage = new Image("body_vert.png");
                        if (tile.getRotation() == Directions.RIGHT) {
                            bodyImage = new Image("rotate-2.png");
                        }
                        if (tile.getRotation() == Directions.LEFT) {
                            bodyImage = new Image("rotate-3.png");
                        }
                    }
                    case RIGHT -> {
                        if (tile.getRotation() == Directions.UP) {
                            bodyImage = new Image("rotate-3.png");
                        }
                        if (tile.getRotation() == Directions.DOWN) {
                            bodyImage = new Image("rotate-4.png");
                        }
                    }
                    case LEFT -> {
                        if (tile.getRotation() == Directions.UP) {
                            bodyImage = new Image("rotate-2.png");
                        }
                        if (tile.getRotation() == Directions.DOWN) {
                            bodyImage = new Image("rotate-1.png");
                        }
                    }
                }
            } else {
                switch (tile.getDirection()) {
                    case UP -> bodyImage = new Image("tail_up.png");
                    case DOWN -> bodyImage = new Image("tail_down.png");
                    case RIGHT -> bodyImage = new Image("tail_right.png");
                    case LEFT -> bodyImage = new Image("tail_left.png");
                }
            }
            gc.drawImage(bodyImage, tile.getColumn() * tileSize,
                    tile.getRow() * tileSize);
            field.setTile(tile);
        }
        return field;
    }

    public Field move(Directions changeDirection, Field field) {
        Tile bodyTile = field.getTile(head.getColumn(), head.getRow());
        bodyTile.setType(TileType.SNAKE_BODY);
        bodyTile.setRotation(direction);
        bodyTile.setDirection(changeDirection);
        body.add(bodyTile);
        int headRow = head.getRow();
        int headColumn = head.getColumn();
        Image[] frames = new Image[3];
        switch (changeDirection) {
            case UP -> {
                direction = Directions.UP;
                headRow = head.getRow() - 1;
                frames = headUp;
            }
            case RIGHT -> {
                direction = Directions.RIGHT;
                headColumn = head.getColumn() + 1;
                frames = headRight;
            }
            case DOWN -> {
                direction = Directions.DOWN;
                headRow = head.getRow() + 1;
                frames = headDown;
            }
            case LEFT -> {
                direction = Directions.LEFT;
                headColumn = head.getColumn() - 1;
                frames = headLeft;
            }
        }
        head.setRow(headRow);
        head.setColumn(headColumn);
        headImage.setFrames(frames);
        if (body.size() > length) {
            Tile tile = body.remove(0);
            tile.setType(TileType.EMPTY);
            tile.setRotation(null);
            body.get(0).setType(TileType.SNAKE_TAIL);
        }
        return field;
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

    public ArrayList<Tile> getBody() {
        return body;
    }

    public int getLength() {
        return length;
    }

    public void grow() {
        length+=3;
    }
}
