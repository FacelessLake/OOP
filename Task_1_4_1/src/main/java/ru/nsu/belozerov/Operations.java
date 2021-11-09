package ru.nsu.belozerov;

import java.util.Stack;

public abstract class Operations {
    public abstract Integer getArity();
    public abstract void eval(Stack<Double> num);
}