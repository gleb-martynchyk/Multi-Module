package com.jazzteam.martynchyk.iterator;

import com.jazzteam.martynchyk.iterator.entity.Tree;

import java.util.*;

public class TreeIterator implements Iterator<Tree.Node> {

    private Tree.Node current;
    private Set<Tree.Node> visitedNodes = new HashSet<>();
    private Queue<Tree.Node> queue = new LinkedList<>();

    public TreeIterator(Tree.Node root) {
        queue.add(root);
        visitedNodes.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Tree.Node next() {
        if (!hasNext()) throw new NoSuchElementException();
        current = queue.poll();
        childrenToQueue(current);
        return current;
    }

    private void childrenToQueue(Tree.Node parent) {
        List<Tree.Node> children = parent.getChildren();
        if (children == null) {
            return;
        }
        for (Tree.Node child : children) {
            queue.add(child);
        }
    }

    @Override
    public void remove() {
    }
}
