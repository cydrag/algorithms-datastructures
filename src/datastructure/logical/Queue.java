package datastructure.logical;

public interface Queue<T> {
    void enqueue(T element);
    T dequeue();
    boolean isEmpty();
    T peek();
    void destroy();
}
