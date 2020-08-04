package datastructure.logical;

import datastructure.physical.SingleLinkedList;

abstract class DynamicBinaryTree<T> implements BinaryTree<T> {

    TreeNode<T> root;

    DynamicBinaryTree() {
        this.root = null;
    }

    DynamicBinaryTree(T data) {
        this.root = new TreeNode<>(data);
    }

    @Override
    public SingleLinkedList<T> inOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.inOrderTraverse(list, this.root);
        return list;
    }

    @Override
    public SingleLinkedList<T> preOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.preOrderTraverse(list, this.root);
        return list;
    }

    @Override
    public SingleLinkedList<T> postOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.postOrderTraverse(list, this.root);
        return list;
    }

    @Override
    public SingleLinkedList<T> levelOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();

        Queue<TreeNode<T>> queue = new DynamicQueue<>();

        if (this.root != null) {
            queue.enqueue(this.root);
        }

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.dequeue();

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

    private void inOrderTraverse(SingleLinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            inOrderTraverse(list, root.getLeft());
            list.addAtEnd(root.getData());
            inOrderTraverse(list, root.getRight());
        }
    }

    private void preOrderTraverse(SingleLinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            list.addAtEnd(root.getData());
            preOrderTraverse(list, root.getLeft());
            preOrderTraverse(list, root.getRight());
        }
    }

    private void postOrderTraverse(SingleLinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            postOrderTraverse(list, root.getLeft());
            postOrderTraverse(list, root.getRight());
            list.addAtEnd(root.getData());
        }
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void destroy() {
        this.root = null;
    }
}
