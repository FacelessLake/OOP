package ru.nsu.belozerov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

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
        assertArrayEquals(ms.arr, ms2.arr);
    }

    @Test
    public void Test_MyStackOnString() {
        MyStack<String> ms = new MyStack<>();
        ms.push("I");
        ms.push("Love");
        ms.push("D&D");
        Assertions.assertEquals(3, ms.count());

        MyStack<String> ms2 = new MyStack<>();
        ms2.pushStack(ms);
        String val1 = ms2.pop();
        MyStack<String> val = ms.popStack(2);
        String val2 = val.pop();
        assertEquals(val2, val1);
    }

    @Test
    public void Test_EmptyStack() {
        MyStack<String> ms = new MyStack<>();
        Assertions.assertEquals(0, ms.count());

        MyStack<String> ms2 = new MyStack<>();
        ms2.pushStack(ms);
        Assertions.assertEquals(0, ms2.count());

        Assertions.assertThrows(EmptyStackException.class, ms::pop);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> ms.popStack(4));
    }
}