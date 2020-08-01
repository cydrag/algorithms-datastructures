package datastructure.physical;

public interface LinkedList<T> {

    int size();
    void remove(T value);
    boolean contains(T value);
    void add(T value, int location);
    void destroy();

}
