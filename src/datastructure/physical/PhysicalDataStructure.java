package datastructure.physical;

public interface PhysicalDataStructure<T> extends Iterable<T> {

    boolean isEmpty();
    int length();
    void add(T element);
    void add(T element, int index);
    T get(int index);
    boolean contains(T element);
    void remove(int index);
    void remove(T element);

}
