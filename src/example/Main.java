package example;

import datastructure.logical.BinaryTreeArray;
import datastructure.logical.FixedQueue;
import datastructure.logical.PriorityQueue;
import datastructure.logical.Queue;

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
//        BinaryTreeArray<Integer> binaryTreeArray = new BinaryTreeArray<>(5);
//
//        binaryTreeArray.add(5);
//        binaryTreeArray.add(10);
//        binaryTreeArray.add(10);
//        binaryTreeArray.add(20);
//        binaryTreeArray.add(30);
//
//        System.out.println(binaryTreeArray.isStrict());
//        System.out.println(binaryTreeArray.isFull());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.enqueue(2);
        priorityQueue.enqueue(null);
        priorityQueue.enqueue(3);
        priorityQueue.enqueue(null);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(22);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.dequeue());
        }
    }
}