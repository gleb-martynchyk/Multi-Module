package com.jazzteam.martynchyk.iterator.entity;

import com.jazzteam.martynchyk.iterator.Iterator;
import com.jazzteam.martynchyk.iterator.TreeIterator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Tree<E> {

    private Node<E> root;

    public Tree(E rootData) {
        root = new Node<>();
        root.data = rootData;
        root.children = new ArrayList<Node<E>>();
    }

    @Getter
    @Setter
    public static class Node<E> {
        private E data;
        private Node<E> parent;
        private List<Node<E>> children;

        public Node() {
        }

        public Node(E data, Node<E> parent) {
            this.data = data;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        public Node<E> addChild(E data) {
            Node<E> child = new Node<>(data, this);
            this.children.add(child);
            return child;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data) &&
                    Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, children);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", children=" + children +
                    '}';
        }
    }

    public Node<E> addChild(E data) {
        Node<E> child = new Node<>(data, this.root);
        this.root.children.add(child);
        return child;
    }

    public Iterator iterator() {
        return new TreeIterator(root);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree<?> tree = (Tree<?>) o;
        return Objects.equals(root, tree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }


}
