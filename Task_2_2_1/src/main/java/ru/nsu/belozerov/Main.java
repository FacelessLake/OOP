package ru.nsu.belozerov;

import ru.nsu.belozerov.json.JsonHandler;
import ru.nsu.belozerov.json.JsonPizzeria;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        JsonHandler handler = new JsonHandler();
        JsonPizzeria jp = handler.jsonHandle();
        Pizzeria pizzeria = new Pizzeria(jp.getBakersAmount(), jp.getBakersSpeeds(), jp.getDeliverersAmount(), jp.getDeliverersSpeeds(), jp.getStorageSize(), jp.getTrunkSizes(), jp.getOrdersDelay());
        pizzeria.pizzeriaStart();
        Thread.sleep(1000 * 60);
        pizzeria.pizzeriaStop();
    }
}
