package ru.nsu.belozerov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    public void Test_MyStackOnInt() {
        MyStack<Integer> ms = new MyStack<>();
        ms.push(47);
        ms.push(19);
        Assertions.assertEquals(2, ms.count());

        MyStack<Integer> ms2 = new MyStack<>();
        ms2.pushStack(ms);
        ms2.pop();
        ms.popStack(1);
        Assertions.assertEquals(ms, ms2);
    }
}