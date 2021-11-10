package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class Addition extends Operations {
    @Override
    public void count(Stack<Double> num) throws EmptyStackException {
        try {
            num.push(num.pop() + num.pop());
        } catch (EmptyStackException e) {
            System.out.println("Not enough arguments for addition!");
        }
    }
}
