package com.cydrag.example;

import com.cydrag.datastructure.logical.*;
import com.cydrag.datastructure.nodes.Vertex;
import com.cydrag.datastructure.nodes.WeightedVertex;
import com.cydrag.datastructure.physical.BidirectionalIterator;
import com.cydrag.datastructure.physical.CircularDoubleLinkedList;
import com.cydrag.datastructure.physical.DoubleLinkedList;
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

    }
}