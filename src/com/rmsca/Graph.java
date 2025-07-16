package com.rmsca;

import java.util.ArrayDeque;
import java.util.ArrayList;
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

    public Edge getEdge(String source, String dest) {
        if (this.graph.containsKey(source) && this.graph.get(source).containsKey(dest)) 
            return this.graph.get(source).get(dest);
        
        return null;
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
        ArrayList<String> fullPathList = new ArrayList<>(fullPath);
        int pathSize = fullPathList.size();
        int numEdges = pathSize-1;
        int count = 0;  // this variable is used to count how many edges have slots assigned to them
        int startIndex = 0; // index where first slot is assigned

        for (int i=0; i<numEdges; i=(i+1)%(numEdges)) {
            String currNode = fullPathList.get(i);
            String nextNode = fullPathList.get(i+1);
            Edge currEdge = getEdge(currNode, nextNode);

            System.out.println(currNode + " " + nextNode);

            if (canAssignSlots(currEdge, startIndex, numSlots)) ++count;
            else {
                count = 0;
                startIndex = findStartIndex(currEdge, startIndex, numSlots);
                if (startIndex == -1)   return false;
            }

            if (count == numEdges) {
                finalizeSlots(fullPathList, startIndex, numSlots);
                return true;
            }
        }
        return false;
    }

    public boolean canAssignSlots(Edge currEdge, int startIndex, int numSlots) {
        boolean[] spectrum = currEdge.getSpectrum();
        for (int i=startIndex; i<numSlots; ++i) {
            if (spectrum[i] == true)    return false;
        }
        return true;
    }

    public void finalizeSlots(ArrayList<String> fullPathList, int startIndex, int numSlots) {
        int numEdges = fullPathList.size()-1;
        for (int i=0; i<numEdges; ++i) {
            String currNode = fullPathList.get(i);
            String nextNode = fullPathList.get(i+1);
            Edge currEdge = getEdge(currNode, nextNode);

            boolean[] spectrum = currEdge.getSpectrum();
            int endPoint = startIndex + numSlots;

            for (int j=startIndex; j<endPoint; ++j) spectrum[j] = true;
            System.out.println("Edge (" + currNode + " --> " + nextNode + "): assigned slots: " + 
                                "from " + startIndex + " to " + (endPoint - 1));
        }
    }

    public int findStartIndex(Edge currEdge, int startIndex, int numSlots) {
        boolean[] spectrum = currEdge.getSpectrum();
        int i = startIndex;
        while (i < spectrum.length && spectrum[i] == false) ++i;    // find first assigned slot
        while (i < spectrum.length && spectrum[i] == true) ++i;    // find last contigous assigned slot

        if (spectrum.length - i < numSlots) return -1;

        return i;
    }
    
}
