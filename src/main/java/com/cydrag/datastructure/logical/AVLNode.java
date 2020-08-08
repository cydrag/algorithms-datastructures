package com.cydrag.datastructure.logical;

class AVLNode<T> {

    private T data;
    private int height;
    private AVLNode<T> left;
    private AVLNode<T> right;

    AVLNode(T data) {
        this.data = data;
        this.height = 0;
        this.left = this.right = null;
    }

    T getData() {
        return data;
    }

    void setHeight(int height) {
        this.height = height;
    }

    int getHeight() {
        return height;
    }

    AVLNode<T> getLeft() {
        return left;
    }

    AVLNode<T> getRight() {
        return right;
    }

    void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    void setRight(AVLNode<T> right) {
        this.right = right;
    }
}
