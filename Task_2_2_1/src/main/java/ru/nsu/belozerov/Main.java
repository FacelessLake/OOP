package ru.nsu.belozerov;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pizzeria pizzeria = new Pizzeria(5, new int[]{1000, 2 * 1000, 3 * 1000, 4 * 1000, 5 * 1000}, 3, new int[]{20 * 1000, 10 * 1000, 15 * 1000}, 20, new int[]{2, 3, 4}, 5 * 1000);
        pizzeria.pizzeriaStart();
        Thread.sleep(1000 * 60);
        pizzeria.pizzeriaStop();
    }
}
