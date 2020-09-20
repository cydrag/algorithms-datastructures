package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.TreeNode;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

abstract class DynamicBinaryTreeBase<T> implements BinaryTree<T> {

    TreeNode<T> root;

    DynamicBinaryTreeBase() {
        this.root = null;
    }

    DynamicBinaryTreeBase(T root) {
        checkIfNull(root);
        this.root = new TreeNode<>(root);
    }

    void checkIfNull(T value) {
        if (value == null) {
            throw new NullValueException();
        }
    }

    @Override
    public boolean isFull() {
        if (this.root != null) {
            Queue<TreeNode<T>> queue = new DynamicQueue<>();

            queue.enqueue(this.root);
            int expected = 1; // broj dece koji se ocekuje na jednom nivou ukoliko svaki roditelj na istom nivou ima dece

            while (!queue.isEmpty()) {

                boolean firstHasChildren = true, first = true;

                for (int i = 0; i < expected; i++) {
                    TreeNode<T> current = queue.dequeue();

                    if (first) { // Uvek se prvi put na novom nivou pita roditelj da li ima dece
                        if ((current.getLeft() == null) && (current.getRight() == null)) { // ako nema onda se ocekuje da sledeci roditelji na istom nivou nemaju decu
                            firstHasChildren = false;
                        } else if ((current.getLeft() != null) && (current.getRight() != null)) { // ako ima onda svi roditelji na istom nivou moraju da imaju decu
                            queue.enqueue(current.getLeft());
                            queue.enqueue(current.getRight());
                            firstHasChildren = true;
                        } else { // Ukoliko bar jedan roditelj ima samo jedno dete, automatski nije potpuno stablo
                            queue.clear();
                            return false;
                        }
                        first = false;
                    } else { // Sudbina potpunog stabla zavisi od prvog roditelja
                        if ((current.getLeft() != null && current.getRight() != null)) {
                            if (!firstHasChildren) {
                                queue.clear();
                                return false;
                            }
                            queue.enqueue(current.getLeft());
                            queue.enqueue(current.getRight());
                        } else {
                            if (firstHasChildren) {
                                queue.clear();
                                return false;
                            }
                        }
                    }
                }

                expected *= 2;
            }

        }
        return true;
    }

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
        else {
            return true;
        }
    }

    @Override
    public boolean isComplete() {
        boolean nullValFound = false;

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.dequeue();

            if (current == null) {
                nullValFound = true;
            }
            else {
                if (nullValFound) {
                    return false;
                }
                else {
                    queue.enqueue(current.getLeft());
                    queue.enqueue(current.getRight());
                }
            }
        }

        return true;
    }

    @Override
    public LinkedList<T> levelOrder() {

        LinkedList<T> linkedList = new SinglyLinkedList<>();
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
    public LinkedList<T> inOrder() {
        LinkedList<T> linkedList = new SinglyLinkedList<>();
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
        LinkedList<T> linkedList = new SinglyLinkedList<>();
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
        LinkedList<T> linkedList = new SinglyLinkedList<>();
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

    protected abstract void addHook(T value);
    protected abstract void removeHook(T value);
    protected abstract boolean containsHook(T value);

    @Override
    public void add(T value) {
        this.checkIfNull(value);
        this.addHook(value);
    }

    @Override
    public void remove(T value) {
        this.checkIfNull(value);
        this.removeHook(value);
    }

    @Override
    public boolean contains(T value) {
        this.checkIfNull(value);
        return this.containsHook(value);
    }

    @Override
    public int size() {
        int numberOfNodes = 0;
        Queue<TreeNode<T>> queue = new DynamicQueue<>();

        if (this.root != null) {
            queue.enqueue(this.root);
        }

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.dequeue();
            numberOfNodes++;

            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enqueue(node.getRight());
            }
        }

        return numberOfNodes;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void clear() {
        this.root = null;
    }
}
