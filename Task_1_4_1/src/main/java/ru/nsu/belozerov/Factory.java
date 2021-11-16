package ru.nsu.belozerov;

import ru.nsu.belozerov.operation.*;

public class Factory {

    public static Operations operandCheck(String expr) {
        Operations op;
        switch (expr) {
            case "+": {
                op = new Addition();
                break;
            }
            case "-": {
                op = new Subtraction();
                break;
            }
            case "*": {
                op = new Multiplication();
                break;
            }
            case "/": {
                op = new Division();
                break;
            }
            case "sin": {
                op = new Sinus();
                break;
            }
            case "cos": {
                op = new Cosine();
                break;
            }
            case "sqrt": {
                op = new SquareRoot();
                break;
            }
            case "pow": {
                op = new Exponentiation();
                break;
            }
            case "log": {
                op = new Logarithm();
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown argument: " + expr);
            }
        }
        return op;
    }
}
