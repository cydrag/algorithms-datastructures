package datastructure.nodes;

public class Node<T> {

    private T data;
    private Node<T> next;
    private Node<T> previous;

    public Node() {
        this.data = null;
        this.next = this.previous = null;
    }

    public Node(T data) {
        this.data = data;
        this.next = this.previous = null;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
