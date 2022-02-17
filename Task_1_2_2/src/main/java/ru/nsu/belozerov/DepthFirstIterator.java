package ru.nsu.belozerov;

import java.util.*;

/**
 * Allows traverse the tree depth first.
 *
 * @param <T> - type of the tree
 */
public class DepthFirstIterator<T> implements Iterator<Node<T>> {
    private final Set<Node<T>> visited = new HashSet<>();
    private final Stack<Iterator<Node<T>>> stack = new Stack<>();
    private Node<T> next;
    private final Tree<T> tree;
    private final int size;

    /**
     * Initialising creates the stack that will be used in the traversing.
     * It also remembers the three and its size.
     *
     * @param tree - the tree that the algorithm will traverse
     */
    public DepthFirstIterator(Tree<T> tree) {
        this.tree = tree;
        size = tree.getSize();
        stack.push(tree.getSetOfChildren().iterator());
        next = tree.getRoot();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check whether the tree can be traversed further.
     *
     * @return true - if you can, false - if not
     */
    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    /**
     * If tree can be traversed further, returns next node.
     *
     * @return next node
     */
    @Override
    public Node<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (size != tree.getSize()) {
            throw new ConcurrentModificationException();
        }
        try {
            visited.add(next);
            return next;
        } finally {
            this.advance();
        }
    }

    private void advance() {
        Iterator<Node<T>> neighbors = stack.peek();
        do {
            while (!neighbors.hasNext()) {
                stack.pop();
                if (stack.isEmpty()) {
                    next = null;
                    return;
                }
                neighbors = stack.peek();
            }

            next = neighbors.next();
        } while (visited.contains(next));
        stack.push(next.getChildren().iterator());
    }
}