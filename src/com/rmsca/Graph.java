package com.rmsca;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

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

    public DijkstraResult shortestPath(String source, String dest) {
        //Using Dijkstra's to find the shortest distance between source and destination nodes
        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, String> prev = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(
            (s1, s2) -> Integer.compare(distance.get(s1), distance.get(s2))
        );
        HashSet<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
            prev.put(node, "-");
        }
        distance.put(source, 0);
        pq.offer(source);

        while (!pq.isEmpty()) {
            String current = pq.poll();

            if (current.equals(dest))   break;
            // Loop terminated because here we find the distance to the target node and not distances to all the nodes

            if (visited.contains(current))  continue;

            Map<String, Edge> neighbors = graph.get(current);
            
            // Performing edge relaxations for all edges from current vertex
            for (String neighbor : neighbors.keySet()) {
                if (visited.contains(neighbor)) continue;
                int temp = distance.get(current) + neighbors.get(neighbor).getWeight();

                if (temp < distance.get(neighbor)) {
                    distance.put(neighbor, temp);
                    prev.put(neighbor, current);
                    pq.offer(neighbor);
                }
            }

            visited.add(current);
        }

        DijkstraResult res = new DijkstraResult(source, dest, distance.get(dest), prev);

        return res;
    }

    public boolean assignSlots(ArrayDeque<String> fullPath, int numSlots) {
        // TO-DO: code the logic to assign slots
        return false;
    }
}
