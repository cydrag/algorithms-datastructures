package datastructure.physical;

public interface LinkedList<T> extends PhysicalDataStructure<T> {
    void addAtStart(T element);
    void addAtEnd(T element);
    void removeAtStart();
    void removeAtEnd();
    void destroy();
}
