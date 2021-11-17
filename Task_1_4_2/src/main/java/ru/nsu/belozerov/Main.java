package ru.nsu.belozerov;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("parameters expected");
            return;
        }

        Notebook nb = new Notebook();
        switch (args[0]) {
            case "-add":
                if (validateParameters(args, 3)) {
                    nb.add(args[1]);
                }
                break;
            case "-show":
                if (validateParameters(args, 3)) {
                    nb.show(args[1], args[2], args[3]);
                }
                break;
            case "-rm":
                if (validateParameters(args, 2)) {
                    nb.remove(args[1]);
                }
                break;
            default:
                System.out.printf("Unexpected param %s\n", args[0]);
                break;
        }
    }

    public static boolean validateParameters(String[] args, int expected) {
        if (args.length < expected) {
            System.out.println("parameters expected");
            return false;
        }
        return true;
    }
}
