package datastructure.logical;

import datastructure.exceptions.FullDataStructureException;
import datastructure.exceptions.NullValueException;
import datastructure.physical.Array;
import datastructure.physical.LinkedList;
import datastructure.physical.SingleLinkedList;

// TODO: Actually allow null values?
public class BinaryTreeArray<T> implements BinaryTree<T> {

    private final Array<T> array;
    private int lastUsedIndex;

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
        this.checkNullValue(root);
        this.array.add(root, ++this.lastUsedIndex);
    }

    private void checkNullValue(T element) {
        if (element == null) {
            throw new NullValueException();
        }
    }

    @Override
    public boolean isStrict() {
        return this.lastUsedIndex % 2 == 1;
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
        this.checkNullValue(element);
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
                break;
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
