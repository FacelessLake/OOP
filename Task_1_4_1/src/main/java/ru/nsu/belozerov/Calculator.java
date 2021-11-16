package ru.nsu.belozerov;

import ru.nsu.belozerov.operation.Operations;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private final Stack<Double> num = new Stack<>();

    private static boolean isParsable(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public double compute(String input) {
        Scanner scanner = new Scanner(input);
        return compute(scanner);
    }

    public double compute(Scanner scanner) throws IllegalArgumentException {
        String string = scanner.nextLine();
        String[] words = string.replaceAll("\\s+", " ").split(" ");
        Operations operation;
        for (int i = words.length - 1; i >= 0; i--) {
            if (isParsable(words[i])) {
                num.push(Double.parseDouble(words[i]));
            } else {
                operation = Factory.operandCheck(words[i]);
                operation.count(num);
            }
        }
        return num.pop();
    }

}