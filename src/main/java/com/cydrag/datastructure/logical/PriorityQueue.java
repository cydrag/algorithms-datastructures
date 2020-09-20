package com.cydrag.datastructure.logical;

import java.util.Comparator;

public class PriorityQueue<T extends Comparable<? super T>> extends DynamicQueue<T> {

    private final Comparator<T> comparator;

    public PriorityQueue() {
        this(Comparator.naturalOrder());
    }

    public PriorityQueue(Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    @Override
    public void enqueue(T value) {
        if (this.linkedList.isEmpty()) {
            this.linkedList.addAtStart(value);
        }
        else {
            if (value == null) {
                this.linkedList.addAtEnd(null);
            }
            else {
                boolean found = false;
                int lastIndex = 0;

                for (int i = 0; i < this.linkedList.size(); i++) {
                    T data = this.linkedList.get(i);
                    lastIndex = i + 1;

                    if (data != null) {
                        if (comparator.compare(data, value) > 0) {
                            this.linkedList.add(value, i);
                            found = true;
                            break;
                        }
                    }
                    else {
                        this.linkedList.add(value, i);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    this.linkedList.add(value, lastIndex);
                }
            }
        }
    }

    @Override
    public void remove(T value) {
        linkedList.remove(value);
    }
}
