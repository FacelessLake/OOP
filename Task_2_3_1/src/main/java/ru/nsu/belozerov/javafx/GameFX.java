package ru.nsu.belozerov.javafx;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import ru.nsu.belozerov.*;

public class GameFX {
    private final int SQUARE_SIZE;
    private final int WINDOW_WIDTH_PIXELS;
    private final int WINDOW_HEIGHT_PIXELS;
    private final int WINDOW_WIDTH_SQUARES;
    private final int WINDOW_HEIGHT_SQUARES;
    private final int winCondition;
    private final int foodCnt;
    private final Tile[] foodTile;
    private final Field field;
    private final Snake snake;
    private final SnakeFX snakeFX;
    private final Food food;
    private boolean foodFlag = false;
    private boolean winFlag = false;
    AnimationTimer timer;
    private String input = "";
    Integer score = 0;
    ObservableList<Node> list;

    public GameFX(GameProperties properties){
        SQUARE_SIZE = properties.getSquareSize();
        WINDOW_WIDTH_PIXELS = properties.getWindowWidthPixels();
        WINDOW_WIDTH_SQUARES = WINDOW_WIDTH_PIXELS / SQUARE_SIZE;
        WINDOW_HEIGHT_PIXELS = properties.getWindowHeightPixels();
        WINDOW_HEIGHT_SQUARES = WINDOW_WIDTH_PIXELS / SQUARE_SIZE;
        winCondition = properties.getWinCondition();
        foodCnt = properties.getFoodCnt();
        field = new Field(WINDOW_WIDTH_SQUARES, WINDOW_HEIGHT_SQUARES, SQUARE_SIZE);
        snake = new Snake(WINDOW_HEIGHT_SQUARES / 2, WINDOW_WIDTH_SQUARES / 2, Directions.RIGHT, field);
        snakeFX = new SnakeFX(SQUARE_SIZE, snake);
        food = new Food(field, SQUARE_SIZE);
        foodTile = new Tile[foodCnt];
    }

    public void startGame(Scene theScene, ObservableList<Node> list) {
        this.list = list;
        GraphicsContext gc = ((Canvas) list.get(0)).getGraphicsContext2D();
        AnimatedImage animatedHead = snakeFX.makeMainSnake();
        snakeFX.drawMainSnake(gc, field);
        gc.drawImage(animatedHead.getFrame(0), snake.getHead().getColumn() * SQUARE_SIZE,
                snake.getHead().getRow() * SQUARE_SIZE);

        for(int i=0; i<foodCnt; i++){
            while (true) {
                foodTile[i] = food.createFood();
                if (field.getTile(foodTile[i].getColumn(), foodTile[i].getRow()).getType() == TileType.EMPTY) {
                    field.setTile(foodTile[i]);
                    break;
                }
            }
        }

        timer = new AnimationTimer() {
            int speedDelay = 150;
            Long lastNanoTime = System.nanoTime();
            Directions changeDirection = null;

            public void handle(long currentNanoTime) {
                try {
                    Thread.sleep(speedDelay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                theScene.setOnKeyPressed(e -> input = e.getCode().toString());
                foodCheck();
                paintTheField(gc, Color.web("AAD751"), Color.web("A2D149"));
                for (int i = 0; i < foodCnt; i++) {
                    gc.drawImage(food.getFoodImage(), foodTile[i].getColumn() * SQUARE_SIZE, foodTile[i].getRow() * SQUARE_SIZE);
                }

                // game logic
                switch (input) {
                    case "LEFT" -> {
                        if (snake.getDirection() != Directions.RIGHT) {
                            changeDirection = Directions.LEFT;
                        }
                    }
                    case "RIGHT" -> {
                        if (snake.getDirection() != Directions.LEFT) {
                            changeDirection = Directions.RIGHT;
                        }
                    }
                    case "UP" -> {
                        if (snake.getDirection() != Directions.DOWN) {
                            changeDirection = Directions.UP;
                        }
                    }
                    case "DOWN" -> {
                        if (snake.getDirection() != Directions.UP) {
                            changeDirection = Directions.DOWN;
                        }
                    }
                }

                if (changeDirection != null) {
                    snake.move(changeDirection);
                }
                if (consumeFood()) {
                    speedDelay -= 100 / winCondition;
                }

                if (bumpInto()) {
                    gameOver(gc);
                }
                if (score == winCondition) {
                    winFlag = true;
                    gameOver(gc);
                }

                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 200000000.0;
                lastNanoTime = currentNanoTime;

                // render
                snakeFX.drawMainSnake(gc, field);
                gc.drawImage(animatedHead.getFrame(elapsedTime), snake.getHead().getColumn() * SQUARE_SIZE,
                        snake.getHead().getRow() * SQUARE_SIZE);
                printScore(gc);
            }
        };
        timer.start();
    }

    public void paintTheField(GraphicsContext gc, Color color1, Color color2) {
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(color1);
                } else {
                    gc.setFill(color2);
                }
                if (i == 0 || j == 0 || i == WINDOW_WIDTH_SQUARES - 1 || j == WINDOW_HEIGHT_SQUARES - 1) {
                    gc.setFill(Color.FIREBRICK);
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void foodCheck() {
        if (!foodFlag) {
            for (int i = 0; i < foodCnt; i++) {
                if (foodTile[i].getType() == TileType.EMPTY) {
                    while (true) {
                        foodTile[i] = food.createFood();
                        if (field.getTile(foodTile[i].getColumn(), foodTile[i].getRow()).getType() == TileType.EMPTY) {
                            field.setTile(foodTile[i]);
                            break;
                        }
                    }
                }
            }
            foodFlag = true;
        }
    }

    private boolean consumeFood() {
        for (int i = 0; i < foodCnt; i++) {
            if (foodTile[i].getColumn() == snake.getHead().getColumn() & foodTile[i].getRow() == snake.getHead().getRow()) {
                snake.grow();
                foodFlag = false;
                score++;
                foodTile[i].setType(TileType.EMPTY);
                return true;
            }
        }
        return false;
    }

    public boolean bumpInto() {
        Tile tile = field.getTile(snake.getHead().getColumn(), snake.getHead().getRow());
        return (tile.getType() == TileType.WALL || tile.getType() == TileType.SNAKE_BODY
                || tile.getType() == TileType.SNAKE_TAIL);
    }

    public void gameOver(GraphicsContext gc) {
        Color color1;
        Color color2;
        String text;
        if (winFlag) {
            color1 = Color.web("FFCA33");
            color2 = Color.web("EAB726");
            text = "   YOU WON";
        } else {
            color1 = Color.BLACK;
            color2 = Color.BLACK;
            text = " GAME OVER";
        }
        paintTheField(gc, color1, color2);
        GraphicsContext gc2 = ((Canvas) list.get(1)).getGraphicsContext2D();
        gc2.setFill(Color.RED);
        gc2.setStroke(Color.BLACK);
        gc2.setLineWidth(1);
        Font theFont = Font.font("Snap ITC", FontWeight.BOLD, 60);
        gc2.setFont(theFont);
        gc2.fillText(text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        gc2.strokeText(text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        // end();
        timer.stop();
    }

//    public void end() {
//        Button button = new Button();
//        button.setText("RESTART");
//        button.setOnAction((ActionEvent event) -> {
//            startGame(stage);
//        });
//        root.getChildren().add(button);
//    }

    public void printScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setLineWidth(1);
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        String pointsText = "Points: " + score + " / " + winCondition;
        gc.fillText(pointsText, WINDOW_WIDTH_PIXELS >> 5, WINDOW_WIDTH_PIXELS >> 5);
    }

    public static Image[] getImageRow(int frames, int width, int height, String pathFile) {
        Image[] img = new Image[frames];
        Image stripImg = new Image(pathFile);
        PixelReader pr = stripImg.getPixelReader();
        PixelWriter pw;

        for (int i = 0; i < frames; i++) {
            WritableImage wImg = new WritableImage(width, height);
            pw = wImg.getPixelWriter();

            for (int readX = 0; readX < width; readX++) {

                int ww = (height * i);
                for (int readY = ww; readY < ww + height; readY++) {
                    Color color = pr.getColor(readX, readY);
                    pw.setColor(readX, readY - ww, color);

                }
            }
            img[i] = wImg;
        }
        return img;
    }

    private void rotate(GraphicsContext gc, double angle, double pivotX, double pivotY) {
        Rotate rotate = new Rotate(angle, pivotX, pivotY);
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
    }
}
