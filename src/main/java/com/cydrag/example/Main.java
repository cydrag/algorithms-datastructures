package com.cydrag.example;

import com.cydrag.datastructure.logical.BinaryHeap;

import java.util.Comparator;

public class Main {

    private static void test() {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(10, Comparator.reverseOrder());

        binaryHeap.add(40);
        binaryHeap.add(17);
        binaryHeap.add(36);
        binaryHeap.add(7);
        binaryHeap.add(8);
        binaryHeap.add(5);
        binaryHeap.add(4);

        System.out.println("Removed: " + binaryHeap.remove());

        for (Integer num : binaryHeap.levelOrder()) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {


    }
}