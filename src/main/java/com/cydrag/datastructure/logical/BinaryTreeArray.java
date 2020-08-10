package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.FullDataStructureException;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.physical.Array;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SingleLinkedList;

/**
 *
 * New nodes are always level-order added for now...
 */
public class BinaryTreeArray<T> implements BinaryTree<T> {

    final Array<T> array;
    int lastUsedIndex;

    public BinaryTreeArray(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }

        this.array = new Array<>(size + 1);
        this.array.add(null, 0);
        this.lastUsedIndex = 0;
    }

    public BinaryTreeArray(int size, T root) {
        this(size);
        this.array.add(root, ++this.lastUsedIndex);
    }

    void checkIfNull(T value) {
        if (value == null) {
            throw new NullValueException();
        }
    }

    @Override
    public boolean isFull() {
        if (this.lastUsedIndex == 0) {
            return false;
        }
        else {
            double result = Math.log(this.lastUsedIndex + 1) / Math.log(2);
            return result % 1 == 0;
        }
    }

    @Override
    public boolean isStrict() {
        return this.lastUsedIndex % 2 == 1;
    }

    public boolean isFilled() {
        return this.lastUsedIndex == this.capacity();
    }

    public boolean isEmpty() {
        return this.lastUsedIndex == 0;
    }

    public int capacity() {
        return this.array.length() - 1;
    }

    @Override
    public void add(T value) {
        checkIfNull(value);
        if (this.isFilled()) {
            throw new FullDataStructureException();
        }

        this.array.add(value, ++this.lastUsedIndex);
    }

    @Override
    public void remove(T value) {
        checkIfNull(value);

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            if ((value == array.get(i)) || (value.equals(array.get(i)))) {
                this.array.add(this.array.get(this.lastUsedIndex), i);
                this.array.add(null, this.lastUsedIndex--);
                break;
            }
        }
    }

    @Override
    public boolean contains(T value) {
        checkIfNull(value);

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            if ((value == array.get(i)) || (value.equals(this.array.get(i)))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public LinkedList<T> inOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.inOrder(linkedList, 1);
        return linkedList;
    }

    private void inOrder(LinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            this.inOrder(list, index * 2);
            list.addAtEnd(this.array.get(index));
            this.inOrder(list, index * 2 + 1);
        }
    }

    @Override
    public LinkedList<T> preOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.preOrderTraverse(linkedList, 1);
        return linkedList;
    }

    private void preOrderTraverse(LinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            list.addAtEnd(this.array.get(index));
            preOrderTraverse(list, index * 2);
            preOrderTraverse(list, index * 2 + 1);
        }
    }

    @Override
    public LinkedList<T> postOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.postOrder(linkedList, 1);
        return linkedList;
    }

    private void postOrder(LinkedList<T> list, int index) {
        if (index <= this.lastUsedIndex) {
            this.postOrder(list, index * 2);
            this.postOrder(list, index * 2 + 1);
            list.addAtEnd(this.array.get(index));
        }
    }

    @Override
    public LinkedList<T> levelOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();

        for (int i = 1; i <= this.lastUsedIndex; i++) {
            linkedList.addAtEnd(this.array.get(i));
        }

        return linkedList;
    }

    @Override
    public void clear() {
        this.array.clear();
        this.lastUsedIndex = 0;
    }
}
