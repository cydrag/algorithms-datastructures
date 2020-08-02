package example;

import datastructure.physical.CircularDoubleLinkedList;

public class Main {

    public static void main(String[] args) {

        CircularDoubleLinkedList<Integer> linkedList = new CircularDoubleLinkedList<>();

        linkedList.addAtStart(10);
        linkedList.addAtEnd(20);
//        linkedList.add(30, 1);
//        linkedList.addAtEnd(40);
//        linkedList.addAtStart(50);
        linkedList.traverse();
        System.out.println(linkedList.contains(20));
    }

}
