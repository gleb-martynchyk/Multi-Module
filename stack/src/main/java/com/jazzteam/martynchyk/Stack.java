package com.jazzteam.martynchyk;


import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private final static int MAX_SIZE = 256;
    private Object[] object;
    private int first;

    public Stack() {
        object = new Object[MAX_SIZE];
        first = -1;
    }

    public void push(Object o) {
        if (first >= MAX_SIZE) {
            throw new StackOverflowError();
        } else {
            first++;
            object[first] = o;
        }
    }

    public Object pop() {
        if (empty()) {
            throw new EmptyStackException();
        } else {
            return object[first--];
        }
    }

    public Object peek() {
        if (empty()) {
            throw new EmptyStackException();
        } else {
            return object[first];
        }
    }

    public boolean empty() {
        return first < 0;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "object=" + Arrays.toString(object)
                + '}';
    }
}
