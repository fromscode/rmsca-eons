package com.rmsca;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 20);
        graph.addEdge("B", "C", 30);
        graph.addEdge("A", "D", 40);
        graph.addEdge("C", "D", 50);
        graph.addEdge("C", "E", 60);
        graph.addEdge("B", "E", 70);
        graph.addEdge("D", "E", 80);

        System.out.println(graph);
    }
}
