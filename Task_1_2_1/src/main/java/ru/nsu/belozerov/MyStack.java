package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
    private int cnt;
    private T[] arr;
    private int size;

    public MyStack() {
        size = 0;
        arr = (T[]) new Object[size];
        cnt = 0;
    }

    public void push(T item) {
        arr = Arrays.copyOf(arr, ++size);
        arr[cnt++] = item;
    }

    public T pop() {
        if (cnt == 0) {
            throw new EmptyStackException();
        }
        return arr[cnt--];
    }

    public int count() {
        return cnt;
    }

    public void pushStack(MyStack<T> stack) {
        int stackLen = stack.arr.length;
        size += stackLen;
        arr = Arrays.copyOf(arr, size);
        System.arraycopy(stack.arr, 0, arr, cnt, stackLen);
        cnt += stackLen;
    }

    public MyStack<T> popStack(int elemCnt) {
        if (size < 0 || elemCnt > size) {
            throw new IndexOutOfBoundsException();
        }
        MyStack<T> answStack = new MyStack<T>();
        cnt -= elemCnt;
        System.arraycopy(arr, cnt, answStack.arr, 0, elemCnt);
        return (answStack);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return cnt != -1;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return arr[cnt++];
                } else {
                    throw new EmptyStackException();
                }
            }
        };
    }
}