package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;

import java.util.Iterator;

public class Set<T> implements DataStructure<T>, Iterable<T> {

    private final HashTable<T, Object> set;
    private static final Object INSERTED = new Object();

    public Set() {
        this.set = new HashTable<>();
    }

    @Override
    public Iterator<T> iterator() {
        return this.set.keys().iterator();
    }

    @Override
    public void add(T value) {
        this.set.add(value, INSERTED);
    }

    @Override
    public void remove(T value) {
        this.set.remove(value);
    }

    @Override
    public boolean contains(T value) {
        return this.set.contains(value);
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public void clear() {
        this.set.clear();
    }
}
