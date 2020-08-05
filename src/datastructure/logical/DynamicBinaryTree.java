package datastructure.logical;

import datastructure.physical.LinkedList;
import datastructure.physical.SingleLinkedList;

abstract class DynamicBinaryTree<T> implements BinaryTree<T> {

    TreeNode<T> root;

    DynamicBinaryTree() {
        this.root = null;
    }

    DynamicBinaryTree(T root) {
        this.root = new TreeNode<>(root);
    }

    // TODO: Make package-private method to check if entered root value is null

    @Override
    public boolean isStrict() {
        if (this.root != null) {

            boolean strict = true;

            Queue<TreeNode<T>> nodes = new DynamicQueue<>();
            nodes.enqueue(this.root);

            while (!nodes.isEmpty()) {
                TreeNode<T> currentNode = nodes.dequeue();

                if (!(((currentNode.getLeft() == null) && (currentNode.getRight() == null)) ||
                        ((currentNode.getLeft() != null) && (currentNode.getRight() != null)))) {
                    strict = false;
                    break;
                }
                else {
                    if (currentNode.getLeft() != null) {
                        nodes.enqueue(currentNode.getLeft());
                    }

                    if (currentNode.getRight() != null) {
                        nodes.enqueue(currentNode.getRight());
                    }
                }
            }
            nodes.clear();
            return strict;
        }
        return false;
    }

    @Override
    public LinkedList<T> inOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.inOrder(linkedList, this.root);
        return linkedList;
    }

    private void inOrder(LinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            this.inOrder(list, root.getLeft());
            list.addAtEnd(root.getData());
            this.inOrder(list, root.getRight());
        }
    }

    @Override
    public LinkedList<T> preOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.preOrder(linkedList, this.root);
        return linkedList;
    }

    private void preOrder(LinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            list.addAtEnd(root.getData());
            this.preOrder(list, root.getLeft());
            this.preOrder(list, root.getRight());
        }
    }

    @Override
    public LinkedList<T> postOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.postOrder(linkedList, this.root);
        return linkedList;
    }

    private void postOrder(LinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            this.postOrder(list, root.getLeft());
            this.postOrder(list, root.getRight());
            list.addAtEnd(root.getData());
        }
    }

    @Override
    public LinkedList<T> levelOrder() {

        LinkedList<T> linkedList = new SingleLinkedList<>();
        Queue<TreeNode<T>> queue = new DynamicQueue<>();

        if (this.root != null) {
            queue.enqueue(this.root);
        }

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.dequeue();

            linkedList.addAtEnd(node.getData());

            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enqueue(node.getRight());
            }
        }

        return linkedList;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public abstract void add(T element);

    @Override
    public abstract void remove(T element);

    @Override
    public abstract boolean contains(T element);
}
