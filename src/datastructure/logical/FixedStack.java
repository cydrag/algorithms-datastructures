package datastructure.logical;

import datastructure.exceptions.EmptyDataStructureException;
import datastructure.exceptions.FullDataStructureException;
import datastructure.exceptions.IllegalCapacityException;

public class FixedStack<T> implements Stack<T> {

    private Object[] objects;

    private int i = -1;
    private int lastIndex;

    public FixedStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalCapacityException();
        }

        this.objects = new Object[capacity];
        this.lastIndex = capacity - 1;
    }

    @Override
    public void push(T element) {
        if (this.isFull()) {
            throw new FullDataStructureException();
        }
        else {
            this.i++;
            this.objects[this.i] = element;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        else {
            return (T)this.objects[this.i--];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyDataStructureException();
        }
        return (T)this.objects[this.i];
    }

    @Override
    public boolean isEmpty() {
        return this.i == -1;
    }

    public boolean isFull() {
        return this.i == this.lastIndex;
    }

    @Override
    public void destroy() {
        this.objects = null;
    }
}
