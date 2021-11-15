package ru.nsu.belozerov;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private final Stack<Double> num = new Stack<>();

    public double compute(String input) {
        Scanner scanner = new Scanner(input);
        return compute(scanner);
    }

    public double compute(Scanner scanner) {
        String string = scanner.nextLine();
        String[] words = string.replaceAll("\\s+", " ").split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            Factory.operandCheck(num, words[i]);
        }
        return num.pop();
    }

}