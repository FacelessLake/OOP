package ru.nsu.belozerov.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.belozerov.*;

public class SnakeFX {
    private final Image[] headRight = new Image[3];
    private final Image[] headDown = new Image[3];
    private final Image[] headUp = new Image[3];
    private final Image[] headLeft = new Image[3];
    private final int tileSize;
    private AnimatedImage headImage;
    private final Snake snake;

    public SnakeFX(int tileSize, Snake snake) {
        this.tileSize = tileSize;
        this.snake = snake;
    }

    public AnimatedImage makeMainSnake() {

        for (int i = 0; i < 3; i++) {
            headRight[i] = new Image("snake_right" + (i + 1) + ".png");
            headDown[i] = new Image("snake_down" + (i + 1) + ".png");
            headUp[i] = new Image("snake_up" + (i + 1) + ".png");
            headLeft[i] = new Image("snake_left" + (i + 1) + ".png");
        }
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
    }
}
