package com.cydrag.example;

import com.cydrag.datastructure.logical.BinaryHeap;
import com.cydrag.datastructure.logical.UndirectedUnweightedGraph;
import com.cydrag.datastructure.logical.UnweightedGraph;

import java.util.Comparator;

public class Main {

    private static void test() {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(10, Comparator.reverseOrder());

        binaryHeap.add(40);
        binaryHeap.add(17);
        binaryHeap.add(36);
        binaryHeap.add(7);
        binaryHeap.add(8);
        binaryHeap.add(5);
        binaryHeap.add(4);

        System.out.println("Removed: " + binaryHeap.remove());

        for (Integer num : binaryHeap.levelOrder()) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {

        Person p1 = new Person("Adam", 21.00d);
        Person p2 = new Person("Adam", 32.00d);
        Person p3 = new Person("Adam", 33.00d);

        UnweightedGraph<String> graph = new UndirectedUnweightedGraph<>();

        System.out.println(Math.abs("Bob".hashCode()) % 50);
        System.out.println(Math.abs("Marie".hashCode()) % 50);
        System.out.println(Math.abs("Nicolai".hashCode()) % 50);

        graph.add("Bob");
        graph.add("Marie");
        graph.add("Nicolai");

        graph.addEdge("Bob", "Marie");
        graph.addEdge("Bob", "Nicolai");
        graph.addEdge("Bob", "Bob");
        graph.addEdge("Marie", "Nicolai");

        for (String vert : UnweightedGraph.bfs(graph, "Bob")) {
            System.out.println(vert);
        }
    }
}