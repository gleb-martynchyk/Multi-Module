package com.jazzteam.martynchyk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EmptyStackException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StackTest {

    private Object object;
    private Stack stack;

    @BeforeMethod
    public void setUp() {
        object = new Object();
        stack = new Stack();
    }

    @Test
    public void testPush() throws StackOverflowError {
        stack.push(object);
        assertEquals(stack.peek(), object);
    }

    @Test
    public void testPop() throws EmptyStackException {
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
    public void testEmpty() {
        assertTrue(stack.empty());
    }

    @Test
    public void negativeTestEmpty() {
        stack.push(new Object());
        assertTrue(!stack.empty());
    }
}