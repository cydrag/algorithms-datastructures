package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.nodes.Node;

import java.util.Objects;

public class SinglyLinkedList<T> extends LinkedListBase<T> {

    public SinglyLinkedList() {
        super();
    }

    @Override
    protected void addHook(T value, int index) {
        Node<T> newNode = new Node<>(value);

        if (this.head == null) {
            this.head = this.tail = newNode;
        }
        else if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        else if (index == this.size()) {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        else {
            Node<T> current = this.head;
            Node<T> previous = current;
            int count = 0;

            while (count < index) {
                previous = current;
                current = current.getNext();
                count++;
            }

            newNode.setNext(current);
            previous.setNext(newNode);
        }
    }

    @Override
    protected void removeHook(T value) {
        if (Objects.equals(this.head.getData(), value)) {
            if (this.head.getNext() == null) {
                this.tail = this.head = null;
            }
            else {
                Node<T> previous = this.head;
                this.head = this.head.getNext();
                previous.setNext(null);
            }
        }
        else {
            Node<T> previous = this.head;
            Node<T> current = this.head;

            while (current != null) {
                if (Objects.equals(current.getData(), value)) {

                    if (current == this.tail) {
                        this.tail = previous;
                    }

                    previous.setNext(current.getNext());
                    current.setNext(null);
                    break;
                }
                previous = current;
                current = current.getNext();
            }
        }
    }

    @Override
    protected void clearHook() {
        this.head = this.tail = null;
    }
}
