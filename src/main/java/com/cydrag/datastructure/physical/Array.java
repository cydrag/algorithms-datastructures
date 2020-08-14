package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.FullDataStructureException;
import com.cydrag.datastructure.exceptions.IndexNotInBoundsException;
import com.cydrag.datastructure.exceptions.NegativeValueException;

import java.util.Iterator;

public class Array<T> implements PhysicalDataStructure<T>, Reversible<T> {

    private final Object[] array;
    private final int size;

    public Array(int size) {
        if (size < 0) {
            throw new NegativeValueException(size);
        }
        this.array = new Object[size];
        this.size = size;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexNotInBoundsException(index, this.size());
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Iterator<T> reverseIterator() {
        return new ReverseItr();
    }

    public ArrayIterator<T> arrayIterator() {
        return new ArrayItr();
    }

    public ArrayIterator<T> arrayIterator(int index) {
        return new ArrayItr(index);
    }

    private class Itr implements Iterator<T> {
        int cursor;
        boolean headNext;

        private Itr() {
            this.cursor = 0;
            this.headNext = false;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != Array.this.size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (this.cursor < 0) {
                this.cursor = 0;
            }
            return (T)Array.this.array[this.cursor++];
        }

        @Override
        public void remove() {
            Array.this.array[this.cursor] = null;
        }
    }

    private class ReverseItr implements Iterator<T> {
        int cursor;

        public ReverseItr() {
            this.cursor = Array.this.size - 1;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != -1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T)Array.this.array[this.cursor--];
        }
    }

    private class ArrayItr extends Itr implements ArrayIterator<T> {

        private ArrayItr() {
            super();
        }

        private ArrayItr(int index) {
            Array.this.checkBounds(index);
            this.cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != -1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T previous() {
            if (this.cursor >= Array.this.size) {
                this.cursor = Array.this.size - 1;
            }
            return (T)Array.this.array[this.cursor--];
        }

        @Override
        public void setIndex(int index) {
            Array.this.checkBounds(index);
            this.cursor = index;
        }
    }

    private boolean hasFreeSpot() {
        for (Object o : this.array) {
            if (o == null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void add(T value, int index) {
        this.checkBounds(index);
        this.array[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        this.checkBounds(index);
        return (T)this.array[index];
    }

    @Override
    public void remove(int index) {
        this.checkBounds(index);
        this.array[index] = null;
    }

    @Override
    public void add(T value) {
        if (!this.hasFreeSpot()) {
            throw new FullDataStructureException();
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] == null) {
                    this.array[i] = value;
                    break;
                }
            }
        }
    }

    @Override
    public void remove(T value) {
        if (value != null) {
            for (int i = 0; i < this.size; i++) {
                if ((this.array[i] == value) || (value.equals(this.array[i]))) {
                    this.array[i] = null;
                }
            }
        }
    }

    @Override
    public boolean contains(T value) {
        for (Object o : this.array) {
            if (o == value) {
                return true;
            }
            else if (o != null) {
                if (o.equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        for (Object o : array) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
    }
}
