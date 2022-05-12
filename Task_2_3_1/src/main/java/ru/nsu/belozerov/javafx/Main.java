package ru.nsu.belozerov.javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nsu.belozerov.GameProperties;

import static javafx.application.Platform.exit;


public class Main extends Application {
    static GameProperties properties;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        properties = new GameProperties();
        startGame(theStage);
    }

    public static void startGame(Stage theStage) {
        theStage.setTitle("Snake The Game");
        theStage.getIcons().add(new Image("icon.png"));
        GameFX game = new GameFX(properties, theStage);
        int WINDOW_WIDTH_PIXELS = properties.getWindowWidthPixels();
        int WINDOW_HEIGHT_PIXELS = properties.getWindowHeightPixels();
        Canvas canvas1;
        Canvas canvas2;
        Pane root;
        root = new StackPane();
        ObservableList<Node> list = root.getChildren();
        canvas1 = new Canvas(WINDOW_WIDTH_PIXELS, WINDOW_HEIGHT_PIXELS);
        canvas2 = new Canvas(WINDOW_WIDTH_PIXELS, WINDOW_HEIGHT_PIXELS);
        list.addAll(canvas1, canvas2);
        canvas2.toFront();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        GraphicsContext gc = canvas1.getGraphicsContext2D();
        game.paintTheField(gc, Color.web("AAD751"), Color.web("A2D149"));

        Button startButton = new Button();
        startButton.setText("Start new game");
        startButton.setOnAction(event -> {
            list.remove(2, 3);
            game.newGame(theScene, root);
        });

        Button settingsButton = new Button();
        settingsButton.setText("Settings");
        settingsButton.setOnAction(event -> {
            Stage newWindow = new Stage();

            Label winLabel = new Label("Number of apples to win: ");
            TextField winTextField = new TextField();
            winTextField.setText("15");

            Label foodLabel = new Label("Number of apples on the field at the same time: ");
            Slider foodSlider = new Slider(1, 5, 3);
            foodSlider.setShowTickMarks(true);
            foodSlider.setShowTickLabels(true);
            foodSlider.setBlockIncrement(1);
            foodSlider.setMajorTickUnit(1);
            foodSlider.setMinorTickCount(0);
            foodSlider.setSnapToTicks(true);

            Label squareSizeLabel = new Label("Size of square (in pixels):");
            TextField squareSizeTextField = new TextField();
            squareSizeTextField.setText("50");

            Label widthLabel = new Label("Width of the field (in pixels):");
            TextField widthTextField = new TextField();
            widthTextField.setText("1000");

            Label heightLabel = new Label("Height of the field (in pixels):");
            TextField heightTextField = new TextField();
            heightTextField.setText("1000");

            Button saveButton = new Button();
            saveButton.setText("Save");
            saveButton.setOnAction(event2 -> {
                properties.setWinCondition(Integer.parseInt(winTextField.getText()));
                properties.setFoodCnt((int) foodSlider.getValue());
                int height = Integer.parseInt(heightTextField.getText());
                properties.setWindowHeightPixels(height);
                int width = Integer.parseInt(widthTextField.getText());
                properties.setWindowWidthPixels(width);
                int size = Integer.parseInt(squareSizeTextField.getText());
                if (size < height & size < width) {
                    properties.setSquareSize(Integer.parseInt(squareSizeTextField.getText()));
                }
                newWindow.close();
                startGame(theStage);
            });

            VBox vboxSettings = new VBox();
            vboxSettings.setAlignment(Pos.CENTER);
            vboxSettings.setSpacing(10);
            vboxSettings.getChildren().addAll(winLabel, winTextField, foodLabel, foodSlider, widthLabel, widthTextField,
                    heightLabel, heightTextField, squareSizeLabel, squareSizeTextField, saveButton);
            vboxSettings.setSpacing(10);
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(vboxSettings);
            Scene secondScene = new Scene(secondaryLayout);

            // New window (Stage)
            newWindow.setTitle("Settings");
            newWindow.setScene(secondScene);
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(theStage);
            newWindow.setX(theStage.getX());
            newWindow.setY(theStage.getY());
            newWindow.show();
        });

        Button quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setOnAction(event -> exit());

        VBox vboxStart = new VBox();
        vboxStart.setAlignment(Pos.CENTER);
        vboxStart.getChildren().addAll(startButton, settingsButton, quitButton);
        vboxStart.setSpacing(10);
        list.add(vboxStart);
        theStage.setTitle("Snake the Game");
        theStage.setScene(theScene);
        theStage.show();
    }
}
