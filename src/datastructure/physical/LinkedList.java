package datastructure.physical;

public interface LinkedList<T> extends PhysicalDataStructure<T> {
    void addAtStart(T value);
    void addAtEnd(T value);
    void removeAtStart();
    void removeAtEnd();
    T getAtStart();
    T getAtEnd();
}
