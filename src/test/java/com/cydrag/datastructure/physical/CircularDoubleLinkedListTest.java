package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.IndexNotInBoundsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class CircularDoubleLinkedListTest {

    private CircularDoubleLinkedList<Integer> circularDoubleLinkedList;

    @BeforeEach
    public void initialization() {
        this.circularDoubleLinkedList = new CircularDoubleLinkedList<>();
        this.circularDoubleLinkedList.add(0);
    }

    @AfterEach
    public void destroy() {
        this.circularDoubleLinkedList.clear();
    }

    @Test
    public void iteratorTest() {
        this.circularDoubleLinkedList.add(1);
        this.circularDoubleLinkedList.add(2);
        this.circularDoubleLinkedList.addAtEnd(3);

        Iterator<Integer> iterator = this.circularDoubleLinkedList.iterator();

        for (int i = 0; i < this.circularDoubleLinkedList.size(); i++) {
            Assertions.assertEquals(i, iterator.next());
        }

        Assertions.assertEquals(0, iterator.next());
    }

    @Test
    public void loopIteratorTest() {
        this.circularDoubleLinkedList.add(1);
        this.circularDoubleLinkedList.add(2);
        this.circularDoubleLinkedList.add(3);

        int i = 0;
        int count;

        Iterator<Integer> loopIterator = this.circularDoubleLinkedList.loopIterator();

        while (i < 7) {
            count = i % 4;
            Assertions.assertEquals(count, loopIterator.next());
            i++;
        }

        this.circularDoubleLinkedList.add(4);
        Assertions.assertThrows(ConcurrentChangeException.class, loopIterator::next);
    }

    @Test
    public void bidirectionalIteratorTest() {
        this.circularDoubleLinkedList.add(1);
        this.circularDoubleLinkedList.add(2);
        this.circularDoubleLinkedList.add(3);

        BidirectionalIterator<Integer> bidirectionalIterator = this.circularDoubleLinkedList.bidirectionalIterator();

        int i = 0;
        int count;

        while (i < 7) {
            count = i % 4;
            Assertions.assertEquals(count, bidirectionalIterator.next());
            i++;
        }

        i = 7;
        while (i >= 0) {
            count = i % 4;
            Assertions.assertEquals(count, bidirectionalIterator.previous());
            i--;
        }

        Assertions.assertThrows(ConcurrentChangeException.class, () -> {
            this.circularDoubleLinkedList.add(5);
            bidirectionalIterator.next();
        });
    }

    @Test
    public void addAndGetIndexTest() {
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.circularDoubleLinkedList.add(4, 2);
        });

        this.circularDoubleLinkedList.add(5, 1);
        Assertions.assertEquals(5, this.circularDoubleLinkedList.get(1));
        this.circularDoubleLinkedList.add(1, 0);
        Assertions.assertEquals(1, this.circularDoubleLinkedList.get(0));
        Assertions.assertEquals(0, this.circularDoubleLinkedList.get(1));
    }

    @Test
    public void removeTest() {
        this.circularDoubleLinkedList.remove(new Integer(1));
        this.circularDoubleLinkedList.remove(new Integer(0));
        Assertions.assertEquals(0, this.circularDoubleLinkedList.size());
        this.circularDoubleLinkedList.add(1);
        this.circularDoubleLinkedList.add(2);
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.circularDoubleLinkedList.remove(2);
        });
        this.circularDoubleLinkedList.remove(1);
        Assertions.assertEquals(1, this.circularDoubleLinkedList.get(0));
    }

    @Test
    public void sizeTest() {
        Assertions.assertEquals(1, this.circularDoubleLinkedList.size());
        this.circularDoubleLinkedList.add(2);
        this.circularDoubleLinkedList.add(3);
        Assertions.assertEquals(3, this.circularDoubleLinkedList.size());
        this.circularDoubleLinkedList.remove(new Integer(2));
        Assertions.assertEquals(2, this.circularDoubleLinkedList.size());
        this.circularDoubleLinkedList.clear();
        Assertions.assertEquals(0, this.circularDoubleLinkedList.size());
    }

    @Test
    public void containsTest() {
        Assertions.assertTrue(this.circularDoubleLinkedList.contains(0));
        this.circularDoubleLinkedList.remove(new Integer(0));
        Assertions.assertFalse(this.circularDoubleLinkedList.contains(0));
        Assertions.assertFalse(this.circularDoubleLinkedList.contains(null));
        this.circularDoubleLinkedList.addAtEnd(0);
        this.circularDoubleLinkedList.addAtEnd(1);
        this.circularDoubleLinkedList.addAtEnd(2);
        Assertions.assertTrue(this.circularDoubleLinkedList.contains(2));
    }

    @Test
    public void isEmptyTest() {
        Assertions.assertFalse(this.circularDoubleLinkedList.isEmpty());
        this.circularDoubleLinkedList.add(2);
        this.circularDoubleLinkedList.remove(new Integer(0));
        Assertions.assertFalse(this.circularDoubleLinkedList.isEmpty());
        this.circularDoubleLinkedList.remove(new Integer(2));
        Assertions.assertTrue(this.circularDoubleLinkedList.isEmpty());
    }

    @Test
    public void clearTest() {
        Assertions.assertFalse(this.circularDoubleLinkedList.isEmpty());
        this.circularDoubleLinkedList.add(2);
        this.circularDoubleLinkedList.add(3);
        this.circularDoubleLinkedList.clear();
        Assertions.assertTrue(this.circularDoubleLinkedList.isEmpty());
    }
}
