package ru.nsu.belozerov;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pizzeria pizzeria = new Pizzeria(5, 10, 3, 10, 20, 6, 10);
        pizzeria.pizzeriaStart();
        pizzeria.pizzeriaStop();
    }
}
