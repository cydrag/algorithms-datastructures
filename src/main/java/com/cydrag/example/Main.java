package com.cydrag.example;

import com.cydrag.datastructure.logical.AVLTree;
import com.cydrag.datastructure.logical.BinaryTree;

public class Main {

    public static void main(String[] args) {

        BinaryTree<Integer> tree = new AVLTree<>(10);

        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(55);

        System.out.println(tree.contains(20));
    }
}