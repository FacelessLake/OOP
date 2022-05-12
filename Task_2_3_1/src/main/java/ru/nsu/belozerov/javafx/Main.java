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
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nsu.belozerov.GameProperties;


public class Main extends Application {
    private int WINDOW_WIDTH_PIXELS;
    private int WINDOW_HEIGHT_PIXELS;
    Canvas canvas1;
    Canvas canvas2;
    Pane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Snake The Game");
        theStage.getIcons().add(new Image("icon.png"));
        GameProperties properties = new GameProperties();
        GameFX game = new GameFX(properties);
        WINDOW_WIDTH_PIXELS = properties.getWindowWidthPixels();
        WINDOW_HEIGHT_PIXELS = properties.getWindowHeightPixels();

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
        startButton.setLayoutX(WINDOW_HEIGHT_PIXELS >> 1);
        startButton.setLayoutY(WINDOW_WIDTH_PIXELS >> 1);
        startButton.setText("Start new game");
        startButton.setOnAction(event -> {
            list.remove(2,3);
            game.startGame(theScene, list);
        });

        Button settingsButton = new Button();
        settingsButton.setText("Settings");
        settingsButton.setOnAction(event -> {
            Label secondLabel = new Label("I'm a Label on new Window");

            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);

            Scene secondScene = new Scene(secondaryLayout, 230, 100);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Settings");
            newWindow.setScene(secondScene);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(theStage);

            // Set position of second window, related to primary window.
            newWindow.setX(theStage.getX());
            newWindow.setY(theStage.getY());

            newWindow.show();
        });
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(startButton, settingsButton);
        vbox.setSpacing(10);
        list.add(vbox);
        theStage.setTitle("Snake the Game");
        theStage.setScene(theScene);
        theStage.show();
    }
}
