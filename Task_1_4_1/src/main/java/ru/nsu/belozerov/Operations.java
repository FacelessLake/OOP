package ru.nsu.belozerov;

import java.util.Scanner;

public class Operations {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double num1 = getNum();
        double num2 = getNum();
        String operation = getOperation();
        double result = calc(num1, num2, operation);
        System.out.println(result);
    }

    public static double getNum() {
        double num;
        if (scanner.hasNextDouble()) {
            num = scanner.nextDouble();
        } else {
            scanner.next();
            num = getNum();
        }
        return num;
    }

    public static String getOperation() {
        String operation;
        if (scanner.hasNext()) {
            operation = scanner.next();
        } else {
            scanner.next();
            operation = getOperation();
        }
        return operation;
    }

    public static double calc(double num1, double num2, String operation) {
        double result;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "log":
                result = Math.log(num1);
                break;
            case "sqrt":
                result = Math.sqrt(num1);
                break;
            case "sin":
                result = Math.sin(num1);
                break;
            case "cos":
                result = Math.cos(num1);
                break;
            case "pow":
                result = Math.pow(num1, num2);
                break;
            default:
                System.out.println("No such operation");
                result = calc(num1, num2, getOperation());
        }
        return result;
    }
}