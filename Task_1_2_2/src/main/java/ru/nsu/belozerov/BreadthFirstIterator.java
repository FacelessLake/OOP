package ru.nsu.belozerov;

import java.util.*;

/**
 * Allows traverse the tree breadth first.
 *
 * @param <T> - type of the tree
 */
public class BreadthFirstIterator<T> implements Iterator<Node<T>> {
    private final Set<Node<T>> visited = new HashSet<>();
    private final Queue<Node<T>> queue = new LinkedList<>();
    private final Tree<T> tree;
    private final int size;

    /**
     * Initialising creates the queue for the nodes and the set for the visited ones, add root to both then.
     * It also remembers the three and its size.
     *
     * @param tree - the tree that the algorithm will traverse
     */
    public BreadthFirstIterator(Tree<T> tree) {
        this.tree = tree;
        size = tree.getSize();
        queue.add(tree.getRoot());
        visited.add(tree.getRoot());
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
        return !queue.isEmpty();
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
        Node<T> next = queue.remove();
        for (Node<T> child : next.getChildren()) {
            if (!this.visited.contains(child)) {
                this.queue.add(child);
                this.visited.add(child);
            }
        }
        return next;
    }
}