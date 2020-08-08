package com.cydrag.datastructure.nodes;

public class TreeNode<T> {

    private T data;
    private int height;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data) {
       this.data = data;
       this.height = 0;
       this.left = this.right = null;
    }

    public T getData() {
       return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TreeNode<T> getLeft() {
       return left;
    }

    public void setLeft(TreeNode<T> left) {
       this.left = left;
    }

    public TreeNode<T> getRight() {
       return right;
    }

    public void setRight(TreeNode<T> right) {
       this.right = right;
    }
}
