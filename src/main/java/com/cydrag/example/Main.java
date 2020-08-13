package com.cydrag.example;

import com.cydrag.datastructure.logical.*;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.physical.LinkedList;

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

        Vertex<String> bob = new Vertex<>("Bob");
        Vertex<String> marie = new Vertex<>("Marie");
        Vertex<String> nicolai = new Vertex<>("Nicolai");
        Vertex<String> ricardo = new Vertex<>("Ricardo");

        graph.add(bob);
        graph.add(marie);
        graph.add(nicolai);
        graph.add(ricardo);

        graph.addEdge(bob, marie);
        graph.addEdge(bob, nicolai);
        graph.addEdge(bob, bob);

//        graph.addEdge(marie, nicolai);
//        graph.addEdge(ricardo, marie);

        LinkedList<Vertex<String>> shortestPath = graph.shortestPathBreadthFirstSearch(bob, bob);

        System.out.println(shortestPath.length());

        for (Vertex<String> vert : shortestPath) {
            System.out.println(vert.getData());
        }
    }
}