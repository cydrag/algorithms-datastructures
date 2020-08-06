package example;

import datastructure.logical.BinaryTree;
import datastructure.logical.FixedQueue;
import datastructure.logical.Queue;
import datastructure.logical.RegularBinaryTree;

public class Main {

    private void test() {
        Queue<Integer> queue = new FixedQueue<>(3); // CircularQueue instead of FixedQueue

        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.dequeue();
        queue.enqueue(4);
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new RegularBinaryTree<>();

        binaryTree.add(1);
        binaryTree.add(2);
        binaryTree.add(3);
        System.out.println(binaryTree.isFull());
    }
}