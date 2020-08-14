package com.cydrag.algorithm;

import com.cydrag.datastructure.logical.BinaryTree;
import com.cydrag.datastructure.physical.Array;
import com.cydrag.datastructure.physical.LinkedList;

public final class Algorithm {

    private Algorithm() { }

    public static String toString(Array<?> array) {

        StringBuilder sb = new StringBuilder("[");

        int iMax = array.size() - 1;

        for (int i = 0; i <= iMax; i++) {
            sb.append(array.get(i));
            if (i == iMax) {
                sb.append("]");
            }
            else {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public static <T> LinkedList<T> traverseInOrder(BinaryTree<T> binaryTree) {
        return binaryTree.inOrder();
    }
}
