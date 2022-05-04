package ru.nsu.belozerov.javafx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import ru.nsu.belozerov.Directions;
import ru.nsu.belozerov.Field;
import ru.nsu.belozerov.Snake;

import java.util.ArrayList;


public class Main extends Application {
    private final int SQUARE_SIZE = 50;
    private final int WINDOW_WIDTH_PIXELS = 1000;
    private final int WINDOW_HEIGHT_PIXELS = 1000;
    private final int WINDOW_WIDTH_SQUARES = 20;
    private final int WINDOW_HEIGHT_SQUARES = 20;
    private final Field field = new Field(WINDOW_WIDTH_SQUARES, WINDOW_HEIGHT_SQUARES, SQUARE_SIZE);
    private final Snake snake = new Snake(WINDOW_HEIGHT_SQUARES, WINDOW_WIDTH_SQUARES, Directions.RIGHT);
    private final Sprite snakeSprite = new Sprite();
    private ArrayList<String> input = new ArrayList<String>();
    LongValue lastNanoTime = new LongValue(System.nanoTime());
    IntValue score = new IntValue(0);
    Directions changeDirection = null;

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
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

                Image[] headRight = new Image[3];
        Image[] headDown = new Image[3];
        Image[] headUp = new Image[3];
        Image[] headLeft = new Image[3];
        for (int i = 0; i < 3; i++) {
            headRight[i] = new Image("snake_right" + (i + 1) + ".png");
            headDown[i] = new Image("snake_down" + (i + 1) + ".png");
            headUp[i] = new Image("snake_up" + (i + 1) + ".png");
            headLeft[i] = new Image("snake_left" + (i + 1) + ".png");
        }

        AnimatedImage head = new AnimatedImage(headRight,0.00009);
        snakeSprite.setAnimatedImage(head);
        snakeSprite.setPosition((WINDOW_WIDTH_SQUARES >> 1) * SQUARE_SIZE, (WINDOW_HEIGHT_SQUARES >> 1) * SQUARE_SIZE);


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                paintTheField(gc, Color.web("AAD751"), Color.web("A2D149"));

                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 200000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic
                if (input.contains("LEFT") & snake.getDirection() != Directions.RIGHT) {
                    snake.setDirection(Directions.LEFT);
                    changeDirection = Directions.LEFT;
                }
                if (input.contains("RIGHT") & snake.getDirection() != Directions.LEFT) {
                    snake.setDirection(Directions.RIGHT);
                    changeDirection = Directions.RIGHT;
                }
                if (input.contains("UP") & snake.getDirection() != Directions.DOWN) {
                    snake.setDirection(Directions.UP);
                    changeDirection = Directions.UP;
                }
                if (input.contains("DOWN") & snake.getDirection() != Directions.UP) {
                    snake.setDirection(Directions.DOWN);
                    changeDirection = Directions.DOWN;
                }

                if (changeDirection != null & snakeSprite.getBoundary().getMinX() % SQUARE_SIZE < 5
                        & snakeSprite.getBoundary().getMinY() % SQUARE_SIZE < 5) {
                    switch (changeDirection) {
                        case UP -> {
                            snakeSprite.setVelocity(0, -SQUARE_SIZE);
                            changeDirection = null;
                            head.setFrames(headUp);
                        }
                        case RIGHT -> {
                            snakeSprite.setVelocity(SQUARE_SIZE, 0);
                            changeDirection = null;
                            head.setFrames(headRight);
                        }
                        case DOWN -> {
                            snakeSprite.setVelocity(0, SQUARE_SIZE);
                            changeDirection = null;
                            head.setFrames(headDown);
                        }
                        case LEFT -> {
                            snakeSprite.setVelocity(-SQUARE_SIZE, 0);
                            changeDirection = null;
                            head.setFrames(headLeft);
                        }
                    }
                }
                snakeSprite.update(elapsedTime);

                // collision detection

//                Iterator<Sprite> moneybagIter = moneybagList.iterator();
//                while (moneybagIter.hasNext()) {
//                    Sprite moneybag = moneybagIter.next();
//                    if (snakeSprite.intersects(moneybag)) {
//                        moneybagIter.remove();
//                        score.value++;
//                    }
//                }

                // render

                //gc.clearRect(0, 0, 512, 512);
                snakeSprite.renderAnimated(gc, elapsedTime);

//                for (Sprite moneybag : moneybagList)
//                    moneybag.render(gc);

                String pointsText = "Points: " + (score.value);
                gc.fillText(pointsText, 360, SQUARE_SIZE);
                gc.strokeText(pointsText, 360, SQUARE_SIZE);
            }
        }.start();

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
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
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
