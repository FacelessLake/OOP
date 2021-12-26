package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NumTreeTest {

    Tree<Integer> tree;
    ArrayList<Integer> output;

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
        output = new ArrayList<>();
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
        tree.add(1, 99);
        assertEquals(tree.getSetOfChildren().size(), 3);
    }

    @Test
    void addNode_nonExistingParent() {
        tree.remove(8);
        assertFalse(tree.add(8, 0));
    }

    @Test
    void addNode_sameName() {
        assertFalse(tree.add(8, 9));
    }

    @Test
    void getSize() {
        assertEquals(11, tree.getSize());
    }

    @Test
    void getSubtree() {
        Tree<Integer> newTree = tree.getSubtree(8);
        assertEquals(4, newTree.getSize());
    }

    @Test
    void getSubtree_noSuchNode() {
        assertThrows(NoSuchElementException.class, () -> tree.getSubtree(200));
    }

    @Test
    void getNode() {
        Integer target = 5;
        assertEquals(target, tree.getNode(target).getValue());
    }

    @Test
    void getNode_noSuchNode() {
        assertThrows(NoSuchElementException.class, () -> tree.getNode(200));
    }

    @Test
    void BreadthFirstIteratorTest() {
        Iterator<Node<Integer>> iterator = tree.iterateBreadthFirst();
        while (iterator.hasNext()) {
            output.add(iterator.next().getValue());
        }
        System.out.println(output.toString());
        assertTrue(output.indexOf(3) < output.indexOf(11));
    }

    @Test
    void DepthFirstIteratorTest() {
        Iterator<Node<Integer>> iterator = tree.iterateDepthFirst();
        while (iterator.hasNext()) {
            output.add(iterator.next().getValue());
        }
        System.out.println(output.toString());
        assertTrue(output.indexOf(2) < output.indexOf(6));
    }
}