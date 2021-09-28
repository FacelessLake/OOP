package ru.nsu.belozerov;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[] str = in.nextLine().toCharArray();
        char[] str2 = in.nextLine().toCharArray();

        FindSubstring fs = new FindSubstring();
        fs.find(str,str2);
    }
}
