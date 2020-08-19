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
        // == Heapify bottom to top ==
        int lastUsedTempIndex = lastUsedIndex;
        int currentIndex = lastUsedTempIndex / 2;

        while (currentIndex > 0) {
            if (comparator.compare(array.get(currentIndex), array.get(lastUsedTempIndex)) > 0) {
                this.swap(currentIndex, lastUsedTempIndex);
                lastUsedTempIndex = currentIndex;
            }
            currentIndex /= 2;
        }
    }

    private void swap(int index1, int index2) {
        T temp = array.get(index1);
        array.add(array.get(index2), index1);
        array.add(temp, index2);
    }

    private void heapifyTopToBottom(int index) {
        int largest = index;
        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        if (leftIndex <= lastUsedIndex) {
            if (comparator.compare(array.get(largest), array.get(leftIndex)) > 0) {
                largest = leftIndex;
            }
        }

        if (rightIndex <= lastUsedIndex) {
            if (comparator.compare(array.get(index), array.get(rightIndex)) > 0) {
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
        checkIfNull(value);

        for (int i = 1; i <= lastUsedIndex; i++) {
            if ((value == array.get(i)) || (value.equals(array.get(i)))) {
                array.add(array.get(lastUsedIndex), i);
                array.add(null, lastUsedIndex--);
                heapifyTopToBottom(i);
                break;
            }
        }
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
