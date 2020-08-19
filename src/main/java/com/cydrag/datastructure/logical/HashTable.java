package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.physical.Array;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

/**
 * Currently no null key support, some operations must be changed if null key is allowed
 */
public class HashTable<K, V> implements DataStructure<K> {

    private final Array<LinkedList<Pair<K, V>>> hashtable;
    private int numOfElements;

    private static final int BUCKET_SIZE = 50;

    public HashTable() {
        this.hashtable = new Array<>(BUCKET_SIZE);
        this.numOfElements = 0;

        for (int i = 0; i < BUCKET_SIZE; i++) {
            this.hashtable.add(new SinglyLinkedList<>(), i);
        }
    }

    private void checkIfNull(K key) {
        if (key == null) {
            throw new NullValueException();
        }
    }

    private int bucketIndex(K key) {
        this.checkIfNull(key);
        return Math.abs(key.hashCode()) % BUCKET_SIZE;
    }

    public V add(K key, V value) {
        this.checkIfNull(key);

        int index = this.bucketIndex(key);
        LinkedList<Pair<K, V>> bucket = this.hashtable.get(index);

        V returnValue = null;

        if (bucket.isEmpty()) {
            bucket.add(new Pair<>(key, value));
            this.numOfElements++;
        }
        else {
            boolean exists = false;

            for (Pair<K, V> node : bucket) {
                if ((node.getKey() == key) || (node.getKey().equals(key))) {
                    returnValue = node.getValue();
                    node.setValue(value);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                bucket.add(new Pair<>(key, value));
                this.numOfElements++;
            }
        }

        return returnValue;
    }

    public V getAndRemove(K key) {
        this.checkIfNull(key);

        int index = this.bucketIndex(key);
        LinkedList<Pair<K, V>> bucket = this.hashtable.get(index);

        V value = null;
        Pair<K, V> foundNode = null;

        for (Pair<K, V> node : bucket) {
            if ((node.getKey() == key) || (node.getKey().equals(key))) {
                foundNode = node;
                break;
            }
        }

        if (foundNode != null) {
            value = foundNode.getValue();
            bucket.remove(foundNode);
            this.numOfElements--;
        }

        return value;
    }

    public V get(K key) {
        this.checkIfNull(key);

        int index = this.bucketIndex(key);
        LinkedList<Pair<K, V>> bucket = this.hashtable.get(index);

        for (Pair<K, V> node : bucket) {
            if ((node.getKey() == key) || (node.getKey().equals(key))) {
                return node.getValue();
            }
        }

        return null;
    }

    public LinkedList<K> keys() {

        LinkedList<K> keys = new SinglyLinkedList<>();

        for (LinkedList<Pair<K, V>> bucket : this.hashtable) {
            if (!bucket.isEmpty()) {
                for (Pair<K, V> node : bucket) {
                    keys.add(node.getKey());
                }
            }
        }

        return keys;
    }

    public LinkedList<V> values() {
        LinkedList<V> values = new SinglyLinkedList<>();
        LinkedList<K> keys = this.keys();

        for (K key : keys) {
            values.add(this.get(key));
        }

        return values;
    }

    public LinkedList<Pair<K, V>> pairs() {

        LinkedList<Pair<K, V>> pairs = new SinglyLinkedList<>();

        for (LinkedList<Pair<K, V>> bucket : this.hashtable) {
            if (!bucket.isEmpty()) {
                for (Pair<K, V> pair : bucket) {
                    pairs.add(pair);
                }
            }
        }

        return pairs;
    }

    public static class Pair<K, V> {
        private final K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    @Override
    public void add(K key) {
        this.add(key, null);
    }

    @Override
    public void remove(K key) {
        this.getAndRemove(key);
    }

    @Override
    public boolean contains(K key) {
        return this.get(key) != null;
    }

    @Override
    public int size() {
        return this.numOfElements;
    }

    @Override
    public boolean isEmpty() {
        return this.numOfElements == 0;
    }

    @Override
    public void clear() {
        if (!this.isEmpty()) {
            for (LinkedList<Pair<K, V>> bucket : this.hashtable) {
                bucket.clear();
            }
            this.numOfElements = 0;
        }
    }
}
