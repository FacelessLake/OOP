package ru.nsu.belozerov;

import java.util.*;
import java.util.concurrent.*;

/**
 * Class for prime number check using threads
 */
public class PrimeNumberThreads {

    /**
     * Checks if an array contains a number that is NOT prime using amount of threads given
     *
     * @param array         - array you want to check
     * @param threadsAmount - how many threads will be used
     * @return true - if it contains NOT prime number, false - otherwise
     * @throws InterruptedException – if the current thread was interrupted while waiting
     * @throws ExecutionException   – if the computation threw an exception
     */
    static public boolean checkPrimeThreads(int[] array, int threadsAmount) throws InterruptedException, ExecutionException {
//        if (threadsAmount < 1) {
//            throw new IllegalArgumentException();
//        }

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>(Arrays.stream(array).boxed().toList());
        Callable<Boolean> task = () -> {
            if (!queue.isEmpty()) {
                return PrimeNumber.checkNotPrime(queue.poll());
            }
            return false;
        };

        List<Callable<Boolean>> taskList = Collections.nCopies(threadsAmount, task);

        ExecutorService threads = Executors.newFixedThreadPool(threadsAmount);
        List<Future<Boolean>> results = threads.invokeAll(taskList);
        boolean answer = false;
        for (Future<Boolean> result : results) {
            if (result.get()) {
                answer = true;
                break;
            }
        }
        threads.shutdownNow();
        return answer;
    }

    /**
     * Checks if an array contains a number that is NOT prime using maximum amount of threads available
     *
     * @param array - array you want to check
     * @return true - if it contains NOT prime number, false - otherwise
     * @throws InterruptedException – if the current thread was interrupted while waiting
     * @throws ExecutionException   – if the computation threw an exception
     */
    static public boolean checkPrimeThreads(int[] array) throws InterruptedException, ExecutionException {
        int threadsAvailable = Runtime.getRuntime().availableProcessors();
        return checkPrimeThreads(array, threadsAvailable);
    }
}