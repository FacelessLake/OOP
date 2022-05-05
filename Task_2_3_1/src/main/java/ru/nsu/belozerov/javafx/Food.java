package ru.nsu.belozerov.javafx;

import javafx.scene.image.Image;
import ru.nsu.belozerov.*;

import java.util.Random;

public class Food {
    private final Field field;
    private final Image foodImage = new Image("food1.png");
    private final Random random = new Random();

    public Food(Field field) {
        this.field = field;
    }

    public Tile createFood() {
        return new Tile(random.nextInt(field.getWidth()), random.nextInt(field.getHeight()), TileType.FOOD);
    }

    public Image getFoodImage() {
        return foodImage;
    }
}
