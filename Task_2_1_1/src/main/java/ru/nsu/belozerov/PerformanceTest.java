package ru.nsu.belozerov;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Class for function performance testing
 */
public class PerformanceTest {
    /**
     * Tests how fast each function work on full-prime array of big numbers
     *
     * @throws InterruptedException – if the current thread was interrupted while waiting
     * @throws ExecutionException   – if the computation threw an exception
     */
    static public void PrimeNumberPerformanceTest() throws InterruptedException, ExecutionException {

        int[] arr = new int[1000];
        Arrays.fill(arr, 6997901);

        List<Long> sequenceResults = new ArrayList<>();
        List<Long> parallelStreamResults = new ArrayList<>();
        int threadsAvailable = Runtime.getRuntime().availableProcessors();
        Long[] threadsResults = new Long[threadsAvailable + 1];

        for (int i = 1; i <= threadsAvailable; i++) {
            List<Long> results = new ArrayList<>();
            for (int j = 0; j < 3; ++j) {
                long time = System.nanoTime();
                PrimeNumberThreads.checkPrimeThreads(arr, i);
                time = System.nanoTime() - time;
                results.add(time);
            }
            threadsResults[i] = Collections.min(results);
        }

        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            PrimeNumber.checkArray(arr);
            time = System.nanoTime() - time;
            sequenceResults.add(time);

            time = System.nanoTime();
            PrimeNumberParallelStream.parallelCheck(arr);
            time = System.nanoTime() - time;
            parallelStreamResults.add(time);
        }
        long sequence = Collections.min(sequenceResults);
        long parallelStream = Collections.min(parallelStreamResults);

        System.out.println("Sequential: " + sequence);
        System.out.println("On Parallel Streams: " + parallelStream);
        System.out.println("Using threads:");
        for (int i = 1; i <= threadsAvailable; i++) {
            System.out.println(i + ": " + threadsResults[i]);
        }
    }
}
