package ru.nsu.belozerov;

import java.util.*;
import java.util.concurrent.*;
/**
 * Class for prime number check using threads
 */
public class PrimeNumberThreads {
    /**
     * Checks if an array contains a number that is NOT prime using threads
     * @param array - array you want to check
     * @return true - if it contains NOT prime number, false - otherwise
     * @throws InterruptedException – if the current thread was interrupted while waiting
     * @throws ExecutionException – if the computation threw an exception
     */
    static public boolean checkPrimeThreads(int[] array) throws InterruptedException, ExecutionException {
        Deque<Integer> deque = new ArrayDeque<>(Arrays.stream(array).boxed().toList());
        Callable<Boolean> task = () -> {
            if (!deque.isEmpty()) {
                return PrimeNumber.checkNotPrime(deque.pop());
            }
            return false;
        };

        int threadsAvailable = Runtime.getRuntime().availableProcessors();
        List<Callable<Boolean>> taskList = Collections.nCopies(threadsAvailable, task);

        ExecutorService threads = Executors.newFixedThreadPool(threadsAvailable);
        List<Future<Boolean>> results = threads.invokeAll(taskList);
        for (Future<Boolean> result : results) {
            if (result.get()) {
                threads.shutdownNow();
                return true;
            }
        }
        threads.shutdownNow();
        return false;
    }
}