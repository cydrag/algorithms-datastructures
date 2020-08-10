package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.TreeNode;

public class BinarySearchTree<T extends Comparable<? super T>> extends DynamicBinaryTreeBase<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T root) {
        super(root);
    }

    @Override
    protected void addValue(T value) {
        this.root = this.add(this.root, value, 0);
    }

    @Override
    protected void removeValue(T value) {
        this.root = this.remove(this.root, value);
    }

    @Override
    protected boolean containsValue(T value) {
        return this.contains(this.root, value);
    }

    private TreeNode<T> add(TreeNode<T> currentNode, T value, int height) {
        if (currentNode == null) {
            TreeNode<T> newNode = new TreeNode<>(value);
            newNode.setHeight(height);
            return newNode;
        }
        else {
            if (currentNode.getData().compareTo(value) >= 0) {
                currentNode.setLeft(this.add(currentNode.getLeft(), value, height + 1));
            }
            else {
                currentNode.setRight(this.add(currentNode.getRight(), value, height + 1));
            }
            return currentNode;
        }
    }

    private TreeNode<T> remove(TreeNode<T> currentNode, T value) {
        if (currentNode == null) {
            return null;
        }
        else if (currentNode.getData().compareTo(value) > 0) {
            currentNode.setLeft(remove(currentNode.getLeft(), value));
        }
        else if (currentNode.getData().compareTo(value) < 0) {
            currentNode.setRight(remove(currentNode.getRight(), value));
        }
        else {
            if ((currentNode.getLeft() != null) && (currentNode.getRight() != null)) {
                TreeNode<T> successor = minimumNode(currentNode.getRight());
                currentNode.setData(successor.getData());
                currentNode.setRight(remove(currentNode.getRight(), successor.getData()));
            }
            else if (currentNode.getLeft() == null) {
                int currentHeight = currentNode.getHeight();
                currentNode = currentNode.getRight();
                if (currentNode != null) {
                    currentNode.setHeight(currentHeight);
                }
            }
            else if (currentNode.getRight() == null) {
                int currentHeight = currentNode.getHeight();
                currentNode = currentNode.getLeft();
                if (currentNode != null) {
                    currentNode.setHeight(currentHeight);
                }
            }
            else {
                currentNode = null;
            }
        }

        return currentNode;
    }

    private boolean contains(TreeNode<T> currentNode, T value) {
        if (currentNode == null) {
            return false;
        }
        else if (currentNode.getData().compareTo(value) == 0) {
            return true;
        }
        else if (currentNode.getData().compareTo(value) > 0) {
            return this.contains(currentNode.getLeft(), value);
        }
        else {
            return this.contains(currentNode.getRight(), value);
        }
    }

    private static <T> TreeNode<T> minimumNode(TreeNode<T> root) {
        if (root.getLeft() == null) {
            return root;
        }

        return minimumNode(root.getLeft());
    }
}
