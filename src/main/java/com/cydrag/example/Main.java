package com.cydrag.example;

import com.cydrag.datastructure.logical.BinaryHeap;
import com.cydrag.datastructure.logical.BinarySearchTree;
import com.cydrag.datastructure.logical.RegularBinaryTree;

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

        RegularBinaryTree<Integer> binaryTree = new RegularBinaryTree<>();

        binaryTree.add(1);
        binaryTree.add(2);
        binaryTree.add(3);
        binaryTree.add(4);
        binaryTree.add(5);
        binaryTree.add(6);
        binaryTree.add(7);
        binaryTree.add(8);
        binaryTree.add(9);

        binaryTree.remove(2);

        System.out.println("Hello");
    }
}