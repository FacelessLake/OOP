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
        size = 1;
        node = new Node<>();
        node.setValue(value);
    }

    private Tree(Node<T> root, int size) {
        this.size = size;
        node = root;
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

    private Node<T> get(Node<T> root, T target) {
        for (Node<T> child : root.getChildren()) {
            if (child.getValue() == target) {
                return child;
            }
        }
        for (Node<T> child : root.getChildren()) {
            Node<T> subTree = get(child, target);
            if (subTree != null) {
                return subTree;
            }
        }
        return null;
    }

    private int countSubtreeSize(Node<T> root, int counter) {
        counter += root.getChildren().size();
        for (Node<T> child : root.getChildren()) {
            countSubtreeSize(child, counter);
        }
        return counter;
    }

    /**
     * Allows getting the subtree of the current tree
     *
     * @param target - value of the node, that will become root of your new subtree
     * @return the subtree
     */
    public Tree<T> getSubtree(T target) {
        if (node == null) {
            throw new NoSuchElementException();
        }
        Node<T> root = get(node, target);
        if (root == null) {
            throw new NoSuchElementException();
        }
        int size = countSubtreeSize(root, 1);
        return new Tree<>(root, size);
    }

    /**
     * Allows getting the node of the current tree by the given name
     *
     * @param target - name of the node you want to get
     * @return the node itself
     */
    public Node<T> getNode(T target) {
        if (node == null) {
            throw new NoSuchElementException();
        }
        Node<T> root = get(node, target);
        if (root == null) {
            throw new NoSuchElementException();
        }
        return get(node, target);
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

    /**
     * Allows traversing the tree breadth first
     *
     * @return tree iterator
     */
    public Iterator<Node<T>> iterateBreadthFirst() {
        return new BreadthFirstIterator<>(this);
    }

    /**
     * Allows traversing the tree depth first
     *
     * @return tree iterator
     */
    public Iterator<Node<T>> iterateDepthFirst() {
        return new DepthFirstIterator<>(this);
    }
}