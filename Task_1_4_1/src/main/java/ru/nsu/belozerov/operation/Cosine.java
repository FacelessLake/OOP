package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class Cosine extends Operations {
    @Override
    public void count(Stack<Double> num) throws EmptyStackException {
        num.push(Math.cos(num.pop()));
    }
}
