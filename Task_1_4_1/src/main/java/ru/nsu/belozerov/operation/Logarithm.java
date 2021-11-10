package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class Logarithm extends Operations {
    @Override
    public void count(Stack<Double> num) {
        try {
            num.push(Math.log(num.pop()));
            if (Double.isNaN(num.peek())) {
                throw new ArithmeticException("Argument of logarithm should be positive!");
            }
        } catch (EmptyStackException e) {
            System.out.println("Not enough arguments for logarithm!");
            throw e;
        }
    }
}
