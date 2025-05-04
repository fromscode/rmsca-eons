package com.rmsca;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Map<String, Edge>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void setGraph(Map<String, Map<String, Edge>> graph) {
        this.graph = graph;
    }

    public Map<String, Map<String, Edge>> getGraph() {
        return this.graph;
    }

    public void addEdge(String source, String destination, int weight) {
        Edge edge = new Edge(source, destination, weight);

        if (!graph.containsKey(source)) {
            Map<String, Edge> map = new HashMap<>();
            map.put(destination, edge);
            graph.put(source, map);
        }
        else {
            graph.get(source).put(destination, edge);
        }

        if (!graph.containsKey(destination)) {
            Map<String, Edge> map = new HashMap<>();
            map.put(source, edge);
            graph.put(destination, map);
        }
        else {
            graph.get(destination).put(source, edge);
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (String source : graph.keySet()) {
            output += source + ": ";
            Map<String, Edge> map = graph.get(source);
            for (String dest : map.keySet()) {
                output += "(" + dest+ ",(" + map.get(dest).getFrom() + ", " + map.get(dest).getTo() + ","
                + map.get(dest).getWeight() + ")),  ";
            
            }
            output += "\n";
        }
        return output;
    }
}
