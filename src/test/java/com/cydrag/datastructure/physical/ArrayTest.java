package com.cydrag.datastructure.physical;

import com.cydrag.datastructure.exceptions.FullDataStructureException;
import com.cydrag.datastructure.exceptions.IndexNotInBoundsException;
import com.cydrag.datastructure.exceptions.NegativeValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class ArrayTest {

    private Array<Integer> array;

    @BeforeEach
    public void initialize() {
        this.array = new Array<>(5);
        this.array.add(1);
        this.array.add(2);
        this.array.add(3);
        this.array.add(4);
    }

    @AfterEach
    public void destroy() {
        this.array.clear();
    }

    @Test
    public void initializationTest() {
        Assertions.assertThrows(NegativeValueException.class, () -> {
            Array<Integer> array = new Array<>(-3);
        });
        Assertions.assertDoesNotThrow(() -> {
            Array<Integer> array = new Array<>(0);
            Array<Integer> array1 = new Array<>(5);
        });
    }

    @Test
    public void iteratorTest() {
        Iterator<Integer> iterator = this.array.iterator();

        for (int i = 0; i < this.array.size() - 1; i++) {
            Assertions.assertEquals(i + 1, iterator.next());
        }
        Assertions.assertNull(iterator.next());
        Assertions.assertThrows(Exception.class, iterator::next);
    }

    @Test
    public void arrayIteratorTest() {
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.array.arrayIterator(10);
        });

        ArrayIterator<Integer> arrayIterator = this.array.arrayIterator(2);

        for (int i = 3; i >= 1; i--) {
            Assertions.assertEquals(i, arrayIterator.previous());
        }

        Assertions.assertThrows(Exception.class, arrayIterator::previous);
    }

    @Test
    public void addAndGetWithIndexTest() {
        this.array.add(5, 4);
        Assertions.assertEquals(5, this.array.get(4));

        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.array.get(5);
        });

        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.array.add(20, -1);
        });
    }

    @Test
    public void removeIndexTest() {
        Assertions.assertThrows(IndexNotInBoundsException.class, () -> {
            this.array.remove(5);
        });
        this.array.remove(0);
        Assertions.assertNull(this.array.get(0));
    }

    @Test
    public void addTest() {
        this.array.add(null);
        this.array.add(5);

        Assertions.assertThrows(FullDataStructureException.class, () -> {
            this.array.add(6);
        });

        for (int i = 1; i < 5; i++) {
            Assertions.assertEquals(i, this.array.get(i - 1));
        }
    }

    @Test
    public void removeValueTest() {
        this.array.remove(null);
        this.array.remove(new Integer(4));
        Assertions.assertNull(this.array.get(3));
    }

    @Test
    public void containsTest() {
        Assertions.assertTrue(this.array.contains(4));
        Assertions.assertTrue(this.array.contains(null));
        Assertions.assertFalse(this.array.contains(5));
    }

    @Test
    public void sizeTest() {
        Assertions.assertEquals(5, this.array.size());
    }

    @Test
    public void isEmpty() {
        Assertions.assertFalse(this.array.isEmpty());
        for (int i = 0; i < this.array.size(); i++) {
            this.array.add(null, i);
        }
        Assertions.assertTrue(this.array.isEmpty());
    }

    @Test
    public void clear() {
        this.array.clear();
        Assertions.assertTrue(this.array.isEmpty());
    }
}
