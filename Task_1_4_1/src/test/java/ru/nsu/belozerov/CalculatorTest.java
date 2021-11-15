package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator cl = new Calculator();

    @Test
    void compute_addition() {
        double a = 10, b = 15, c = 8;
        assertEquals(a + b + c, cl.compute("+ + 10 15 8"));
    }

    @Test
    void compute_subtraction() {
        double a = 10, b = 15;
        assertEquals(a - b, cl.compute("- 10 15"));
    }

    @Test
    void compute_multiplication() {
        double a = -10, b = 0.9;
        assertEquals(a * b, cl.compute("* -10 0.9"));
    }

    @Test
    void compute_division() {
        double a = 10, b = 3;
        assertEquals(a / b, cl.compute("/ 10 3"));
    }

    @Test
    void compute_divisionByZero() {
        assertThrows(ArithmeticException.class, () -> cl.compute("/ 6 0"));
    }

    @Test
    void compute_sinus() {
        double a = 15;
        assertEquals(Math.sin(Math.sin(a)), cl.compute("sin sin 15"));
    }

    @Test
    void compute_cosine() {
        double a = 10;
        assertEquals(Math.cos(Math.cos(Math.cos(a))), cl.compute("cos cos cos 10"));
    }

    @Test
    void compute_squareRoot() {
        assertEquals(2, cl.compute("sqrt sqrt 16"));
    }

    @Test
    void compute_squareRootFromNegative() {
        assertThrows(ArithmeticException.class, () -> cl.compute("sqrt -7"));
    }

    @Test
    void compute_exponentiation() {
        double a = 7, b = -2;
        assertEquals(Math.pow(a, b), cl.compute("pow 7 -2"));
    }

    @Test
    void compute_logarithm() {
        double a = 7;
        assertEquals(Math.log(a), cl.compute("log 7"));
    }

    @Test
    void compute_logarithmFromNegative() {
        assertThrows(ArithmeticException.class, () -> cl.compute("log -7"));
    }

    @Test
    void compute_wrongInput() {
        assertThrows(IllegalArgumentException.class, () -> cl.compute("God save the queen! 17 6"));
    }

    @Test
    void compute_notEnoughArguments() {
        assertThrows(EmptyStackException.class, () -> cl.compute("+ sin 17"));
    }

    @Test
    void compute_expression() {
        assertEquals(0, cl.compute("sin + - 1 2 1"));
    }

    @Test
    void compute_withMultipleSpaces() {
        double a = 10, b = 15, c = 8;
        assertEquals(a + b + c, cl.compute("+     +      10  15  8"));
    }
}