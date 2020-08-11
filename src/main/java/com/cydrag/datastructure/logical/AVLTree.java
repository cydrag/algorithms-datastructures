package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.nodes.TreeNode;

// TODO: Test this class and see if code reduction can be done?
public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    public AVLTree() {
        super();
    }

    public AVLTree(T value) {
        super(value);
    }

    @Override
    protected void addValue(T value) {
        this.root = this.add(this.root, value);
    }

    @Override
    protected void removeValue(T value) {
        this.root = this.removeValue(this.root, value);
    }

    private TreeNode<T> removeValue(TreeNode<T> currentNode, T value) {

        if (currentNode == null) {
            return null;
        }

        if (currentNode.getData().compareTo(value) > 0) {
            currentNode.setLeft(this.removeValue(currentNode.getLeft(), value));
        }
        else if (currentNode.getData().compareTo(value) < 0) {
            currentNode.setRight(this.removeValue(currentNode.getRight(), value));
        }
        else {
            if ((currentNode.getLeft() != null) && (currentNode.getRight() != null)) {
                TreeNode<T> minNodeForRight = minimumNode(currentNode.getRight());

                currentNode.setData(minNodeForRight.getData());

                removeValue(currentNode.getRight(), minNodeForRight.getData());
            }
            else if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }
            else if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            }
            else {
                currentNode = null;
            }

            return currentNode;
        }

        int balance = calculateBalance(currentNode.getLeft(), currentNode.getRight());

        if (balance > 1) {
            if (calculateBalance(currentNode.getLeft().getLeft(), currentNode.getLeft().getRight()) > 0) {
                currentNode = rightRotate(currentNode);
            } else {
                currentNode.setLeft(leftRotate(currentNode.getLeft()));
                currentNode = rightRotate(currentNode);
            }
        } else if (balance < -1) {
            if (calculateBalance(currentNode.getRight().getRight(), currentNode.getRight().getLeft()) > 0) {
                currentNode = leftRotate(currentNode);
            } else {
                currentNode.setRight(rightRotate(currentNode.getRight()));
                currentNode = leftRotate(currentNode);
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

    private TreeNode<T> add(TreeNode<T> currentNode, T value) {

        if (currentNode == null) {
            return new TreeNode<>(value);
        }
        else if (currentNode.getData().compareTo(value) >= 0) {
            currentNode.setLeft(this.add(currentNode.getLeft(), value));
        }
        else {
            currentNode.setRight(this.add(currentNode.getRight(), value));
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

    private TreeNode<T> leftRotate(TreeNode<T> currentNode) {
        TreeNode<T> newRoot = currentNode.getRight();
        currentNode.setRight(currentNode.getRight().getLeft());
        newRoot.setLeft(currentNode);
        currentNode.setHeight(calculateHeight(currentNode));
        newRoot.setHeight(calculateHeight(newRoot));
        return newRoot;
    }

    private TreeNode<T> rightRotate(TreeNode<T> currentNode) {
        TreeNode<T> newRoot = currentNode.getLeft();
        currentNode.setLeft(currentNode.getLeft().getRight());
        newRoot.setRight(currentNode);
        currentNode.setHeight(calculateHeight(currentNode));
        newRoot.setHeight(calculateHeight(newRoot));
        return newRoot;
    }

    private static <T> int calculateBalance(TreeNode<T> left, TreeNode<T> right) {
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

    private static <T> int calculateHeight(TreeNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return 1 + Math.max((currentNode.getLeft() != null ? currentNode.getLeft().getHeight() : -1),
                (currentNode.getRight() != null ? currentNode.getRight().getHeight() : -1));
    }
}
