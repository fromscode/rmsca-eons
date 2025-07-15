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

    public int getSlotsRequired (String source, String dest, int strength) {
        int R = strength;
        double B = 12.5;
        int SE = 0;

        DijkstraResult res = this.getShortestPath(source, dest);
        int distance = res.getDistance();

        if (distance <= 250) SE = 6;
        else if (distance <= 500) SE = 5;
        else if (distance <= 1000) SE = 4;
        else if (distance <= 2000) SE = 3;
        else if (distance <= 4000) SE = 2;
        else SE = 6;

        return  (int) Math.ceil(R / (B * SE));
    }
}