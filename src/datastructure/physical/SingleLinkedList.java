package datastructure.physical;

import datastructure.nodes.Node;

public class SingleLinkedList<T> extends LinkedListBase<T> {

    public SingleLinkedList() {
        super();
    }

    @Override
    public void add(T element, int index) {
        this.checkAddBounds(index);

        Node<T> newNode = new Node<>(element);

        if (this.head == null) {
            this.head = this.tail = newNode;
        }
        else if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        else if (index == this.length()) {
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
    public void remove(T element) {
        this.checkEmpty();

        if (this.head.getData().equals(element)) {
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
                if (current.getData().equals(element)) {

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
    public void clear() {
        this.head = this.tail = null;
    }
}
