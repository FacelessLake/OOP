package ru.nsu.sbelozerov;
import java.util.Scanner;
import java.lang.String;


public class Heapsort {

    public void ExtractMax(int arr[], int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[max])
            max = left;

        if (right < n && arr[right] > arr[max])
            max = right;

        if (max != i) {
            int temp = arr[i];
            arr[max] = arr[i];
            arr[i] = temp;
            ExtractMax(arr, n, max);
        }
    }


    public void Add(int arr[], int i) {
        int temp;
        while (arr[i] < arr[(i - 1) / 2] && i > 0) {
            temp = arr[(i - 1) / 2];
            arr[(i - 1) / 2] = arr[i];
            arr[i] = temp;
            i = (i - 1) / 2;
        }
    }


    public void Hsort(int[] arr, int n){

        for (int i = 0; i < n; i++) {
            Add(arr, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            ExtractMax(arr, i, 0);
        }
    }
}

