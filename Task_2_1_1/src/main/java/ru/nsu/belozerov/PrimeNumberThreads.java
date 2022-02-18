package ru.nsu.belozerov;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PrimeNumberThreads {
    public boolean checkPrimeThreads(int[] array) throws ExecutionException, InterruptedException {
        Callable<Boolean> task = () -> {
            PrimeNumber pn = new PrimeNumber();
            return pn.checkArray(array);
        };
        FutureTask<Boolean> future = new FutureTask<>(task);

        Thread[] threads = new Thread[5];
        for (int i = 1; i < 5; i++) {
            threads[i] = new Thread(future);
            threads[i].start();
        }
        return future.get();
    }
}
