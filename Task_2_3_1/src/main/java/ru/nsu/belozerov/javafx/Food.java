package ru.nsu.belozerov.javafx;

import javafx.scene.image.Image;
import ru.nsu.belozerov.*;

import java.util.Random;

public class Food {
    private final Field field;
    private final Image foodImage;
    private final Random random = new Random();

    public Food(Field field, int tileSize) {
        this.field = field;
        foodImage = new Image("food1.png",tileSize*1.05,tileSize*1.05,true,true);
    }

    public Tile createFood() {
        return new Tile(random.nextInt(field.getWidth()), random.nextInt(field.getHeight()), TileType.FOOD);
    }

    public Image getFoodImage() {
        return foodImage;
    }
}
