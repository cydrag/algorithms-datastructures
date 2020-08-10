package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.TreeNode;

public class RegularBinaryTree<T> extends DynamicBinaryTreeBase<T> {

    private int size;

    public RegularBinaryTree() {
        super();
        this.size = 0;
    }

    public RegularBinaryTree(T root) {
        super(root);
        this.size = 1;
    }

    @Override
    protected void addValue(T value) {

        TreeNode<T> newChild = new TreeNode<>(value);
        this.size++;
        int height = (int)(Math.log(this.size) / Math.log(2));
        newChild.setHeight(height);

        if (this.root == null) {
            this.root = newChild;
        }
        else {
            Queue<TreeNode<T>> children = new DynamicQueue<>();

            children.enqueue(this.root);

            while (!children.isEmpty()) {
                TreeNode<T> node = children.dequeue();

                if (node.getLeft() == null) {
                    node.setLeft(newChild);
                    break;
                }
                else if (node.getRight() == null) {
                    node.setRight(newChild);
                    break;
                }
                else {
                    children.enqueue(node.getLeft());
                    children.enqueue(node.getRight());
                }
            }
            children.clear();
        }
    }

    @Override
    protected void removeValue(T value) {

        boolean found = false;

        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(this.root);

        TreeNode<T> current = null;

        while (!children.isEmpty()) {
            current = children.dequeue();

            if (current != null) {
                if (current.getData().equals(value)) {
                    found = true;
                    children.clear();
                    break;
                }
                else {
                    children.enqueue(current.getLeft());
                    children.enqueue(current.getRight());
                }
            }
        }

        if (found) {
            TreeNode<T> deepestNode = this.getDeepestNode();
            current.setData(deepestNode.getData());
            this.removeLastChild();
            this.size--;
        }
    }

    @Override
    protected boolean containsValue(T value) {
        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(this.root);

        while (!children.isEmpty()) {
            TreeNode<T> node = children.dequeue();

            if (node != null) {
                if (node.getData().equals(value)) {
                    children.clear();
                    return true;
                }
                else {
                    children.enqueue(node.getLeft());
                    children.enqueue(node.getRight());
                }
            }
        }

        return false;
    }

    private void removeLastChild() {

        if ((this.root.getLeft() == null) && (this.root.getRight() == null)) {
            this.root = null;
            return;
        }

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        TreeNode<T> currentNode = this.root;
        TreeNode<T> previousNode;

        queue.enqueue(this.root);

        while (!queue.isEmpty()) {
            previousNode = currentNode;
            currentNode = queue.dequeue();

            if (currentNode.getLeft() == null) {
                previousNode.setRight(null);
                break;
            }
            else if (currentNode.getRight() == null) {
                currentNode.setLeft(null);
                break;
            }
            else {
                queue.enqueue(currentNode.getLeft());
                queue.enqueue(currentNode.getRight());
            }
        }

        queue.clear();
    }

    private TreeNode<T> getDeepestNode() {

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        queue.enqueue(this.root);

        TreeNode<T> current = null;

        while (!queue.isEmpty()) {
            current = queue.dequeue();

            if (current.getLeft() != null) {
                queue.enqueue(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.enqueue(current.getRight());
            }

        }

        return current;
    }

}
