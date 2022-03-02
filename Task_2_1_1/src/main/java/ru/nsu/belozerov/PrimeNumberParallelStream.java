package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class for prime number check using threads using parallel streams
 */
public class PrimeNumberParallelStream {
    /**
     * Checks if an array contains a number that is NOT prime using parallel streams
     *
     * @param arr - array you want to check
     * @return true - if it contains NOT prime number, false - otherwise
     */
    static public boolean parallelCheck(int[] arr) {
        IntStream stream = Arrays.stream(arr);
        return stream.parallel().anyMatch(PrimeNumber::checkNotPrime);
    }
}
