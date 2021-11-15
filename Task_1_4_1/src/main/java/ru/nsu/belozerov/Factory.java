package ru.nsu.belozerov;

import ru.nsu.belozerov.operation.*;

import java.util.Stack;

public class Factory {

    private static boolean isParsable(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static void operandCheck(Stack<Double> num, String expr) throws IllegalArgumentException {
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
