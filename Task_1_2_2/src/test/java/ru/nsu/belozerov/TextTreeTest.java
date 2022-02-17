package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TextTreeTest {

    Tree<String> tree;
    ArrayList<String> output;

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
        output = new ArrayList<>();
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
        tree.add("A", "Z");
        assertEquals(tree.getSetOfChildren().size(), 3);
    }

    @Test
    void addNode_nonExistingParent() {
        tree.remove("H");
        assertFalse(tree.add("H", "O"));
    }

    @Test
    void addNode_sameName() {
        assertFalse(tree.add("H", "I"));
    }

    @Test
    void getSize() {
        assertEquals(11, tree.getSize());
    }

    @Test
    void getSubtree() {
        Tree<String> newTree = tree.getSubtree("H");
        assertEquals(4, newTree.getSize());
    }

    @Test
    void getSubtree_noSuchNode() {
        assertThrows(NoSuchElementException.class, () -> tree.getSubtree("P"));
    }

    @Test
    void getNode() {
        String target = "I";
        assertEquals(target, tree.getNode(target).getValue());
    }

    @Test
    void getNode_noSuchNode() {
        assertThrows(NoSuchElementException.class, () -> tree.getNode("P"));
    }

    @Test
    void BreadthFirstIteratorTest() {
        Iterator<Node<String>> iterator = tree.iterateBreadthFirst();
        while (iterator.hasNext()) {
            output.add(iterator.next().getValue());
        }
        System.out.println(output.toString());
        assertTrue(output.indexOf("B") < output.indexOf("E"));
    }

    @Test
    void DepthFirstIteratorTest() {
        Iterator<Node<String>> iterator = tree.iterateDepthFirst();
        while (iterator.hasNext()) {
            output.add(iterator.next().getValue());
        }
        System.out.println(output.toString());
        assertTrue(output.indexOf("B") < output.indexOf("E"));
    }
}