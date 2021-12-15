package ru.nsu.belozerov;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {
    private T value;
    private final Set<Node<T>> children = new HashSet<>();

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public Set<Node<T>> getChildren() {
        return children;
    }

    public void removeChild(Node<T> child) {
        children.remove(child);
    }
}
