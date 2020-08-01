package datastructure.nodes;

public interface SingleNode<T> {
    T getData();
    SingleNode<T> getNext();
    void setNext(SingleNode<T> next);
}
