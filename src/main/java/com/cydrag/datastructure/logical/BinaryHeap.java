package com.cydrag.datastructure.logical;

import java.util.Comparator;

public class BinaryHeap<T extends Comparable<? super T>> extends BinaryTreeArray<T> implements OrderedDataStructure<T> {

    private final Comparator<T> comparator;

    public BinaryHeap(int size) {
        super(size);
        this.comparator = Comparator.naturalOrder();
    }

    public BinaryHeap(int size, T root) {
        super(size, root);
        this.comparator = Comparator.naturalOrder();
    }

    public BinaryHeap(int size, Comparator<T> comparator) {
        super(size);
        this.comparator = comparator;
    }

    public BinaryHeap(int size, T root, Comparator<T> comparator) {
        super(size, root);
        this.comparator = comparator;
    }

    @Override
    public void add(T value) {
        super.add(value);
        this.heapifyBottomToTop();
    }

    private void heapifyBottomToTop() {
        int lastUsedTempIndex = this.lastUsedIndex;
        int currentIndex = lastUsedTempIndex / 2;

        while (currentIndex > 0) {
            if (this.comparator.compare(this.array.get(currentIndex), this.array.get(lastUsedTempIndex)) > 0) {
                this.swap(currentIndex, lastUsedTempIndex);
                lastUsedTempIndex = currentIndex;
            }
            currentIndex /= 2;
        }
    }

    private void swap(int index1, int index2) {
        T temp = this.array.get(index1);
        this.array.add(array.get(index2), index1);
        this.array.add(temp, index2);
    }

    private void heapifyTopToBottom(int index) {
        int largest = index;
        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        if (leftIndex <= lastUsedIndex) {
            if (this.comparator.compare(this.array.get(largest), this.array.get(leftIndex)) > 0) {
                largest = leftIndex;
            }
        }

        if (rightIndex <= lastUsedIndex) {
            if (this.comparator.compare(this.array.get(largest), this.array.get(rightIndex)) > 0) {
                largest = rightIndex;
            }
        }

        if (largest != index) {
            this.swap(index, largest);
            this.heapifyTopToBottom(largest);
        }
    }

    @Override
    public void remove(T value) {
        this.checkIfNull(value);
        super.remove(value);
        this.heapifyTopToBottom(1);
    }

    @Override
    public T remove() {
        T data = this.array.get(1);
        this.remove(data);
        return data;
    }

    @Override
    public T peek() {
        return this.array.get(1);
    }
}
