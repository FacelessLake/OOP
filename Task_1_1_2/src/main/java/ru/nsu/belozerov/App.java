package ru.nsu.belozerov;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String filename = in.nextLine();
        char[] pattern = in.nextLine().toCharArray();
        ArrayList<Integer> answ = new ArrayList<>(0);

        try {
            Reader input1 = new FileReader(filename);
            FindSubstring fs = new FindSubstring();
            answ = fs.find(input1, pattern);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (answ.isEmpty()) {
            answ.add(-1);
        }

        System.out.println(answ);
    }
}
