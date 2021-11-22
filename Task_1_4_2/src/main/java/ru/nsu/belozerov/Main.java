package ru.nsu.belozerov;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Notebook nb = new Notebook();
        boolean flag = true;
        while (flag) {
            Scanner in = new Scanner(System.in);
            String[] input = in.nextLine().split(" ");

            switch (input[0]) {
                case "-quit":{
                    flag = false;
                    break;
                }
                case "-add":
                    if (validateParameters(input, 3)) {
                        nb.add(input[1],input[2]);
                    }
                    break;
                case "-showall":
                    if (validateParameters(input, 1)) {
                        nb.show();
                    }
                case "-show":
                    if (validateParameters(input, 4)) {
                        nb.show(input[1], input[2], input[3]);
                    }
                    break;
                case "-rm":
                    if (validateParameters(input, 2)) {
                        nb.remove(input[1]);
                    }
                    break;
                default:
                    System.out.printf("Unexpected param %s\n", input[0]);
                    break;
            }
        }

    }

    public static boolean validateParameters(String[] input, int expected) {
        if (input.length < expected) {
            System.out.println("parameters expected");
            return false;
        }
        return true;
    }
}
