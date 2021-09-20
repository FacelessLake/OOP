package ru.nsu.belozerov;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[] array = new  int[] {2, 2, 2};
        int size = array.length;

        HeapSort hs = new HeapSort(array, size);
        hs.Sort();

        for(int i=0;i<size;i++){
            array[i]=(hs.getElement(i));
        }

        System.out.print(Arrays.toString(array));
    }
}
