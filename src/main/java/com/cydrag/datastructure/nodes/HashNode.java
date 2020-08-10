package com.cydrag.datastructure.nodes;

public class HashNode<K, V> {

    private final K key;
    private V value;
    private HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashNode<K, V> getNext() {
        return this.next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
}
