package datastructure.physical;

import datastructure.exceptions.DestroyedDataStructureException;
import datastructure.exceptions.FullDataStructureException;
import datastructure.exceptions.IndexNotInBoundsException;
import datastructure.exceptions.NegativeSizeException;

import java.util.Iterator;

public class Array<T> implements PhysicalDataStructure<T> {

    private Object[] array;
    private int size;

    public Array(int size) {
        if (size < 0) {
            throw new NegativeSizeException(size);
        }
        array = new Object[size];
        this.size = size;
    }

    private void checkIfDestroyed() {
        if (this.array == null) {
            throw new DestroyedDataStructureException();
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= this.length()) {
            throw new IndexNotInBoundsException(index, this.length());
        }
    }

    public Iterator<T> iterator() {
        this.checkIfDestroyed();
        return new Itr();
    }

    public ArrayIterator<T> arrayIterator() {
        this.checkIfDestroyed();
        return new ArrayItr();
    }

    public ArrayIterator<T> arrayIterator(int index) {
        this.checkIfDestroyed();
        return new ArrayItr(index);
    }

    private class Itr implements Iterator<T> {
        int cursor;
        boolean headNext;

        private Itr() {
            cursor = 0;
            headNext = false;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (cursor < 0) {
                cursor = 0;
            }
            return (T)array[cursor++];
        }

        @Override
        public void remove() {
            array[cursor] = null;
        }
    }

    private class ArrayItr extends Itr implements ArrayIterator<T> {

        private ArrayItr() {
            super();
        }

        private ArrayItr(int index) {
            Array.this.checkBounds(index);
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != -1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T previous() {
            if (cursor >= size) {
                cursor = size - 1;
            }
            return (T)array[cursor--];
        }

        @Override
        public void setIndex(int index) {
            Array.this.checkBounds(index);
            cursor = index;
        }
    }

    private boolean hasFreeSpot() {
        this.checkIfDestroyed();
        for (Object o : this.array) {
            if (o == null) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmpty() {
        this.checkIfDestroyed();
        for (Object o : array) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    public int length() {
        return size;
    }

    @Override
    public void add(T element) {
        this.checkIfDestroyed();
        if (!this.hasFreeSpot()) {
            throw new FullDataStructureException();
        }
        else {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    array[i] = element;
                    break;
                }
            }
        }
    }

    public void add(T element, int index) {
        this.checkIfDestroyed();
        this.checkBounds(index);
        array[index] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        this.checkIfDestroyed();
        this.checkBounds(index);
        return (T)array[index];
    }

    public boolean contains(T element) {
        this.checkIfDestroyed();

        for (Object o : array) {
            if (o == element) {
                return true;
            }
            else if (o != null) {
                if (o.equals(element)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void remove(int index) {
        this.checkIfDestroyed();
        this.checkBounds(index);
        array[index] = null;
    }

    @Override
    public void remove(T element) {
        this.checkIfDestroyed();

        if (element != null) {
            for (int i = 0; i < this.size; i++) {
                if ((array[i] == element) || (element.equals(array[i]))) {
                    array[i] = null;
                }
            }
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
    }
}
