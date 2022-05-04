package ru.nsu.belozerov.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.belozerov.*;

public class Food {
    private final Field field;
    private final Image foodImage = new Image("food1.png");

    public Food(Field field) {
        this.field = field;
    }

    public void createFood(GraphicsContext gc) {
        while (true) {
            Tile foodTile = new Tile((int) (Math.random() * field.getWidth()) / field.getTileSize(),
                    (int) (Math.random() * field.getHeight()) / field.getTileSize(), field.getTileSize(), TileType.FOOD);
            if (field.getTile(foodTile.getColumn(), foodTile.getRow()).getType() == TileType.EMPTY) {
                gc.drawImage(foodImage, foodTile.getColumn(), foodTile.getRow());
                break;
            }
        }
    }
}
