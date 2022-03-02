package ru.nsu.belozerov;

/**
 * Class for prime number check
 */
public class PrimeNumber {

    /**
     * Checks if a number is NOT prime
     *
     * @param number - the number you want to check
     * @return true - if it is NOT prime, false - otherwise
     */
    static public boolean checkNotPrime(int number) {
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

    /**
     * Checks if an array contains a number that is NOT prime
     *
     * @param array - array you want to check
     * @return true - if it contains NOT prime number, false - otherwise
     */
    static public boolean checkArray(int[] array) {
        for (int j : array) {
            if (checkNotPrime(j)) {
                return true;
            }
        }
        return false;
    }
}
