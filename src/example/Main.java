package example;

import datastructure.logical.RegularBinaryTree;

public class Main {

    public static void main(String[] args) {

        RegularBinaryTree<Integer> binarySearchTree = new RegularBinaryTree<>();
        binarySearchTree.add(100);
        binarySearchTree.add(80);
        binarySearchTree.add(200);
        binarySearchTree.add(150);
        binarySearchTree.add(300);
        binarySearchTree.add(160);
        binarySearchTree.add(155);
        binarySearchTree.add(170);

        binarySearchTree.remove(170);

        System.out.println(binarySearchTree.isFull());

        for (Integer num : binarySearchTree.levelOrder()) {
            System.out.println(num);
        }

    }
}