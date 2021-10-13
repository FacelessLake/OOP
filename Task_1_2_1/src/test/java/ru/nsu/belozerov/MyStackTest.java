package ru.nsu.belozerov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    private MyStack<Integer> msInt;
    private MyStack<String> msStr;

    @BeforeEach
    void myStack() {
        msInt = new MyStack<>();
    }

    @Test
    public void pop_emptyStack() {
        assertThrows(EmptyStackException.class, msInt::pop);
    }

    @Test
    public void popStack_emptyStack() {
        assertThrows(IndexOutOfBoundsException.class, () -> msInt.popStack(4));
    }

    @Test
    public void pushStack_emptyStack() {
        MyStack<Integer> ms2 = new MyStack<>();
        ms2.pushStack(msInt);
        assertEquals(0, msInt.count());
    }



    @Test
    public void push_intStack() {
        msInt.push(47);
        msInt.push(19);
        assertEquals(2, msInt.count());
    }

    @Test
    public void popStack_intStack() {
        msInt.push(-11);
        msInt.push(25);
        MyStack<Integer> ms2 = msInt.popStack(2);

        assertEquals(ms2.pop(),25);
        assertEquals(ms2.pop(),-11);
    }

    @Test
    public void pushStack_intStack() {
        msInt.push(999);
        msInt.push(-666);
        MyStack<Integer> ms2 = new MyStack<>();
        ms2.pushStack(msInt);

        assertEquals(msInt.pop(),ms2.pop());
    }

    @BeforeEach
    void myStringStack() {
        msStr = new MyStack<>();
    }

    @Test
    public void push_stringStack() {
        msStr.push("I");
        msStr.push("Love");
        msStr.push("D&D");
        assertEquals(3, msStr.count());
    }

    @Test
    public void popStack_stringStack() {
        msStr.push("World");
        msStr.push("Hello");
        MyStack<String > ms2 = msStr.popStack(2);

        assertEquals(ms2.pop(),"Hello");
        assertEquals(ms2.pop(),"World");
    }

    @Test
    public void pushStack_stringStack() {
        msStr.push("999");
        msStr.push("-666");
        MyStack<String > ms2 = new MyStack<>();
        ms2.pushStack(msStr);

        assertEquals(msStr.pop(),ms2.pop());
    }

    @Test
    public void iterator_string() {
        msStr.push("N");
        msStr.push("Y");
        msStr.push("C");

        StringBuilder answer = new StringBuilder();
        for (String s : msStr) {
            answer.append(s);
        }

        Assertions.assertEquals("NYC", answer.toString());
    }
}