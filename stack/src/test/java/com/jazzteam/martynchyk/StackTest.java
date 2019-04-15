package com.jazzteam.martynchyk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EmptyStackException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StackTest {

    private Object object = new Object();
    private Stack stack;

    @BeforeMethod
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testPush() {
        stack.push(object);
        assertEquals(stack.peek(), object);
    }

    @Test
    public void testPop() {
        stack.push(object);
        assertEquals(stack.pop(), object);
        stack.push(new Object());
        assertTrue(!stack.peek().equals(object));
    }

    @Test(expectedExceptions = EmptyStackException.class)
    public void testPopIfEmptyTrows() {
        stack.peek();
    }

    @Test
    public void testPeek() throws EmptyStackException {
        stack.push(object);
        assertEquals(stack.peek(), object);
        assertEquals(stack.peek(), object);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void negativeTestIsEmpty() {
        stack.push(new Object());
        assertTrue(!stack.isEmpty());
    }
}