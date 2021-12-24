package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTreeTest {

    Tree<String> tree;

    @BeforeEach
    void myTree() {
        tree = new Tree<>("A");
        tree.add("A", "B");
        tree.add("A", "C");
        tree.add("B", "D");
        tree.add("B", "E");
        tree.add("B", "F");
        tree.add("C", "G");
        tree.add("C", "H");
        tree.add("H", "I");
        tree.add("H", "J");
        tree.add("H", "K");
    }

    @Test
    void removeNode() {
        tree.remove("C");
        assertEquals(tree.getSetOfChildren().size(), 1);
    }

    @Test
    void removeNode_alreadyDeleted() {
        tree.remove("G");
        assertFalse(tree.remove("G"));
    }

    @Test
    void removeNode_deletedParent() {
        tree.remove("H");
        assertFalse(tree.remove("I"));
    }

    @Test
    void addNode() {
        tree.add("A","Z");
        assertEquals(tree.getSetOfChildren().size(), 3);
    }

    @Test
    void addNode_nonExistingParent() {
        tree.remove("H");
        assertFalse(tree.add("H","O"));
    }

    @Test
    void addNode_sameName() {
        assertFalse(tree.add("H","I"));
    }
}