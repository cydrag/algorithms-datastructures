package example;

import datastructure.physical.CircularSingleLinkedList;

public class Main {

    public static void main(String[] args) {

        CircularSingleLinkedList<Integer> linkedList = new CircularSingleLinkedList<>();

        linkedList.addAtStart(10);
        linkedList.addAtEnd(20);
//        linkedList.addAtEnd(30);

        linkedList.remove(new Integer(20));
    }

}