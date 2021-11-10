package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class SquareRoot extends Operations {
    @Override
    public void count(Stack<Double> num) {
        try {
            num.push(Math.sqrt(num.pop()));
            if (Double.isNaN(num.peek())) {
                throw new ArithmeticException("You can take square root from positive number only!");
            }
        } catch (EmptyStackException e) {
            System.out.println("Not enough arguments for logarithm!");
            throw e;
        }
    }
}
