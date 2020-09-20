package com.cydrag.example;

import com.cydrag.datastructure.logical.*;
import com.cydrag.datastructure.nodes.Vertex;

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

        for (Integer num : binaryHeap.levelOrder()) {
            System.out.print(num + " ");
        }
        System.out.println("\n =====================");

        while (!binaryHeap.isEmpty()) {
            System.out.println(binaryHeap.peek());
            binaryHeap.remove(binaryHeap.peek());
        }
    }

    private static void test2() {

        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Vertex<String> v3 = new Vertex<>("v3");
        Vertex<String> v4 = new Vertex<>("v4");

        System.out.println(Math.abs(v1.hashCode()) % 50);
        System.out.println(Math.abs(v2.hashCode()) % 50);
        System.out.println(Math.abs(v3.hashCode()) % 50);
        System.out.println(Math.abs(v4.hashCode()) % 50);

        UnweightedGraph<String> unweightedGraph = new UndirectedUnweightedGraph<>();

        unweightedGraph.add(v1);
        unweightedGraph.add(v2);
        unweightedGraph.add(v3);
        unweightedGraph.add(v4);

        unweightedGraph.addEdge(v1, v2);
        unweightedGraph.addEdge(v2, v3);
        unweightedGraph.addEdge(v4, v1);
        unweightedGraph.addEdge(v3, v4);

        unweightedGraph.remove(v3);

        for (Vertex<String> vertex : unweightedGraph.shortestPathBreadthFirstSearch(v2, v4)) {
            System.out.println(vertex.getData());
        }

        WeightedGraph<String> graph = new UndirectedWeightedGraph<>();

        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");

        System.out.println(Math.abs(a.hashCode()) % 50);
        System.out.println(Math.abs(b.hashCode()) % 50);
        System.out.println(Math.abs(c.hashCode()) % 50);
        System.out.println(Math.abs(d.hashCode()) % 50);
        System.out.println(Math.abs(e.hashCode()) % 50);

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);

//        graph.addEdge(a, a, 6);
        graph.addEdge(a, b, 6);
        graph.addEdge(a, d, 1);
        graph.addEdge(d, b, 2);
        graph.addEdge(d, e, 1);
        graph.addEdge(b, e, 2);
        graph.addEdge(b, c, 5);
        graph.addEdge(e, c, 5);

//        graph.remove(a);
//        graph.add(a);

        for (Vertex<String> vertex : graph.shortestPathDijkstra(a, c)) {
            System.out.println(vertex.getData());
        }
    }

    public static void test3() {
        WeightedGraph<String> graph = new UndirectedWeightedGraph<>();

        Vertex<String> v1 = new Vertex<>("1");
        Vertex<String> v2 = new Vertex<>("2");
        Vertex<String> v3 = new Vertex<>("3");
        Vertex<String> v4 = new Vertex<>("4");
        Vertex<String> v5 = new Vertex<>("5");
        Vertex<String> v6 = new Vertex<>("6");
        Vertex<String> v7 = new Vertex<>("7");

        graph.addAll(v1, v2, v3, v4, v5, v6, v7);

//        graph.addEdge(a, a, 6);     TODO: Think about below for BellmanFord for UndirectedWeightedGraph, works for Directed
        graph.addEdge(v1, v2, 6);
        graph.addEdge(v1, v3, 5);
        graph.addEdge(v1, v4, 5);
        graph.addEdge(v2, v5, -1); //
        graph.addEdge(v3, v2, -2); //
        graph.addEdge(v3, v5, 1);
        graph.addEdge(v4, v3, -2); //
        graph.addEdge(v4, v6, -1); //
        graph.addEdge(v5, v7, 3);
        graph.addEdge(v6, v7, 3);

//        graph.remove(a);
//        graph.add(a);

        for (Vertex<String> vertex : graph.shortestPathBellmanFord(v1, v7)) {
            System.out.println(vertex.getData());
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.add(5);
        tree.add(3);
        tree.add(4);

        for (Integer num : tree.levelOrder()) {
            System.out.println(num);
        }
    }
}