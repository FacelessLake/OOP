package ru.nsu.belozerov;

import java.util.*;

public class Tree<T> {

    private final Node<T> node;

    public Tree(T value) {
        node = new Node<>();
        node.setValue(value);
    }

    public boolean addNode(Node<T> root, T newChild, T parent) {
        if (root == null) {
            return false;
        }
        if (root.getValue().equals(parent)) {
            Node<T> newNode = new Node<>();
            newNode.setValue(newChild);
            root.addChild(newNode);
            return true;
        }
        for (Node<T> child : root.getChildren()) {
            if (addNode(child, newChild, parent)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(T parent, T newChild) {
        return addNode(node, newChild, parent);
    }

    public Iterable<Node<T>> getChildren() {
        Set<Node<T>> children = node.getChildren();
        if (children == null || children.isEmpty()) {
            return Collections.emptySet();
        } else {
            return children;
        }
    }

    public boolean removeNode(Node<T> root, T target) {
        if (root == null) {
            return false;
        }
        Node<T> childToRemove = null;
        for (Node<T> child : root.getChildren()) {
            if (child.getValue().equals(target)) {
                childToRemove = child;
                break;
            } else {
                if (removeNode(child, target)) {
                    return true;
                }
            }
        }
        if (childToRemove == null) {
            return false;
        }
        root.removeChild(childToRemove);
        return true;
    }

    public boolean remove(T target) {
        return removeNode(node, target);
    }
}