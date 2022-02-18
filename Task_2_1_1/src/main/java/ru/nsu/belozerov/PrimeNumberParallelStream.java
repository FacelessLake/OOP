package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PrimeNumberParallelStream {
    public boolean parallelCheck(int[] arr) {
        PrimeNumber pn = new PrimeNumber();
        IntStream stream = Arrays.stream(arr);
        return stream.parallel().anyMatch(pn::checkNotPrime);
    }
}
