package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.TreeNode;

public class BinarySearchTree<T extends Comparable<? super T>> extends DynamicBinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T root) {
        super(root);
    }

    @Override
    protected void addValue(T value) {
        this.root = this.add(value, this.root);
    }

    @Override
    protected void removeValue(T value) {
        this.root = this.remove(value, this.root);
    }

    @Override
    protected boolean containsValue(T value) {
        return this.contains(value, this.root);
    }

    private TreeNode<T> add(T value, TreeNode<T> currentNode) {
        if (currentNode == null) {
            return new TreeNode<>(value);
        }
        else if (currentNode.getData().compareTo(value) >= 0) {
            currentNode.setLeft(this.add(value, currentNode.getLeft()));
            return currentNode;
        }
        else {
            currentNode.setRight(this.add(value, currentNode.getRight()));
            return currentNode;
        }
    }

    private TreeNode<T> remove(T value, TreeNode<T> currentNode) {
        if (currentNode == null) {
            return null;
        }
        else if (currentNode.getData().compareTo(value) > 0) {
            currentNode.setLeft(remove(value, currentNode.getLeft()));
        }
        else if (currentNode.getData().compareTo(value) < 0) {
            currentNode.setRight(remove(value, currentNode.getRight()));
        }
        else {
            if ((currentNode.getLeft() != null) && (currentNode.getRight() != null)) {
                TreeNode<T> successor = minimumNode(currentNode.getRight());
                currentNode.setData(successor.getData());
                currentNode.setRight(remove(successor.getData(), currentNode.getRight()));
            }
            else if (currentNode.getLeft() == null) {
                currentNode = currentNode.getRight();
            }
            else if (currentNode.getRight() == null) {
                currentNode = currentNode.getLeft();
            }
            else {
                currentNode = null;
            }
        }

        return currentNode;
    }

    private boolean contains(T dataToSearch, TreeNode<T> currentNode) {
        if (currentNode == null) {
            return false;
        }
        else if (currentNode.getData().compareTo(dataToSearch) == 0) {
            return true;
        }
        else if (currentNode.getData().compareTo(dataToSearch) > 0) {
            return this.contains(dataToSearch, currentNode.getLeft());
        }
        else {
            return this.contains(dataToSearch, currentNode.getRight());
        }
    }

    private static <T> TreeNode<T> minimumNode(TreeNode<T> root) {
        if (root.getLeft() == null) {
            return root;
        }

        return minimumNode(root.getLeft());
    }
}
