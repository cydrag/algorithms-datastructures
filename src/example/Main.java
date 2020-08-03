package example;

import datastructure.physical.SingleLinkedList;

public class Main {

    public static void main(String[] args) {

        Person p1 = new Person("Predrag", 100.0d);
        Person p2 = new Person("Predrag", 100.0d);

        SingleLinkedList<Person> linkedList = new SingleLinkedList<>();

        linkedList.addAtEnd(p1);
        linkedList.addAtEnd(null);
        linkedList.addAtEnd(p2);

    }
}