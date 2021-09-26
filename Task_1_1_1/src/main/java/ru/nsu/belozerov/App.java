package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();
        String[] string = str.replaceAll("\\[", "").replaceAll("]", "").split(",");

        int[] arr = new int[string.length];

        for (int i = 0; i < string.length; i++) {
            arr[i] = Integer.valueOf(string[i]);
        }

        HeapSort hs = new HeapSort();
        hs.sort(arr);

        System.out.print(Arrays.toString(arr));
    }
}
