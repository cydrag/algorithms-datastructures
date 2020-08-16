package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.ConcurrentChangeException;
import com.cydrag.datastructure.exceptions.IndexNotInBoundsException;
import com.cydrag.datastructure.exceptions.NullValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class SingleLinkedListTest {

    private SingleLinkedList<Integer> linkedList;

    @BeforeEach
    public void initialization() {
        this.linkedList = new SingleLinkedList<>();
        this.linkedList.add(1);
    }

    @AfterEach
    public void destroy() {
        this.linkedList.clear();
    }

    @Test
    public void iteratorTest() {
        this.linkedList.addAtStart(0);
        this.linkedList.add(2);
        this.linkedList.addAtEnd(3);

        Iterator<Integer> iterator = this.linkedList.iterator();

        for (int i = 0; i < this.linkedList.size(); i++) {
            Assertions.assertEquals(i, iterator.next());
        }

        Assertions.assertThrows(NullValueException.class, iterator::next);

        linkedList.addAtStart(2);
        Assertions.assertThrows(ConcurrentChangeException.class, iterator::next);
    }

    @Test
    public void addAndGetIndexTest() {
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.linkedList.add(4, 2);
        });

        this.linkedList.add(5, 1);
        Assertions.assertEquals(5, this.linkedList.get(1));
        this.linkedList.add(0, 0);
        Assertions.assertEquals(0, this.linkedList.get(0));
        Assertions.assertEquals(1, this.linkedList.get(1));
    }

    @Test
    public void removeTest() {
        this.linkedList.remove(new Integer(1));
        Assertions.assertEquals(0, this.linkedList.size());
        this.linkedList.add(1);
        this.linkedList.add(2);
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.linkedList.remove(2);
        });
        this.linkedList.remove(1);
        Assertions.assertEquals(1, this.linkedList.get(0));
    }

    @Test
    public void sizeTest() {
        Assertions.assertEquals(1, this.linkedList.size());
        this.linkedList.add(2);
        this.linkedList.add(3);
        Assertions.assertEquals(3, this.linkedList.size());
        this.linkedList.remove(new Integer(1));
        Assertions.assertEquals(2, this.linkedList.size());
        this.linkedList.clear();
        Assertions.assertEquals(0, this.linkedList.size());
    }

    @Test
    public void containsTest() {
        Assertions.assertTrue(this.linkedList.contains(1));
        this.linkedList.remove(new Integer(1));
        Assertions.assertFalse(this.linkedList.contains(1));
        Assertions.assertFalse(this.linkedList.contains(null));
        this.linkedList.addAtEnd(0);
        this.linkedList.addAtEnd(1);
        this.linkedList.addAtEnd(2);
        Assertions.assertTrue(this.linkedList.contains(2));
    }

    @Test
    public void isEmptyTest() {
        Assertions.assertFalse(this.linkedList.isEmpty());
        this.linkedList.add(2);
        this.linkedList.remove(new Integer(1));
        Assertions.assertFalse(this.linkedList.isEmpty());
        this.linkedList.remove(new Integer(2));
        Assertions.assertTrue(this.linkedList.isEmpty());
    }

    @Test
    public void clearTest() {
        Assertions.assertFalse(this.linkedList.isEmpty());
        this.linkedList.add(2);
        this.linkedList.add(3);
        this.linkedList.clear();
        Assertions.assertTrue(this.linkedList.isEmpty());
    }
}
