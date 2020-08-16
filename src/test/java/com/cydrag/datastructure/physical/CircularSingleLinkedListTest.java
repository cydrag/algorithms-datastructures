package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.IndexNotInBoundsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class CircularSingleLinkedListTest {

    private CircularSingleLinkedList<Integer> circularSingleLinkedList;

    @BeforeEach
    public void initialization() {
        this.circularSingleLinkedList = new CircularSingleLinkedList<>();
        this.circularSingleLinkedList.add(0);
    }

    @AfterEach
    public void destroy() {
        this.circularSingleLinkedList.clear();
    }

    @Test
    public void iteratorTest() {
        this.circularSingleLinkedList.add(1);
        this.circularSingleLinkedList.add(2);
        this.circularSingleLinkedList.addAtEnd(3);

        Iterator<Integer> iterator = this.circularSingleLinkedList.iterator();

        for (int i = 0; i < this.circularSingleLinkedList.size(); i++) {
            Assertions.assertEquals(i, iterator.next());
        }

        Assertions.assertEquals(0, iterator.next());
    }

    @Test
    public void loopIteratorTest() {
        this.circularSingleLinkedList.add(1);
        this.circularSingleLinkedList.add(2);
        this.circularSingleLinkedList.add(3);

        int i = 0;
        int count;

        Iterator<Integer> loopIterator = this.circularSingleLinkedList.loopIterator();

        while (i < 7) {
            count = i % 4;
            Assertions.assertEquals(count, loopIterator.next());
            i++;
        }

        this.circularSingleLinkedList.add(4);
        Assertions.assertThrows(ConcurrentChangeException.class, loopIterator::next);
    }

    @Test
    public void addAndGetIndexTest() {
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.circularSingleLinkedList.add(4, 2);
        });

        this.circularSingleLinkedList.add(5, 1);
        Assertions.assertEquals(5, this.circularSingleLinkedList.get(1));
        this.circularSingleLinkedList.add(1, 0);
        Assertions.assertEquals(1, this.circularSingleLinkedList.get(0));
        Assertions.assertEquals(0, this.circularSingleLinkedList.get(1));
    }

    @Test
    public void removeTest() {
        this.circularSingleLinkedList.remove(new Integer(1));
        this.circularSingleLinkedList.remove(new Integer(0));
        Assertions.assertEquals(0, this.circularSingleLinkedList.size());
        this.circularSingleLinkedList.add(1);
        this.circularSingleLinkedList.add(2);
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.circularSingleLinkedList.remove(2);
        });
        this.circularSingleLinkedList.remove(1);
        Assertions.assertEquals(1, this.circularSingleLinkedList.get(0));
    }

    @Test
    public void sizeTest() {
        Assertions.assertEquals(1, this.circularSingleLinkedList.size());
        this.circularSingleLinkedList.add(2);
        this.circularSingleLinkedList.add(3);
        Assertions.assertEquals(3, this.circularSingleLinkedList.size());
        this.circularSingleLinkedList.remove(new Integer(2));
        Assertions.assertEquals(2, this.circularSingleLinkedList.size());
        this.circularSingleLinkedList.clear();
        Assertions.assertEquals(0, this.circularSingleLinkedList.size());
    }

    @Test
    public void containsTest() {
        Assertions.assertTrue(this.circularSingleLinkedList.contains(0));
        this.circularSingleLinkedList.remove(new Integer(0));
        Assertions.assertFalse(this.circularSingleLinkedList.contains(0));
        Assertions.assertFalse(this.circularSingleLinkedList.contains(null));
        this.circularSingleLinkedList.addAtEnd(0);
        this.circularSingleLinkedList.addAtEnd(1);
        this.circularSingleLinkedList.addAtEnd(2);
        Assertions.assertTrue(this.circularSingleLinkedList.contains(2));
    }

    @Test
    public void isEmptyTest() {
        Assertions.assertFalse(this.circularSingleLinkedList.isEmpty());
        this.circularSingleLinkedList.add(2);
        this.circularSingleLinkedList.remove(new Integer(0));
        Assertions.assertFalse(this.circularSingleLinkedList.isEmpty());
        this.circularSingleLinkedList.remove(new Integer(2));
        Assertions.assertTrue(this.circularSingleLinkedList.isEmpty());
    }

    @Test
    public void clearTest() {
        Assertions.assertFalse(this.circularSingleLinkedList.isEmpty());
        this.circularSingleLinkedList.add(2);
        this.circularSingleLinkedList.add(3);
        this.circularSingleLinkedList.clear();
        Assertions.assertTrue(this.circularSingleLinkedList.isEmpty());
    }
}
