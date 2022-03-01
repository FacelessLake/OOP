package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PrimeNumberThreads {

    static public boolean checkPrimeThreads(Integer[] array) throws ExecutionException, InterruptedException {
        Stack<Integer> stack = new Stack<>();
        Arrays.stream(array).forEach(stack::push);

        Callable<Boolean> task = () -> PrimeNumber.checkNotPrime(stack.pop());
        FutureTask<Boolean> future = new FutureTask<>(task);

        int threadsAvailable = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[threadsAvailable];
        for (int i = 1; i < threadsAvailable; i++) {
            threads[i] = new Thread(future);
            threads[i].start();
        }
        return future.get();
    }
}
