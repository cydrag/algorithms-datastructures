package example;

import algorithm.Algorithm;
import datastructure.physical.Array;
import datastructure.physical.SingleLinkedList;

public class Main {

    public static void main(String[] args) {

        Person p1 = new Person("Predrag", 100.0d);
        Person p2 = new Person("Predrag", 100.0d);

        SingleLinkedList<Person> linkedList = new SingleLinkedList<>();

        linkedList.addAtEnd(p1);
        linkedList.addAtEnd(null);
        linkedList.addAtEnd(p2);

        Array<Integer> array = new Array<>(3);
        array.add(2);
        array.add(3);
        array.add(4);
        System.out.println(Algorithm.toString(array));
    }
}