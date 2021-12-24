package ru.nsu.belozerov;

import java.util.*;

public class DepthFirstIterator<T> implements Iterator<Node<T>> {
    private final Set<Node<T>> visited = new HashSet<>();
    private final Stack<Iterator<Node<T>>> stack = new Stack<>();
    private final Tree<T> tree;
    private Node<T> next;

    public DepthFirstIterator(Tree<T> tree) {
        stack.push(tree.getSetOfChildren().iterator());
        this.tree = tree;
        next = tree.getRoot();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public Node<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
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