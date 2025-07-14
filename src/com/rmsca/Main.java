package com.rmsca;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("A", "C", 3);
        graph.addEdge("A", "D", 4);
        graph.addEdge("A", "E", 2);
        graph.addEdge("D", "E", 2);
        graph.addEdge("C", "E", 4);
        graph.addEdge("G", "E", 5);
        graph.addEdge("G", "C", 5);
        graph.addEdge("G", "F", 5);
        graph.addEdge("C", "B", 2);
        graph.addEdge("F", "B", 2);
        graph.addEdge("F", "C", 5);

        System.out.println(graph);
        System.out.println();

        String source = "D";
        String dest = "F";
        DijkstraResult res = graph.shortestPath(source, dest);
        System.out.println(res);

        System.out.println();
        System.out.print("Path from " + source + " to " + dest + ": ");
        System.out.println(res.getFullPath(source, dest));
    }
}
