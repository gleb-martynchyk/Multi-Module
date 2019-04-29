package org.jazzteam.martynchyk.entity.tree;

import java.util.List;

public class Tree {
    private Node root;

    private class Node {
        private Node parent;
        private List<Node> children;
    }
}

