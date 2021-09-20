package ru.nsu.belozerov;

public class HeapSort {

    private static int[] heap;
    private static int[] arr;
    private static int size;
    private static int index = 0;

    public HeapSort(int[] array, int sz){
        size = sz;
        arr = new int[size];
        arr = array;
        heap = new int[size];
    }

    public int getElement(int index){
        return arr[index];
    }

    private void Swap(int a, int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b]=temp;
    }

    private void Add(int i){
        int left = 2*index+1;
        int right = 2*index+2;

        if (heap[left] == 0){
            heap[left]=arr[i];
            SiftUp(left);
        }
        else {
            heap[right]=arr[i];
            SiftUp(right);
            index++;
        }
    }

    private void SiftUp(int i){
        if (heap[i] < heap[(i-1)/2]) {
            Swap(i,(i-1)/2);
            SiftUp((i-1)/2);
        }
    }

    private void SiftDown(int i){
        int left = 2*i+1;
        int right = 2*i+2;

        if (left>size){
            return;
        }

        if (heap[left] < heap[right] | right>size){
            if(heap[left] < heap[i]){
                Swap(i, left);
                SiftDown(left);
            }
        }
        else {
            if (right>size){
                return;
            }
            if(heap[right] < heap[i]){
                Swap(i, right);
                SiftDown(right);
            }
        }
    }

    public void Sort(){

        heap[index] = arr[0];

        for(int i=1;i<size;i++){
            Add(i);
        }
        int end = --size;
        while (size >= 0){
            Swap(0,size);
            arr[end-size]=heap[size];
            size--;
            SiftDown(0);
        }
    }
}
