package example;

import datastructure.logical.FixedQueue;
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

    }
}