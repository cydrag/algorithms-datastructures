package datastructure.logical;

import datastructure.exceptions.FullDataStructureException;
import datastructure.exceptions.NullValueException;
import datastructure.physical.Array;
import datastructure.physical.SingleLinkedList;

public class BinaryTreeArray<T> implements BinaryTree<T> {

    private Array<T> array;
    private int lastUsedIndex;

    public BinaryTreeArray(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }

        this.array = new Array<>(size + 1);
        this.array.add(null, 0);
        this.lastUsedIndex = 0;
    }

    public BinaryTreeArray(int size, T initializer) {
        this(size);
        this.array.add(initializer, ++this.lastUsedIndex);
    }

    private void checkNullValue(T element) {
        if (element == null) {
            throw new NullValueException();
        }
    }

    public boolean isFull() {
        return this.lastUsedIndex == this.getCapacity();
    }

    public boolean isEmpty() {
        return this.lastUsedIndex == 0;
    }

    public int getCapacity() {
        return this.array.length() - 1;
    }

    @Override
    public void add(T element) {

        if (this.isFull()) {
            throw new FullDataStructureException();
        }

        this.array.add(element, ++this.lastUsedIndex);
    }

    @Override
    public void remove(T element) {
        this.checkNullValue(element);

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            if (element.equals(array.get(i))) {
                this.array.add(this.array.get(this.lastUsedIndex), i);
                this.array.add(null, this.lastUsedIndex--);
            }
        }

    }

    @Override
    public boolean contains(T element) {
        this.checkNullValue(element);

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            if (element.equals(this.array.get(i))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public SingleLinkedList<T> inOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.inOrderTraverse(list, 1);
        return list;
    }

    private void inOrderTraverse(SingleLinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            inOrderTraverse(list, index * 2);
            list.addAtEnd(this.array.get(index));
            inOrderTraverse(list, index * 2 + 1);
        }
    }

    @Override
    public SingleLinkedList<T> preOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        this.preOrderTraverse(list, 1);
        return list;
    }

    private void preOrderTraverse(SingleLinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            list.addAtEnd(this.array.get(index));
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

    private void postOrderTraverse(SingleLinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            postOrderTraverse(list, index * 2);
            postOrderTraverse(list, index * 2 + 1);
            list.addAtEnd(this.array.get(index));
        }
    }

    @Override
    public SingleLinkedList<T> levelOrder() {
        SingleLinkedList<T> list = new SingleLinkedList<>();

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            list.addAtEnd(this.array.get(i));
        }

        return list;
    }

    @Override
    public void clear() {
        this.array.clear();
        this.lastUsedIndex = 0;
    }
}
