package ru.nsu.belozerov;

public class PrimeNumber {

    private boolean checkNotPrime(int number) {
        boolean answer = false;
        if (number == 0 || number == 1) {
            answer = true;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }

    public boolean checkArray(int[] array) {
        for (int j : array) {
            if (checkNotPrime(j)) {
                return true;
            }
        }
        return false;
    }
}
