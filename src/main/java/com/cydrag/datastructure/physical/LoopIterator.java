package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.Node;

import java.util.Iterator;

class LoopIterator<T> implements Iterator<T> {

    private final LinkedListBase<T> circularLinkedList;
    private Node<T> temp;
    private final long modCount;

    LoopIterator(LinkedListBase<T> circularLinkedList) {
        this.circularLinkedList = circularLinkedList;
        this.temp = circularLinkedList.head;
        this.modCount = circularLinkedList.modificationCount;
    }

    @Override
    public boolean hasNext() {
        if (this.modCount != circularLinkedList.modificationCount) {
            throw new ConcurrentChangeException();
        }
        return this.temp != null;
    }

    @Override
    public T next() {
        if (this.modCount != circularLinkedList.modificationCount) {
            throw new ConcurrentChangeException();
        }
        if (this.temp == null) {
            throw new NullValueException();
        }

        T value = this.temp.getData();
        this.temp = this.temp.getNext();
        return value;
    }
}
