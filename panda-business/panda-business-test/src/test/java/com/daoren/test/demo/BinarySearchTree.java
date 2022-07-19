package com.daoren.test.demo;

public class BinarySearchTree {
    private Node root;

    static class Node {
        int key;
        String value;
        Node leftChild;
        Node rightChild;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
