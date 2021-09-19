package ru.nsu.sbelozerov;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N];

        for (int i=0;i<N;i++){
            arr[i] = in.nextInt();
        }
        in.close();
        Heapsort hs = new Heapsort();
        hs.Hsort(arr, N);
        System.out.printf(Arrays.toString(arr));
        return;
    }
}
