package ru.nsu.belozerov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class App {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        String filename = in.nextLine();
        File file = new File(filename);

        try {
            Reader input1 = new FileReader(filename);
            FindSubstring fs = new FindSubstring();
            fs.find(input1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
