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

        if (value == null) {
            linkedList.addAtEnd(null);
        }
        else {
            if (linkedList.isEmpty()) {
                linkedList.addAtStart(value);
            }
            for (int i = 0; i < linkedList.size(); i++) {
                if (linkedList.get(i) != null) {
                    if (comparator.compare(linkedList.get(i), value) > 0) {
                        linkedList.add(value, i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void remove(T value) {
        linkedList.remove(value);
    }
}
