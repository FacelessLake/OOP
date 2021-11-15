package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class Division extends Operations {
    @Override
    public void count(Stack<Double> num) throws EmptyStackException {
        num.push(num.pop() / num.pop());
        if (Double.isInfinite(num.peek())) {
            throw new ArithmeticException("You can't divide by zero!");
        }
    }
}
