package ru.nsu.belozerov;

import java.util.*;

/**
 * This is tree, that can have any number of descendants at any level.
 *
 * @param <T> - type of the tree and its nodes
 */
public class Tree<T> {

    private final Node<T> node;
    private int size;

    /**
     * When tree is initialised, it creates the node and set to it given value.
     *
     * @param value - value of the root
     */
    public Tree(T value) {
        size = 0;
        node = new Node<>();
        node.setValue(value);
    }

    /**
     * Allows to get the root of the tree.
     *
     * @return first node of the tree, known as "root"
     */
    public Node<T> getRoot() {
        return node;
    }

    private boolean addNode(Node<T> root, T newChild, T parent) {
        if (root == null) {
            return false;
        }
        if (root.getValue().equals(parent)) {
            for (Node<T> child : root.getChildren()) {
                if (child.getValue() == newChild) {
                    return false;
                }
            }
            Node<T> newNode = new Node<>();
            newNode.setValue(newChild);
            root.addChild(newNode);
            size++;
            return true;
        }
        for (Node<T> child : root.getChildren()) {
            if (addNode(child, newChild, parent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Allows to create the new node and add it to the tree.
     *
     * @param parent   - name of the node receiving the child
     * @param newChild - name of the new child
     * @return true - if addition was successful, false - in other cases
     */
    public boolean add(T parent, T newChild) {
        return addNode(node, newChild, parent);
    }

    /**
     * Allows to get the children of the current node.
     *
     * @return set of the children
     */
    public Set<Node<T>> getSetOfChildren() {
        Set<Node<T>> children = node.getChildren();
        if (children == null || children.isEmpty()) {
            return Collections.emptySet();
        } else {
            return children;
        }
    }

    private boolean removeNode(Node<T> root, T target) {
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
        size--;
        return true;
    }

    /**
     * Allows to delete the node with the given name, if it exists.
     *
     * @param target - name of the node you want to delete
     * @return true - if removal was successful, false - in other cases
     */
    public boolean remove(T target) {
        return removeNode(node, target);
    }

    /**
     * Allows getting the number of the nodes in the tree.
     *
     * @return size of the tree
     */
    public int getSize() {
        return size;
    }
}