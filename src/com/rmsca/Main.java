package com.rmsca;

public class Main {
    public static void main(String[] args) {
        
        Graph graph = new Graph();
        graph.create();
        System.out.println(graph);
        System.out.println();

        Engine engine = new Engine(graph);

        engine.processRequest("A", "G", Integer.MAX_VALUE);
        System.out.println();
        engine.processRequest("D", "F", 100);
        System.out.println();
        engine.processRequest("A", "B", 100);
        System.out.println();
        engine.processRequest("D", "C", 100);
        System.out.println();
        engine.processRequest("A", "C", 100);
        System.out.println();
        engine.processRequest("C", "A", 100);
        System.out.println();
        engine.processRequest("G", "A", 100);
    }
}
