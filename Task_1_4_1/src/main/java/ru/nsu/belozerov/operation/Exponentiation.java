package ru.nsu.belozerov.operation;

import java.util.EmptyStackException;
import java.util.Stack;

public class Exponentiation extends Operations {
    @Override
    public void count(Stack<Double> num) throws EmptyStackException {
        num.push(Math.pow(num.pop(), num.pop()));
    }
}
