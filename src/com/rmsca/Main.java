package com.rmsca;

import java.util.HashMap;
import java.util.Map;

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

        HashMap<String, String> res = graph.shortestPath("D", "F");
        for (Map.Entry<String, String> entry : res.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
