package ru.nsu.belozerov.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nsu.belozerov.GameProperties;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        startGame(theStage);
    }

    public static void startGame(Stage theStage) {
        theStage.setTitle("Snake The Game");
        theStage.getIcons().add(new Image("icon.png"));
        GameProperties properties = new GameProperties();
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

        Button quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setOnAction(event -> {
            Platform.exit();
        });

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
