package com.jazzteam.martynchyk.iterator;

import com.jazzteam.martynchyk.iterator.entity.Tree;
import com.jazzteam.martynchyk.iterator.entity.TreeTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TreeIteratorTest {

    private static Logger log = LogManager.getLogger(TreeTest.class);

    private Tree<Integer> tree;
    private Iterator iterator;


    @BeforeMethod
    public void setUp() {
        tree = new Tree<>(0);
        iterator = tree.iterator();
    }

    @Test
    public void testHasNextWithOneNode() {
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNext() {
        tree.addChild(1);
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNextIdempotent() {
        tree.addChild(1);
        iterator.next();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNextNegative() {
        log.info(iterator.next());
        assertFalse(iterator.hasNext());
    }

    //TODO Осмысленные названия для тестов
    @Test
    public void testHasNextNegative1() {
        tree.addChild(2);
        iterator = tree.iterator();
        log.info(iterator.next());
        log.info(iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext() {
        Tree.Node node = null;
        tree.addChild(1);
        Tree.Node node0 = tree.addChild(2);
        node0.addChild(3);
        node0.addChild(4).addChild(5);
        while (iterator.hasNext()) {
            node = (Tree.Node) iterator.next();
        }
        assertEquals(node.getData(), 5);
    }

    @Test
    public void testNextPrint() {
        tree.addChild(1);
        Tree.Node node0 = tree.addChild(2);
        node0.addChild(3);
        node0.addChild(4).addChild(5);
        while (iterator.hasNext()) {
            log.info(iterator.next());
        }
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testNextNegative() {
        log.info(iterator.next());
        log.info(iterator.next());
    }

}