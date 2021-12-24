package ru.nsu.belozerov;

import java.util.HashSet;
import java.util.Set;

/**
 * Node - is component of tree, witch has its value and set of children.
 *
 * @param <T> - type of the node
 */
public class Node<T> {
    private T value;
    private final Set<Node<T>> children = new HashSet<>();

    /**
     * Allows to get the value of the node.
     *
     * @return - value of the node
     */
    public T getValue() {
        return value;
    }

    /**
     * Allows to get the value of the node.
     *
     * @param value - value that you want to set on this node
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Checks if the node has the children.
     *
     * @return true if node has children, false - if not
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * Allows to add the node as a child to the current one.
     *
     * @param child - node you want to add
     */
    public void addChild(Node<T> child) {
        children.add(child);
    }

    /**
     * Allows to get all the children of the node as a set.
     *
     * @return set of children
     */
    public Set<Node<T>> getChildren() {
        return children;
    }

    /**
     * Allows to delete one of the children of the current node.
     *
     * @param child - child you want to delete
     */
    public void removeChild(Node<T> child) {
        children.remove(child);
    }
}
