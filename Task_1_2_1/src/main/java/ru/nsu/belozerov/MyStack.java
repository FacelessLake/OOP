package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
    private int cnt;
    private T[] arr;

    @SuppressWarnings("unchecked")
    public MyStack() {
        cnt = 0;
        arr = (T[]) new Object[cnt];
    }

    public void push(T item) {
        if (cnt == arr.length) {
            arr = Arrays.copyOf(arr, (cnt + 1) * 2);
        }
        arr[cnt++] = item;
    }

    public T pop() {
        if (cnt == 0) {
            throw new EmptyStackException();
        }
        return arr[--cnt];
    }

    public int count() {
        return cnt;
    }

    public void pushStack(MyStack<T> stack) {
        int stackLen = stack.count();
        arr = Arrays.copyOf(arr, cnt + stackLen);
        System.arraycopy(stack.arr, 0, arr, cnt, stackLen);
        cnt += stackLen;
    }

    @SuppressWarnings("unchecked")
    public MyStack<T> popStack(int elemCnt) {
        if (cnt < 0 || elemCnt > cnt) {
            throw new IndexOutOfBoundsException();
        }
        MyStack<T> answerStack = new MyStack<>();
        answerStack.arr = (T[]) new Object[elemCnt];
        answerStack.cnt = elemCnt;
        cnt -= elemCnt;
        System.arraycopy(arr, cnt, answerStack.arr, 0, elemCnt);
        arr = Arrays.copyOf(arr, cnt);
        return answerStack;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int iter = 0;
            @Override
            public boolean hasNext() {
                return iter != cnt;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return arr[iter++];
                } else {
                    throw new EmptyStackException();
                }
            }
        };
    }
}