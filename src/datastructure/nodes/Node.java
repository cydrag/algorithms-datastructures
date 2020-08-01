package datastructure.nodes;

public class Node<T> implements SingleNode<T> {

    private T data;
    private Node<T> next;
    private Node<T> previous;

    public Node(T data) {
        this.data = data;
        this.next = this.previous = null;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public Node<T> getNext() {
        return this.next;
    }

    @Override
    public void setNext(SingleNode<T> next) {
        this.next = (Node<T>) next;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
