package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.FullDataStructureException;
import com.cydrag.datastructure.exceptions.IllegalCapacityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedQueueTest {

    private FixedQueue<Integer> fixedQueue;

    @BeforeEach
    public void initialization() {
        this.fixedQueue = new FixedQueue<>(5);
        this.fixedQueue.enqueue(1);
        this.fixedQueue.enqueue(2);
        this.fixedQueue.enqueue(3);
        this.fixedQueue.enqueue(4);
    }

    @Test
    public void specInit() {
        Assertions.assertThrows(IllegalCapacityException.class, () -> new FixedQueue<Integer>(0));
    }

    @Test
    public void specialCase() {
        FixedQueue<Integer> specialQueue = new FixedQueue<>(1);
        specialQueue.enqueue(4);
        Assertions.assertThrows(FullDataStructureException.class, () -> {
            specialQueue.enqueue(2);
        });
        Assertions.assertEquals(4, specialQueue.dequeue());
        Assertions.assertTrue(specialQueue.isEmpty());
    }

    @Test
    public void sizeAndContainsTest() {
        Assertions.assertTrue(this.fixedQueue.contains(3));
        Assertions.assertEquals(5, this.fixedQueue.size());
    }

    @Test
    public void enqueueDequeueTest() {
        this.fixedQueue.enqueue(5);

        Assertions.assertThrows(FullDataStructureException.class, () -> {
            this.fixedQueue.enqueue(6);
        });

        int i = 1;
        while (!this.fixedQueue.isEmpty()) {
            Assertions.assertEquals(i++, this.fixedQueue.dequeue());
        }

        this.fixedQueue.enqueue(1);
        this.fixedQueue.enqueue(2);
        this.fixedQueue.enqueue(3);
        this.fixedQueue.enqueue(4);
        this.fixedQueue.enqueue(5);

        this.fixedQueue.dequeue();

        Assertions.assertThrows(FullDataStructureException.class, () -> {
            this.fixedQueue.enqueue(7);
        });
    }

    @Test
    public void isEmptyTest() {
        this.fixedQueue.dequeue();
        Assertions.assertFalse(this.fixedQueue.isEmpty());

        while (!this.fixedQueue.isEmpty()) {
            this.fixedQueue.dequeue();
        }

        Assertions.assertTrue(this.fixedQueue.isEmpty());
    }

    @Test
    public void clearAndFullTest() {
        Assertions.assertFalse(this.fixedQueue.isFull());
        this.fixedQueue.enqueue(5);
        Assertions.assertTrue(this.fixedQueue.isFull());
        this.fixedQueue.clear();
        Assertions.assertTrue(this.fixedQueue.isEmpty());
    }
}
