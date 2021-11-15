package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class Subtraction extends Operations {
    @Override
    public void count(Stack<Double> num) throws EmptyStackException {
        num.push(num.pop() - num.pop());
    }
}
