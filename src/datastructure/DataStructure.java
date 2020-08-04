package datastructure;

public interface DataStructure<T> {
    void add(T element);
    void remove(T element);
    boolean contains(T element);
    boolean isEmpty();
    void destroy();
}
