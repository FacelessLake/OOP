package ru.nsu.belozerov;

import java.util.Stack;

public class Addition extends Operations{

    @Override
    public Integer getArity() {
        return 2;
    }

    @Override
    public void eval(Stack<Double> num) {
        return num.pop() + num.pop();
    }
}
