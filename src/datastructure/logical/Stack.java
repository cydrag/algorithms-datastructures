package datastructure.logical;

public interface Stack<T> extends Orderable<T> {

    @Override
    default void add(T element) {
        this.push(element);
    }

    @Override
    default T remove() {
        return this.pop();
    }

    void push(T element);
    T pop();
}
