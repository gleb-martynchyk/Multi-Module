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
    }

    @Test
    public void testPopIsRemoved() {
        stack.push(new Object());
        stack.push(object);
        stack.pop();
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
    }

    @Test
    public void testPeekNotRemovedObject() throws EmptyStackException {
        stack.push(object);
        stack.peek();
        assertEquals(stack.peek(), object);
    }

    @Test(expectedExceptions = EmptyStackException.class)
    public void testPeekNegative() throws EmptyStackException {
        stack.peek();
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