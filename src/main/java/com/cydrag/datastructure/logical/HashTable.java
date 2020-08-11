package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.DataStructure;
import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.HashNode;
import com.cydrag.datastructure.physical.Array;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SingleLinkedList;

/**
 * Currently no null key support, some operations must be changed if null key is allowed
 */
public class HashTable<K, V> implements DataStructure<K> {

    private final Array<LinkedList<HashNode<K, V>>> hashtable;
    private int numOfElements;

    private static final int BUCKET_SIZE = 50;

    public HashTable() {
        this.hashtable = new Array<>(BUCKET_SIZE);
        this.numOfElements = 0;

        for (int i = 0; i < BUCKET_SIZE; i++) {
            this.hashtable.add(new SingleLinkedList<>(), i);
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
        LinkedList<HashNode<K, V>> bucket = this.hashtable.get(index);

        V returnValue = null;

        if (bucket.isEmpty()) {
            bucket.addAtEnd(new HashNode<>(key, value));
            this.numOfElements++;
        }
        else {
            boolean exists = false;

            for (HashNode<K, V> node : bucket) {
                if ((node.getKey() == key) || (node.getKey().equals(key))) {
                    returnValue = node.getValue();
                    node.setValue(value);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                bucket.addAtEnd(new HashNode<>(key, value));
                this.numOfElements++;
            }
        }

        return returnValue;
    }

    public V getAndRemove(K key) {
        this.checkIfNull(key);

        int index = this.bucketIndex(key);
        LinkedList<HashNode<K, V>> bucket = this.hashtable.get(index);

        V value = null;
        HashNode<K, V> foundNode = null;

        for (HashNode<K, V> node : bucket) {
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
        LinkedList<HashNode<K, V>> bucket = this.hashtable.get(index);

        for (HashNode<K, V> node : bucket) {
            if ((node.getKey() == key) || (node.getKey().equals(key))) {
                return node.getValue();
            }
        }

        return null;
    }

    public int length() {
        return this.numOfElements;
    }

    public LinkedList<K> keys() {

        LinkedList<K> keys = new SingleLinkedList<>();

        for (LinkedList<HashNode<K, V>> bucket : this.hashtable) {
            if (!bucket.isEmpty()) {
                for (HashNode<K, V> node : bucket) {
                    keys.addAtEnd(node.getKey());
                }
            }
        }

        return keys;
    }

    @Override
    public void remove(K key) {
        this.getAndRemove(key);
    }

    @Override
    public boolean contains(K key) {
        this.checkIfNull(key);

        int index = this.bucketIndex(key);
        LinkedList<HashNode<K, V>> bucket = this.hashtable.get(index);

        boolean hasKey = false;

        for (HashNode<K, V> node : bucket) {
            if ((node.getKey() == key) || (node.getKey().equals(key))) {
                hasKey = true;
                break;
            }
        }

        return hasKey;
    }

    @Override
    public boolean isEmpty() {
        return this.numOfElements == 0;
    }

    @Override
    public void clear() {
        if (!this.isEmpty()) {
            for (LinkedList<HashNode<K, V>> bucket : this.hashtable) {
                bucket.clear();
            }
            this.numOfElements = 0;
        }
    }
}
