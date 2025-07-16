package com.rmsca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("A", "C", 3);
        graph.addEdge("A", "D", 4);
        graph.addEdge("A", "E", 4);
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

        Scanner sc = new Scanner(System.in);

        // System.out.print("Enter source node: ");
        // String source = sc.nextLine();
        
        // System.out.print("Enter destination node: ");
        // String dest = sc.nextLine();

        // System.out.print("Enter strength of request: ");
        // int strength = Integer.valueOf(sc.nextLine());

        Engine engine = new Engine(graph);
        // System.out.println();
        // System.out.println(engine.getShortestPath(source, dest));
        // System.out.println(engine.getSlotsRequired(source, dest, strength));

        engine.processRequest("A", "G", Integer.MAX_VALUE);
        System.out.println();
        engine.processRequest("D", "F", 100);
        System.out.println();
        engine.processRequest("A", "B", 100);
        System.out.println();
        engine.processRequest("D", "C", 100);
        System.out.println();
        engine.processRequest("A", "C", 100);
        
        sc.close();
    }
}
