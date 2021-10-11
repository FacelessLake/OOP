package ru.nsu.belozerov;

import java.util.EmptyStackException;
import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
    int size;
    T[] arr;

    public MyStack(T[] array) {
        size = -1;
        arr = array;
    }

    public T push(T item) {
        size++;
        arr[size] = item;
        return item;
    }

    public T peek() {
        if (size == -1)
            throw new EmptyStackException();
        return arr[size];
    }

    public T pop() {
        T answ = peek();
        size--;
        return answ;
    }

    public boolean empty() {
        return size == 0;
    }

    public int search(T obj) {
        int answ = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == obj)
                answ = i;
        }
        return answ;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return size != -1;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return arr[size];
                } else {
                    throw new EmptyStackException();
                }
            }
        };
    }
}

/*
  private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        elementCount = s + 1;
   }

  public synchronized void addElement(E obj) {
        modCount++;
        add(obj, elementData, elementCount);
  }

        public E push(E item) {
        addElement(item);

        return item;
    }
    }
*/