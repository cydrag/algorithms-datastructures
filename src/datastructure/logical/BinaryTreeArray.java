package datastructure.logical;

import datastructure.exceptions.StatusException;
import datastructure.physical.SingleLinkedList;

public class BinaryTreeArray<T> implements BinaryTree<T> {

    private Object[] values;
    private int lastUsedIndex;

    public BinaryTreeArray(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }

        this.values = new Object[size + 1];
        this.values[0] = null;
        this.lastUsedIndex = 0;
    }

    public BinaryTreeArray(int size, T initializer) {
        this(size);
        this.values[++this.lastUsedIndex] = initializer;
    }

    public boolean isFull() {
        return this.lastUsedIndex == this.getCapacity();
    }

    public boolean isEmpty() {
        return this.lastUsedIndex == 0;
    }

    public int getCapacity() {
        return this.values.length - 1;
    }

    @Override
    public boolean contains(T data) {

        if (data != null) {
            for (int i = 1; i <= this.lastUsedIndex; i++) {
                if (this.values[i].equals(data)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void remove(T data) {

        if (data != null) {
            for (int i = 1; i <= this.lastUsedIndex; i++) {
                if (this.values[i].equals(data)) {
                    this.values[i] = this.values[this.lastUsedIndex];
                    this.values[this.lastUsedIndex--] = null;
                }
            }
        }
    }

    @Override
    public SingleLinkedList<T> inOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.inOrderTraverse(list, 1);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void inOrderTraverse(SingleLinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            inOrderTraverse(list, index * 2);
            list.addAtEnd((T)this.values[index]);
            inOrderTraverse(list, index * 2 + 1);
        }
    }

    @Override
    public SingleLinkedList<T> preOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.preOrderTraverse(list, 1);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void preOrderTraverse(SingleLinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            list.addAtEnd((T)this.values[index]);
            preOrderTraverse(list, index * 2);
            preOrderTraverse(list, index * 2 + 1);
        }
    }

    @Override
    public SingleLinkedList<T> postOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.postOrderTraverse(list, 1);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void postOrderTraverse(SingleLinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            postOrderTraverse(list, index * 2);
            postOrderTraverse(list, index * 2 + 1);
            list.addAtEnd((T)this.values[index]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public SingleLinkedList<T> levelOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            list.addAtEnd((T)this.values[i]);
        }

        return list;
    }

    @Override
    public void addChild(T data) {
        if (this.isFull()) {
            throw new StatusException("The binary tree array is full.");
        }

        this.values[++this.lastUsedIndex] = data;
    }

    @Override
    public void delete() {
        this.values = null;
        this.lastUsedIndex = 0;
    }
}
