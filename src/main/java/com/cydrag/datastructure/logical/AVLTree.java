package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.physical.SingleLinkedList;

public class AVLTree<T extends Comparable<? super T>> {

    private AVLNode<T> root;

    public AVLTree() {
        this.root = null;
    }

    public AVLTree(T data) {
        this.root = new AVLNode<>(data);
    }

    public void insert(T data) {
        this.root = insert(this.root, data);
    }

    private AVLNode<T> insert(AVLNode<T> currentNode, T data) {

        if (currentNode == null) {
            return new AVLNode<>(data);
        }
        else if (currentNode.getData().compareTo(data) >= 0) {
            currentNode.setLeft(insert(currentNode.getLeft(), data));
        }
        else {
            currentNode.setRight(insert(currentNode.getRight(), data));
        }

        int balance = calculateBalance(currentNode.getLeft(), currentNode.getRight());

        if (balance > 1) {
            if (calculateBalance(currentNode.getLeft().getLeft(), currentNode.getLeft().getRight()) > 0) {
                currentNode = this.rightRotate(currentNode);
            }
            else {
                currentNode.setLeft(this.leftRotate(currentNode.getLeft()));
                currentNode = this.rightRotate(currentNode);
            }
        }
        else if (balance < -1) {
            if (calculateBalance(currentNode.getRight().getRight(), currentNode.getRight().getLeft()) > 0) {
                currentNode = this.leftRotate(currentNode);
            }
            else {
                currentNode.setRight(this.rightRotate(currentNode.getRight()));
                currentNode = this.leftRotate(currentNode);
            }
        }

        if (currentNode.getLeft() != null) {
            currentNode.getLeft().setHeight(calculateHeight(currentNode.getLeft()));
        }

        if (currentNode.getRight() != null) {
            currentNode.getRight().setHeight(calculateHeight(currentNode.getRight()));
        }

        currentNode.setHeight(calculateHeight(currentNode));
        return currentNode;
    }

    private AVLNode<T> leftRotate(AVLNode<T> currentNode) {
        AVLNode<T> newRoot = currentNode.getRight();
        currentNode.setRight(currentNode.getRight().getLeft());
        newRoot.setLeft(currentNode);
        currentNode.setHeight(calculateHeight(currentNode));
        newRoot.setHeight(calculateHeight(newRoot));
        return newRoot;
    }

    private AVLNode<T> rightRotate(AVLNode<T> currentNode) {
        AVLNode<T> newRoot = currentNode.getLeft();
        currentNode.setLeft(currentNode.getLeft().getRight());
        newRoot.setRight(currentNode);
        currentNode.setHeight(calculateHeight(currentNode));
        newRoot.setHeight(calculateHeight(newRoot));
        return newRoot;
    }

    private static int calculateBalance(AVLNode left, AVLNode right) {
        if ((left == null) && (right == null)) {
            return 0;
        }
        else if (left == null) {
            return -1 * (right.getHeight() + 1);
        }
        else if (right == null) {
            return left.getHeight() + 1;
        }
        else {
            return left.getHeight() - right.getHeight();
        }
    }

    private static int calculateHeight(AVLNode currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return 1 + Math.max((currentNode.getLeft() != null ? currentNode.getLeft().getHeight() : -1),
                (currentNode.getRight() != null ? currentNode.getRight().getHeight() : -1));
    }

    public SingleLinkedList<T> levelOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();

        Queue<AVLNode<T>> queue = new DynamicQueue<>();

        if (this.root != null) {
            queue.enqueue(this.root);
        }

        while (!queue.isEmpty()) {
            AVLNode<T> node = queue.dequeue();

            list.addAtEnd(node.getData());

            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enqueue(node.getRight());
            }
        }

        return list;
    }
}
