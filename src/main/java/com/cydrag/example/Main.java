package com.cydrag.example;

import com.cydrag.datastructure.logical.*;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.nodes.WeightedVertex;

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

    private static void test2() {
        Person p1 = new Person("Adam", 21.00d);
        Person p2 = new Person("Adam", 32.00d);
        Person p3 = new Person("Adam", 33.00d);

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

        WeightedVertex<String> a = new WeightedVertex<>("A");
        WeightedVertex<String> b = new WeightedVertex<>("B");
        WeightedVertex<String> c = new WeightedVertex<>("C");
        WeightedVertex<String> d = new WeightedVertex<>("D");
        WeightedVertex<String> e = new WeightedVertex<>("E");

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

        for (WeightedVertex<String> weightedVertex : graph.shortestPathDijkstra(a, c)) {
            System.out.println(weightedVertex.getData());
        }
    }

    public static void main(String[] args) {
        WeightedGraph<String> graph = new DirectedWeightedGraph<>();

        WeightedVertex<String> v1 = new WeightedVertex<>("1");
        WeightedVertex<String> v2 = new WeightedVertex<>("2");
        WeightedVertex<String> v3 = new WeightedVertex<>("3");
        WeightedVertex<String> v4 = new WeightedVertex<>("4");
        WeightedVertex<String> v5 = new WeightedVertex<>("5");
        WeightedVertex<String> v6 = new WeightedVertex<>("6");
        WeightedVertex<String> v7 = new WeightedVertex<>("7");

        graph.addAll(v1, v2, v3, v4, v5, v6, v7);

//        graph.addEdge(a, a, 6);
        graph.addEdge(v1, v2, 6);
        graph.addEdge(v1, v3, 5);
        graph.addEdge(v1, v4, 5);
        graph.addEdge(v2, v5, -1);
        graph.addEdge(v3, v2, -2);
        graph.addEdge(v3, v5, 1);
        graph.addEdge(v4, v3, -2);
        graph.addEdge(v4, v6, -1);
        graph.addEdge(v5, v7, 3);
        graph.addEdge(v6, v7, 3);

//        graph.remove(a);
//        graph.add(a);

        for (WeightedVertex<String> weightedVertex : graph.shortestPathBellmanFord(v1, v7)) {
            System.out.println(weightedVertex.getData());
        }
    }
}