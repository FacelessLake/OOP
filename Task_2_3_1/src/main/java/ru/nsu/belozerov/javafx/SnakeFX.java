package ru.nsu.belozerov.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.belozerov.*;

public class SnakeFX {
    private final Image[] headRight = new Image[3];
    private final Image[] headDown = new Image[3];
    private final Image[] headUp = new Image[3];
    private final Image[] headLeft = new Image[3];
    private final Image[] rotate = new Image[4];
    private final Image[] tail = new Image[4];
    private final Image[] body = new Image[2];
    private final int tileSize;
    private AnimatedImage headImage;
    private final Snake snake;

    public SnakeFX(int tileSize, Snake snake) {
        this.tileSize = tileSize;
        this.snake = snake;
        for (int i = 0; i < 3; i++) {
            headRight[i] = new Image("snake_right" + (i + 1) + ".png", tileSize, tileSize, true, false);
            headDown[i] = new Image("snake_down" + (i + 1) + ".png", tileSize, tileSize, true, false);
            headUp[i] = new Image("snake_up" + (i + 1) + ".png", tileSize, tileSize, true, false);
            headLeft[i] = new Image("snake_left" + (i + 1) + ".png", tileSize, tileSize, true, false);
        }
        for (int i = 0; i < 4; i++) {
            rotate[i] = new Image("rotate-" + (i + 1) + ".png", tileSize, tileSize, true, false);
            tail[i] = new Image("tail-" + (i + 1) + ".png", tileSize, tileSize, true, false);
        }
        body[0] = new Image("body_hor.png", tileSize, tileSize, true, false);
        body[1] = new Image("body_vert.png", tileSize, tileSize, true, false);
    }

    public AnimatedImage makeMainSnake() {


        Directions direction = snake.getDirection();
        double duration = 0.00009;
        int columnBody = snake.getColumn();
        int columnTale = snake.getColumn();
        int rowBody = snake.getRow();
        int rowTale = snake.getRow();
        switch (direction) {
            case UP -> {
                headImage = new AnimatedImage(headUp, duration);
                rowBody++;
                rowTale += 2;
            }
            case DOWN -> {
                headImage = new AnimatedImage(headDown, duration);
                rowBody--;
                rowTale -= 2;
            }
            case LEFT -> {
                headImage = new AnimatedImage(headLeft, duration);
                columnBody++;
                columnTale += 2;
            }
            case RIGHT -> {
                headImage = new AnimatedImage(headRight, duration);
                columnBody--;
                columnTale -= 2;
            }
        }
        snake.setHead(new Tile(snake.getColumn(), snake.getRow(), TileType.SNAKE_HEAD)); //??
        Tile tileBody = new Tile(columnTale, rowBody, TileType.SNAKE_TAIL);
        tileBody.setDirection(direction);
        snake.getBody().add(tileBody);
        Tile tileTale = new Tile(columnBody, rowTale, TileType.SNAKE_BODY);
        tileTale.setDirection(direction);
        snake.getBody().add(tileTale);
        return headImage;
    }

    public void drawMainSnake(GraphicsContext gc, Field field) {
        Image[] frames = new Image[3];
        switch (snake.getDirection()) {
            case UP -> frames = headUp;
            case RIGHT -> frames = headRight;
            case DOWN -> frames = headDown;
            case LEFT -> frames = headLeft;
        }
        headImage.setFrames(frames);
        for (Tile tile : snake.getBody()) {
            Image bodyImage = body[0];
            if (tile.getType() == TileType.SNAKE_BODY) {
                switch (tile.getDirection()) {
                    case UP -> {
                        bodyImage = body[1];
                        if (tile.getRotation() == Directions.RIGHT) {
                            bodyImage = rotate[0];
                        }
                        if (tile.getRotation() == Directions.LEFT) {
                            bodyImage = rotate[3];
                        }
                    }
                    case DOWN -> {
                        bodyImage = body[1];
                        if (tile.getRotation() == Directions.RIGHT) {
                            bodyImage = rotate[1];
                        }
                        if (tile.getRotation() == Directions.LEFT) {
                            bodyImage = rotate[2];
                        }
                    }
                    case RIGHT -> {
                        if (tile.getRotation() == Directions.UP) {
                            bodyImage = rotate[2];
                        }
                        if (tile.getRotation() == Directions.DOWN) {
                            bodyImage = rotate[3];
                        }
                    }
                    case LEFT -> {
                        if (tile.getRotation() == Directions.UP) {
                            bodyImage = rotate[1];
                        }
                        if (tile.getRotation() == Directions.DOWN) {
                            bodyImage = rotate[0];
                        }
                    }
                }
            } else {
                switch (tile.getDirection()) {
                    case UP -> bodyImage = tail[0];
                    case DOWN -> bodyImage = tail[1];
                    case RIGHT -> bodyImage = tail[2];
                    case LEFT -> bodyImage = tail[3];
                }
            }
            gc.drawImage(bodyImage, tile.getColumn() * tileSize,
                    tile.getRow() * tileSize);
            field.setTile(tile);
        }
    }
}
