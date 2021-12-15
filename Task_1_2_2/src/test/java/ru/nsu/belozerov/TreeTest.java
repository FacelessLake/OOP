package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    Tree<String> tree;

    @BeforeEach
    void myTree(){
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
    void testBreadthFirstIterator() {
        Node<String> node = new Node<>();
        assertEquals("","");
    }
}