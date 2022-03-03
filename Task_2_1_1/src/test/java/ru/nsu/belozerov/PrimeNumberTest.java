package ru.nsu.belozerov;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberTest {
    private final Random random = new Random();

    @Test
    void checkArray_true() {
        int[] arr = {6, 8, 7, 13, 9, 4};
        assertTrue(PrimeNumber.checkArray(arr));
    }

    @Test
    void checkPrimeThreads_true() throws ExecutionException, InterruptedException {
        int[] arr = {6, 8, 7, 13, 9, 4};
        assertTrue(PrimeNumberThreads.checkPrimeThreads(arr));
    }

    @Test
    void checkParallelStreams_true() {
        int[] arr = {6, 8, 7, 13, 9, 4};
        assertTrue(PrimeNumberParallelStream.parallelCheck(arr));
    }

    @Test
    void checkArray_false() {
        int[] arr = {11, 3, 5, 7};
        assertFalse(PrimeNumber.checkArray(arr));
    }

    @Test
    void checkPrimeThreads_false() throws ExecutionException, InterruptedException {
        int[] arr = {11, 3, 5, 7};
        assertFalse(PrimeNumberThreads.checkPrimeThreads(arr));
    }

    @Test
    void checkParallelStreams_false() {
        int[] arr = {11, 3, 5, 7};
        assertFalse(PrimeNumberParallelStream.parallelCheck(arr));
    }

    @Test
    void checkArray_withZeroesAndOnes() {
        int[] arr = {0, 1};
        assertTrue(PrimeNumber.checkArray(arr));
    }

    @Test
    void checkPrimeThreads_withZeroesAndOnes() throws ExecutionException, InterruptedException {
        int[] arr = {0, 1};
        assertTrue(PrimeNumberThreads.checkPrimeThreads(arr));
    }

    @Test
    void checkParallelStreams_withZeroesAndOnes() {
        int[] arr = {0, 1};
        assertTrue(PrimeNumberParallelStream.parallelCheck(arr));
    }

    @Test
    void checkArray_bigNumbers() {
        int[] arr = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        assertFalse(PrimeNumber.checkArray(arr));
    }

    @Test
    void checkPrimeThreads_bigNumbers() throws ExecutionException, InterruptedException {
        int[] arr = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        assertFalse(PrimeNumberThreads.checkPrimeThreads(arr));
    }

    @Test
    void checkParallelStreams_bigNumbers() {
        int[] arr = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        assertFalse(PrimeNumberParallelStream.parallelCheck(arr));
    }

    @Test
    void checkArray_notPrimeAtTheEnd() {
        int[] arr = {3, 3, 7, 7, 3, 4};
        assertTrue(PrimeNumber.checkArray(arr));
    }

    @Test
    void checkPrimeThreads_notPrimeAtTheEnd() throws ExecutionException, InterruptedException {
        int[] arr = {3, 3, 7, 7, 3, 4};
        assertTrue(PrimeNumberThreads.checkPrimeThreads(arr));
    }

    @Test
    void checkParallelStreams_notPrimeAtTheEnd() {
        int[] arr = {3, 3, 7, 7, 3, 4};
        assertTrue(PrimeNumberParallelStream.parallelCheck(arr));
    }

    @Test
    void checkPrimeThreads_random() throws ExecutionException, InterruptedException {
        int[] arr = random.ints(10000, 0, Integer.MAX_VALUE).toArray();
        assertEquals(PrimeNumber.checkArray(arr), PrimeNumberThreads.checkPrimeThreads(arr));
    }

    @Test
    void checkParallelStreams_random() {
        int[] arr = random.ints(10000, 0, Integer.MAX_VALUE).toArray();
        assertEquals(PrimeNumber.checkArray(arr), PrimeNumberParallelStream.parallelCheck(arr));
    }

    @Test
    void performanceTest() throws ExecutionException, InterruptedException {
        PerformanceTest.PrimeNumberPerformanceTest();
    }
}