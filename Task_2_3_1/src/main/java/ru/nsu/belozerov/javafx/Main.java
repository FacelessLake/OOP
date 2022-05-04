package ru.nsu.belozerov.javafx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
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
import javafx.stage.Stage;
import ru.nsu.belozerov.Directions;
import ru.nsu.belozerov.Field;
import ru.nsu.belozerov.Tile;
import ru.nsu.belozerov.TileType;


public class Main extends Application {
    private final int SQUARE_SIZE = 50;
    private final int WINDOW_WIDTH_PIXELS = 1000;
    private final int WINDOW_HEIGHT_PIXELS = 1000;
    private final int WINDOW_WIDTH_SQUARES = 20;
    private final int WINDOW_HEIGHT_SQUARES = 20;

    private Field field = new Field(WINDOW_WIDTH_SQUARES, WINDOW_HEIGHT_SQUARES, SQUARE_SIZE);
    private final Snake snake = new Snake(WINDOW_HEIGHT_SQUARES / 2,
            WINDOW_WIDTH_SQUARES / 2, Directions.RIGHT, SQUARE_SIZE);

    private String input = "";

    Tile foodTile;
    private final Food food = new Food(field);
    private boolean foodFlag = false;

    LongValue lastNanoTime = new LongValue(System.nanoTime());
    IntValue score = new IntValue(0);
    Directions changeDirection = null;
    AnimationTimer timer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Snake The Game");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(WINDOW_WIDTH_PIXELS, WINDOW_HEIGHT_PIXELS);
        root.getChildren().add(canvas);

        theScene.setOnKeyPressed(
                e -> input = e.getCode().toString());

        GraphicsContext gc = canvas.getGraphicsContext2D();


        AnimatedImage animatedHead = snake.makeMainSnake();
        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                foodCheck();
                paintTheField(gc, Color.web("AAD751"), Color.web("A2D149")); //если вынести - будет мем
                gc.drawImage(food.getFoodImage(), foodTile.getColumn() * SQUARE_SIZE, foodTile.getRow() * SQUARE_SIZE);

                // game logic
                switch (input) {
                    case "LEFT" -> {
                        if (snake.getDirection() != Directions.RIGHT) {
                            snake.setDirection(Directions.LEFT);
                            changeDirection = Directions.LEFT;
                        }
                    }
                    case "RIGHT" -> {
                        if (snake.getDirection() != Directions.LEFT) {
                            snake.setDirection(Directions.RIGHT);
                            changeDirection = Directions.RIGHT;
                        }
                    }
                    case "UP" -> {
                        if (snake.getDirection() != Directions.DOWN) {
                            snake.setDirection(Directions.UP);
                            changeDirection = Directions.UP;
                        }
                    }
                    case "DOWN" -> {
                        if (snake.getDirection() != Directions.UP) {
                            snake.setDirection(Directions.DOWN);
                            changeDirection = Directions.DOWN;

                        }
                    }
                }

                if (changeDirection != null) {
                    snake.move(changeDirection);
                }
                consumeFood();
                if (bumpInto()) {
                    gameOver(gc);
                }

                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 200000000.0;
                lastNanoTime.value = currentNanoTime;

                // render
                gc.drawImage(animatedHead.getFrame(elapsedTime), snake.getHead().getColumn() * SQUARE_SIZE,
                        snake.getHead().getRow() * SQUARE_SIZE);
                field = snake.drawMainSnake(gc, field);

                gc.setFill(Color.WHITE);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1);
                Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
                gc.setFont(theFont);
                String pointsText = "Points: " + score.value;
                gc.fillText(pointsText, WINDOW_WIDTH_PIXELS >> 5, SQUARE_SIZE - 10);
                gc.strokeText(pointsText, WINDOW_WIDTH_PIXELS >> 5, SQUARE_SIZE - 10);
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

    private void consumeFood() {
        if (foodTile.getColumn() == snake.getHead().getColumn() & foodTile.getRow() == snake.getHead().getRow()) {
            snake.grow();
            foodFlag = false;
            score.value++;
        }
    }

    public boolean bumpInto() {
        Tile tile = field.getTile(snake.getHead().getColumn(), snake.getHead().getRow());
        return (tile.getType() == TileType.WALL || tile.getType() == TileType.SNAKE_BODY);
    }

    public void gameOver(GraphicsContext gc) {
        paintTheField(gc, Color.BLACK, Color.BLACK);
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 60);
        gc.setFont(theFont);
        String Text = "     GAME OVER";
        gc.fillText(Text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        gc.strokeText(Text, WINDOW_WIDTH_PIXELS >> 2, WINDOW_HEIGHT_PIXELS >> 2);
        timer.stop();
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
