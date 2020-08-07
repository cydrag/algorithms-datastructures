package datastructure;

public interface DataStructure<T> {
    void add(T value);
    void remove(T value);
    boolean contains(T value);
    boolean isEmpty();
    void clear();
}
