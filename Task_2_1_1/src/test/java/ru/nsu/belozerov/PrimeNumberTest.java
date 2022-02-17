package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberTest {
    PrimeNumber pn = new PrimeNumber();

    @Test
    void checkArrayTrue() {
        int[] arr = {6,8,7,13,9,4};
        assertTrue(pn.checkArray(arr));
    }

    @Test
    void checkArrayFalse() {
        int[] arr = {6,8,9,4};
        assertFalse(pn.checkArray(arr));
    }
}