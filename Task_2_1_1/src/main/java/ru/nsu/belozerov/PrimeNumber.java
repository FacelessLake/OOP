package ru.nsu.belozerov;

public class PrimeNumber {

    private boolean checkPrime(int number) {
        boolean answer = true;
        if (number == 0 || number == 1) {
            answer = false;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }

    public boolean checkArray(int[] array){
        for (int j : array) {
            if (checkPrime(j)) {
                return true;
            }
        }
        return false;
    }
}
