package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.EmptyDataStructureException;
import com.cydrag.datastructure.exceptions.FullDataStructureException;
import com.cydrag.datastructure.exceptions.IllegalCapacityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularQueueTest {

    private CircularQueue<Integer> queue = new CircularQueue<>(5);

    @BeforeEach
    public void initialization() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
    }

    @AfterEach
    public void destroy() {
        queue.clear();
    }

    @Test
    void initializationTest() {
        Assertions.assertThrows(IllegalCapacityException.class, () -> new CircularQueue<>(0));
    }

    @Test
    public void queueAndDequeueTest() {
        queue.dequeue();
        queue.enqueue(6);

        for (int i = 2; i < 7; i++) {
            Assertions.assertEquals(i, queue.dequeue());
        }

        Assertions.assertThrows(EmptyDataStructureException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    public void fullTest() {
        Assertions.assertTrue(queue.isFull());
        Assertions.assertThrows(FullDataStructureException.class, () -> {
            queue.enqueue(7);
        });
        queue.dequeue();
        Assertions.assertFalse(queue.isFull());
    }

    @Test
    public void isEmptyTest() {
        Assertions.assertFalse(queue.isEmpty());
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
        }

        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    public void clearTest() {
        queue.clear();
        Assertions.assertTrue(queue.isEmpty());
    }
}
