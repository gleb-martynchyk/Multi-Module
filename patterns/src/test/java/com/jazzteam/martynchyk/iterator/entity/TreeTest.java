package com.jazzteam.martynchyk.iterator.entity;

import com.jazzteam.martynchyk.iterator.TreeIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class TreeTest {

    private static Logger log = LogManager.getLogger(TreeTest.class);
    private Tree<Integer> tree;

    @BeforeMethod
    public void setUp() {
        tree = new Tree<>(0);
    }

    @Test
    public void createThree() {
        Tree<Integer> expected = new Tree<>(33);
        tree = new Tree<>(33);
        assertEquals(tree, expected);
    }

    @Test
    public void createThree1() {
        Tree<Integer> expected = new Tree<>(10);
        tree = new Tree<>(10);
        expected.addChild(11);
        tree.addChild(11);
        assertEquals(tree, expected);
    }

    @Test
    public void createThreeNegative() {
        Tree<Integer> expected = new Tree<>(10);
        tree = new Tree<>(10);
        expected.addChild(11);
        tree.addChild(12);
        tree.addChild(12);
        assertNotEquals(tree, expected);
    }

    @Test
    public void testIteratorIsTreeIterator() {
        assertEquals(tree.iterator().getClass(), TreeIterator.class);
    }

    @Test
    public void testAddChild() {
        Tree<Integer> newTree = new Tree<>(0);
        List<Tree.Node> expected = new ArrayList<>();
        expected.add(new Tree.Node(0, newTree.getRoot()));
        expected.add(new Tree.Node(1, newTree.getRoot()));
        expected.add(new Tree.Node(2, newTree.getRoot()));
        for (Tree.Node num : expected) {
            tree.addChild((Integer) num.getData());
        }
        assertEquals(tree.getRoot().getChildren(), expected);
    }

    @Test
    public void testAddChildSecondLevel() {
        Tree<Integer> newTree = new Tree<>(0);
        Tree.Node node0 = new Tree.Node(0, newTree.getRoot());
        Tree.Node nodeActual = tree.addChild(0);
        List<Tree.Node> expected = new ArrayList<>();
        expected.add(new Tree.Node(0, node0));
        expected.add(new Tree.Node(1, node0));
        expected.add(new Tree.Node(2, node0));
        for (Tree.Node num : expected) {
            nodeActual.addChild((Integer) num.getData());
        }
        assertEquals(nodeActual.getChildren(), expected);
    }
}