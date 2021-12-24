package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumTreeTest {

    Tree<Integer> tree;

    @BeforeEach
    void myTree() {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        tree.add(8, 9);
        tree.add(8, 10);
        tree.add(8, 11);
    }

    @Test
    void removeNode() {
        tree.remove(3);
        assertEquals(tree.getSetOfChildren().size(), 1);
    }

    @Test
    void removeNode_alreadyDeleted() {
        tree.remove(7);
        assertFalse(tree.remove(7));
    }

    @Test
    void removeNode_deletedParent() {
        tree.remove(8);
        assertFalse(tree.remove(9));
    }

    @Test
    void addNode() {
        tree.add(1,99);
        assertEquals(tree.getSetOfChildren().size(), 3);
    }

    @Test
    void addNode_nonExistingParent() {
        tree.remove(8);
        assertFalse(tree.add(8,0));
    }

    @Test
    void addNode_sameName() {
        assertFalse(tree.add(8,9));
    }
}