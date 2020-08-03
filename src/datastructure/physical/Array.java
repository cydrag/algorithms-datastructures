package datastructure.physical;

import java.util.Iterator;

public class Array<T> implements PhysicalDataStructure<T> {

    private Object[] array;
    private int size;

    public Array(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }
        array = new Object[size];
        this.size = size;
    }

    private static void checkBounds(Array<?> array, int index) {
        if (index < 0 || index >= array.length()) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
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

        }

        private ArrayItr(int index) {
            Array.checkBounds(Array.this, index);
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
            Array.checkBounds(Array.this, index);
            cursor = index;
        }
    }

    public boolean isEmpty() {
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

    public void add(T element, int index) {
        Array.checkBounds(this, index);
        array[index] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Array.checkBounds(this, index);
        return (T)array[index];
    }

    public boolean contains(T element) {

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
        Array.checkBounds(this, index);
        array[index] = null;
    }

    @Override
    public void remove(T element) {

        if (element == null) {
            throw new NullPointerException("Removal element cannot be null.");
        }

        for (int i = 0; i < this.size; i++) {
            if ((array[i] == element) || (element.equals(array[i]))) {
                array[i] = null;
            }
        }
    }
}
