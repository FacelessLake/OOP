package ru.nsu.belozerov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String filename = in.nextLine();
        File file = new File(filename);
        char[] str = new char[5000];

        try {
            FileReader input1 = new FileReader(filename);
            input1.read(str);
            input1.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        char[] str2 = in.nextLine().toCharArray();

        FindSubstring fs = new FindSubstring();
        fs.find(str2, str);
    }
}
