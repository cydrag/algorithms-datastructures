package datastructure.logical;

public interface Queue<T> extends Orderable<T> {
    @Override
    default void add(T element) {
        this.enqueue(element);
    }

    @Override
    default T remove() {
        return this.dequeue();
    }

    void enqueue(T element);
    T dequeue();
}
