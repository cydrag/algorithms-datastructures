package com.cydrag.algorithm;

import com.cydrag.datastructure.physical.Array;

public final class Algorithm {

    private Algorithm() { }

    public static String toString(Array<?> array) {

        StringBuilder sb = new StringBuilder("[");

        int iMax = array.length() - 1;

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
}
