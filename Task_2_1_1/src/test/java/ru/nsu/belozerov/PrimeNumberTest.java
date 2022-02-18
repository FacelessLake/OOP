package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberTest {
    PrimeNumber pn = new PrimeNumber();
    PrimeNumberThreads pnth = new PrimeNumberThreads();

    @Test
    void checkArray_true() {
        int[] arr = {6, 8, 7, 13, 9, 4};
        assertTrue(pn.checkArray(arr));
    }

    @Test
    void checkArray_false() {
        int[] arr = {11, 3, 5, 7};
        assertFalse(pn.checkArray(arr));
    }

    @Test
    void checkArray_withZeroesAndOnes() {
        int[] arr = {0, 1};
        assertTrue(pn.checkArray(arr));
    }

    @Test
    void checkArray_bigNumbers() {
        int[] arr = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        assertFalse(pn.checkArray(arr));
    }

    @Test
    void checkPrimeThreads_true() throws ExecutionException, InterruptedException {
        int[] arr = {6, 8, 7, 13, 9, 4};
        assertTrue(pnth.checkPrimeThreads(arr));
    }

    @Test
    void checkPrimeThreads_false() throws ExecutionException, InterruptedException {
        int[] arr = {11, 3, 5, 7};
        assertFalse(pnth.checkPrimeThreads(arr));
    }

    @Test
    void checkPrimeThreads_withZeroesAndOnes() throws ExecutionException, InterruptedException {
        int[] arr = {0, 1};
        assertTrue(pnth.checkPrimeThreads(arr));
    }

    @Test
    void checkPrimeThreads_bigNumbers() throws ExecutionException, InterruptedException {
        int[] arr = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        assertFalse(pnth.checkPrimeThreads(arr));
    }
}