package ru.nsu.belozerov;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
    int cnt;
    T[] arr;

    @SuppressWarnings("unchecked")
    public MyStack() {
        cnt = 0;
        arr = (T[]) new Object[cnt];
    }

    public void push(T item) {
        arr = Arrays.copyOf(arr, cnt + 1);
        arr[cnt++] = item;
    }

    public T pop() {
        if (cnt == 0) {
            throw new EmptyStackException();
        }
        T top = arr[--cnt];
        arr = Arrays.copyOf(arr, cnt);
        return top;
    }

    public int count() {
        return cnt;
    }

    public void pushStack(MyStack<T> stack) {
        int stackLen = stack.arr.length;
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
        return (answerStack);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
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