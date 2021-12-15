package ru.nsu.belozerov;

import java.util.*;

public class BreadthFirstIterator<T> implements Iterator<Node<T>> {
    private final Set<Node<T>> visited = new HashSet<>();
    private final Queue<Node<T>> queue = new LinkedList<>();
    private final Tree<T> tree;

    public BreadthFirstIterator(Tree<T> tree, Node<T> startingVertex) {
            this.tree = tree;
            queue.add(startingVertex);
            visited.add(startingVertex);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Node<T> next() {
        if (!hasNext())
            throw new NoSuchElementException();
        Node<T> next = queue.remove();
        for (Node<T> child : tree.getChildren()) {
            if (!this.visited.contains(child)) {
                this.queue.add(child);
                this.visited.add(child);
            }
        }
        return next;
    }
}