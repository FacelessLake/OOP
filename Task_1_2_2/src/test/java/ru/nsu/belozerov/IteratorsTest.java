package ru.nsu.belozerov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorsTest {
    Tree<String> tree;
    BreadthFirstIterator<String> breadthFirst;
    DepthFirstIterator<String> depthFirst;
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
        breadthFirst = new BreadthFirstIterator<>(tree);
        depthFirst = new DepthFirstIterator<>(tree);
        output = new ArrayList<>();
    }

    @Test
    void BreadthFirstIteratorTest(){
        while (breadthFirst.hasNext()){
            output.add(breadthFirst.next().getValue());
        }
        System.out.println(output.toString());
        assertTrue(output.indexOf("B") < output.indexOf("E"));
    }

    @Test
    void DepthFirstIteratorTest(){
        while (depthFirst.hasNext()){
            output.add(depthFirst.next().getValue());
        }
        System.out.println(output.toString());
        assertTrue(output.indexOf("B") < output.indexOf("E"));
    }
}
