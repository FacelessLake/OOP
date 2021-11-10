package ru.nsu.belozerov;

import ru.nsu.belozerov.operation.*;

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
        String[] words = string.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            operandCheck(words[i]);
        }
        return (num.pop());
    }

    private boolean isParsable(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private void operandCheck(String expr) throws IllegalArgumentException {
        if (isParsable(expr)) {
            num.push(Double.parseDouble(expr));
        } else {
            switch (expr) {
                case "+": {
                    new Addition().count(num);
                    break;
                }
                case "-": {
                    new Subtraction().count(num);
                    break;
                }
                case "*": {
                    new Multiplication().count(num);
                    break;
                }
                case "/": {
                    new Division().count(num);
                    break;
                }
                case "sin": {
                    new Sinus().count(num);
                    break;
                }
                case "cos": {
                    new Cosine().count(num);
                    break;
                }
                case "sqrt": {
                    new SquareRoot().count(num);
                    break;
                }
                case "pow": {
                    new Exponentiation().count(num);
                    break;
                }
                case "log": {
                    new Logarithm().count(num);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown argument: " + expr);
                }
            }
        }
    }
}