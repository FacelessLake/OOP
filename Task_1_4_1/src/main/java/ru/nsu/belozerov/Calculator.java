package ru.nsu.belozerov;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private final Stack<Double> num = new Stack<>();

    private double compute(Scanner scanner) {
        String string = scanner.nextLine();
        String[] words = string.split(" ");
        for (int i; i<words) {
            operandCheck(w);
        }
        return ();
    }

    public static boolean isParsable(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public Operations operandCheck(String expr) {
        try {
            if (isParsable(expr)) {
                num.push(Double.parseDouble(expr));
            } else {
                switch (expr) {
                    case "+": {
                        new Addition();
                        break;
                    }
                    case "-": {
                        new Substraction();
                        break;
                    }
                    case "*": {
                        new Multiplication();
                        break;
                    }
                    case "/": {
                        new Division();
                        break;
                    }
                    case "sin": {
                        new Sinus();
                        break;
                    }
                    case "cos": {
                        new Cosine();
                        break;
                    }
                    case "sqrt": {
                        new SquareRoot();
                        break;
                    }
                    case "pow": {
                        new Power();
                        break;
                    }
                    case "log": {
                        new Logarithm();
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException();
                        break;
                    }
                }
            }
        } catch (IllegalArgumentException) {
            System.out.println("No such argument:" + expr);
        }
    }
}