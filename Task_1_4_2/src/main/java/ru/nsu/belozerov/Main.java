package ru.nsu.belozerov;

public class Main {
    public static void main(String[] args) {
        String[] args2 = new String[2];
        args2[0] = "-rm";
        args2[1] = "memes1";
//        args2[0] = "-add";
//        args2[2] = "want to try some more memes";
//        args2[0] = "-show";
        ConsoleReader cr = new ConsoleReader();
        cr.run(args2);
    }
}
