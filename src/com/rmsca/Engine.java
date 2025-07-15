package com.rmsca;

public class Engine {
    private Graph graph;

    public Engine(Graph graph) {
        this.graph = graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public DijkstraResult getShortestPath(String source, String destination) {
        return graph.shortestPath(source, destination);
    }
}