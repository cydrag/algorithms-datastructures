package example;

import datastructure.physical.CircularSingleLinkedList;
import datastructure.physical.SingleLinkedList;

public class Main {

    public static void main(String[] args) {

        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();

        System.out.println(linkedList.size());

        linkedList.addAtStart(10);
        linkedList.traverse();

        System.out.println(linkedList.size());

        linkedList.addAtEnd(20);
        linkedList.traverse();

        System.out.println(linkedList.size());

        System.out.println("=============================================");

        CircularSingleLinkedList<Integer> circularSingleLinkedList = new CircularSingleLinkedList<>();

        System.out.println(circularSingleLinkedList.size());
        circularSingleLinkedList.add(1, 0);
        System.out.println(circularSingleLinkedList.size());
        circularSingleLinkedList.add(2, 1);
        System.out.println(circularSingleLinkedList.size());
        circularSingleLinkedList.add(32, 0);
        System.out.println(circularSingleLinkedList.size());

//        AVLTree<Integer> tree = new AVLTree<>();
//
//        tree.insert(40);
//        tree.insert(20);
//        tree.insert(10);
//        tree.insert(25);
//        tree.insert(30);
//        tree.insert(22);
//        tree.insert(50);
//        tree.insert(45);
//
//        tree.levelOrder().traverse();

    }


    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    static void sort(int arr[], int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

}
