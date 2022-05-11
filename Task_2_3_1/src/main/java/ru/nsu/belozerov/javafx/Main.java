package ru.nsu.belozerov.javafx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import ru.nsu.belozerov.*;


public class Main extends Application {
    private final int SQUARE_SIZE = 100;
    private final int WINDOW_WIDTH_PIXELS = 1000;
    private final int WINDOW_HEIGHT_PIXELS = 1000;
    private final int WINDOW_WIDTH_SQUARES = 10;
    private final int WINDOW_HEIGHT_SQUARES = 10;

    private final Field field = new Field(WINDOW_WIDTH_SQUARES, WINDOW_HEIGHT_SQUARES, SQUARE_SIZE);
    private final Snake snake = new Snake(WINDOW_HEIGHT_SQUARES / 2, WINDOW_WIDTH_SQUARES / 2, Directions.RIGHT, field);
    private final SnakeFX snakeFX = new SnakeFX(SQUARE_SIZE, snake);
    private final Food food = new Food(field, SQUARE_SIZE);
    private boolean foodFlag = false;

    Tile foodTile;
    AnimationTimer timer;
    private String input = "";
    private final int winCondition = 20;
    Integer score = 0;
    Canvas canvas1;
    Canvas canvas2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Snake The Game");
        theStage.getIcons().add(new Image("icon.png"));
        canvas1 = new Canvas(WINDOW_WIDTH_PIXELS, WINDOW_HEIGHT_PIXELS);
        canvas2 = new Canvas(WINDOW_WIDTH_PIXELS, WINDOW_HEIGHT_PIXELS);
        Pane root = new Pane();
        root.getChildren().addAll(canvas1, canvas2);
        canvas2.toFront();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        GraphicsContext gc = canvas1.getGraphicsContext2D();
        AnimatedImage animatedHead = snakeFX.makeMainSnake();
        snakeFX.drawMainSnake(gc, field);
        gc.drawImage(animatedHead.getFrame(0), snake.getHead().getColumn() * SQUARE_SIZE,
                snake.getHead().getRow() * SQUARE_SIZE);
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
                paintTheField(gc, Color.web("AAD751"), Color.web("A2D149")); //если вынести - будет мем
                gc.drawImage(food.getFoodImage(), foodTile.getColumn() * SQUARE_SIZE, foodTile.getRow() * SQUARE_SIZE);

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
                    speedDelay -= 100/winCondition;
                }

                if (bumpInto()) {
                    gameOver(gc);
                }
                if (score == winCondition) {
                    gameWon(gc);
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
        theStage.show();
    }

    private void paintTheField(GraphicsContext gc, Color color1, Color color2) {
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
            while (true) {
                foodTile = food.createFood();
                if (field.getTile(foodTile.getColumn(), foodTile.getRow()).getType() == TileType.EMPTY) {
                    field.setTile(foodTile);
                    break;
                }
            }
            foodFlag = true;
        }
    }

    private boolean consumeFood() {
        if (foodTile.getColumn() == snake.getHead().getColumn() & foodTile.getRow() == snake.getHead().getRow()) {
            snake.grow();
            foodFlag = false;
            score++;
            return true;
        }
        return false;
    }

    public boolean bumpInto() {
        Tile tile = field.getTile(snake.getHead().getColumn(), snake.getHead().getRow());
        return (tile.getType() == TileType.WALL || tile.getType() == TileType.SNAKE_BODY
                || tile.getType() == TileType.SNAKE_TAIL);
    }

    public void gameOver(GraphicsContext gc) {
        paintTheField(gc, Color.BLACK, Color.BLACK);
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        gc2.setFill(Color.RED);
        gc2.setStroke(Color.BLACK);
        gc2.setLineWidth(1);
        Font theFont = Font.font("Snap ITC", FontWeight.BOLD, 60);
        gc2.setFont(theFont);
        String text = " GAME OVER";
        gc2.fillText(text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        gc2.strokeText(text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        timer.stop();
    }

    public void gameWon(GraphicsContext gc) {
        paintTheField(gc, Color.web("FFCA33"), Color.web("EAB726"));
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        gc2.setFill(Color.RED);
        gc2.setStroke(Color.BLACK);
        gc2.setLineWidth(1);
        Font theFont = Font.font("Snap ITC", FontWeight.BOLD, 60);
        gc2.setFont(theFont);
        String text = "   YOU WON";
        gc2.fillText(text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        gc2.strokeText(text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        timer.stop();
    }

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
