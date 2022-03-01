package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PrimeNumberParallelStream {
    static public boolean parallelCheck(int[] arr) {
        IntStream stream = Arrays.stream(arr);
        return stream.parallel().anyMatch(PrimeNumber::checkNotPrime);
    }
}
