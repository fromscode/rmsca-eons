package com.rmsca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {
    private HashMap<Node, HashMap<Node, Edge>> graph;
    private HashMap<String, Node> nodes;

    public Graph() {
        this.graph = new HashMap<>();
        this.nodes = new HashMap<>();
    }

    public Edge getEdge(Node source, Node dest) {
        if (this.graph.containsKey(source) && this.graph.get(source).containsKey(dest))
            return this.graph.get(source).get(dest);

        return null;
    }

    public void create(Scanner sc) {
        System.out.println("Leave input empty to finish");
        System.out.println();
        while (true) {
            System.out.print("Enter edge (source destination weight): ");
            String input = sc.nextLine();
            if (input.isEmpty())
                break;
            String[] arr = input.split(" ");

            Node source = nodes.getOrDefault(arr[0], new Node(arr[0]));
            nodes.putIfAbsent(arr[0], source);

            Node destination = nodes.getOrDefault(arr[1], new Node(arr[1]));
            nodes.putIfAbsent(arr[1], destination);

            Edge edge = addEdge(source, destination, Integer.valueOf(arr[2]));
            System.out.println("Edge " + edge + " added!");
            System.out.println();
        }
    }

    private Edge addEdge(Node source, Node destination, int weight) {
        Edge edge = new Edge(source, destination, weight);

        if (!graph.containsKey(source)) {
            HashMap<Node, Edge> map = new HashMap<>();
            map.put(destination, edge);
            graph.put(source, map);
        } else {
            graph.get(source).put(destination, edge);
        }

        if (!graph.containsKey(destination)) {
            HashMap<Node, Edge> map = new HashMap<>();
            map.put(source, edge);
            graph.put(destination, map);
        } else {
            graph.get(destination).put(source, edge);
        }

        return edge;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (HashMap.Entry<Node, HashMap<Node, Edge>> entry : graph.entrySet()) {
            output.append(entry.getKey()).append(": ");

            HashMap<Node, Edge> map = entry.getValue();
            for (HashMap.Entry<Node, Edge> edgeEntry : map.entrySet()) {
                output.append("(")
                        .append(edgeEntry.getKey())
                        .append(", ")
                        .append(edgeEntry.getValue())
                        .append("), ");

            }
            output.append("\n");
        }
        return output.toString();
    }

    public DijkstraResult shortestPath(String source, String dest) {
        // Using Dijkstra's to find the shortest distance between source and destination
        // nodes
        Node sourceNode = nodes.get(source);
        Node destNode = nodes.get(dest);
        HashMap<Node, Integer> distance = new HashMap<>();
        HashMap<Node, Node> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (s1, s2) -> Integer.compare(distance.get(s1), distance.get(s2)));
        HashSet<Node> visited = new HashSet<>();

        for (Node node : graph.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
            prev.put(node, null);
        }
        distance.put(sourceNode, 0);
        pq.offer(sourceNode);

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.equals(destNode))
                break;
            // Loop terminated because here we find the distance to the target node and not
            // distances to all the nodes

            if (visited.contains(current))
                continue;

            Map<Node, Edge> neighbors = graph.get(current);

            // Performing edge relaxations for all edges from current vertex
            for (Node neighbor : neighbors.keySet()) {
                if (visited.contains(neighbor))
                    continue;
                int temp = distance.get(current) + neighbors.get(neighbor).getWeight();

                if (temp < distance.get(neighbor)) {
                    distance.put(neighbor, temp);
                    prev.put(neighbor, current);
                    pq.offer(neighbor);
                }
            }

            visited.add(current);
        }

        DijkstraResult res = new DijkstraResult(sourceNode, destNode, distance.get(destNode), prev);

        return res;
    }

    private boolean canAssignPath(int[] corePath, List<Node> nodePath, int numSlots) {
        int pathSize = corePath.length;
        int numEdges = pathSize - 1;
        int count = 0; // this variable is used to count how many edges (channels) have slots assigned
                       // to them
        int startIndex = 0; // index where first slot is assigned

        for (int i = 0; i < numEdges; i = (i + 1) % (numEdges)) {
            Node currNode = nodePath.get(i);
            Node nextNode = nodePath.get(i + 1);

            int currCore = corePath[i];
            int nextCore = corePath[i + 1];

            Edge currEdge = getEdge(currNode, nextNode);
            Channel currChannel = currEdge.getChannels().get(currCore).get(nextCore);

            if (canAssignSlots(currChannel, startIndex, numSlots))
                ++count;
            else {
                count = 0;
                startIndex = findStartIndex(currChannel, startIndex, numSlots);
                if (startIndex == -1)
                    return false;
            }

            if (count == numEdges) {
                finalizeSlots(corePath, nodePath, startIndex, numSlots);
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int[] corePath, List<Node> nodePath, int index, int numSlots) {
        if (index == corePath.length) {
            if (canAssignPath(corePath, nodePath, numSlots))
                return true;

            return false;
        }

        int core = nodePath.get(index).getCurrCore();
        for (int i = 0; i < Node.getNUM_CORES(); ++i, core = ((core % Node.getNUM_CORES()) + 1)) {
            corePath[index] = core;
            if (dfs(corePath, nodePath, index + 1, numSlots))
                return true;
        }

        return false;
    }

    public boolean assignSlots(ArrayList<Node> fullPath, int numSlots) {
        int[] corePath = new int[fullPath.size()];
        return dfs(corePath, fullPath, 0, numSlots);
    }

    private boolean canAssignSlots(Channel currChannel, int startIndex, int numSlots) {
        boolean[] spectrum = currChannel.getSpectrum();
        int endPoint = startIndex + numSlots;
        if (endPoint >= spectrum.length)
            return false;
        for (int i = startIndex; i < endPoint; ++i) {
            if (spectrum[i] == true)
                return false;
        }
        return true;
    }

    private void finalizeSlots(int[] corePath, List<Node> nodePath, int startIndex, int numSlots) {
        int numEdges = nodePath.size() - 1;
        for (int i = 0; i < numEdges; ++i) {
            Node currNode = nodePath.get(i);
            Node nextNode = nodePath.get(i + 1);

            int currCore = corePath[i];
            int nextCore = corePath[i + 1];

            if (i == 0) {
                currNode.setCurrCore();
                nextNode.setCurrCore();
            } else
                nextNode.setCurrCore();

            Edge currEdge = getEdge(currNode, nextNode);
            Channel currChannel = currEdge.getChannels().get(currCore).get(nextCore);

            boolean[] spectrum = currChannel.getSpectrum();
            int endPoint = startIndex + numSlots;

            for (int j = startIndex; j < endPoint; ++j)
                spectrum[j] = true;

            System.out.println(
                    "Edge (" + currEdge.getFrom() + currChannel.getFromCore() + " --> " + currEdge.getTo()
                            + currChannel.getToCore() +
                            "): assigned slots: from " + startIndex + " to " + (endPoint - 1));
        }
    }

    private int findStartIndex(Channel currChannel, int startIndex, int numSlots) {
        boolean[] spectrum = currChannel.getSpectrum();
        int i = startIndex;
        while (i < spectrum.length && spectrum[i] == false)
            ++i; // find first assigned slot
        while (i < spectrum.length && spectrum[i] == true)
            ++i; // find last contigous assigned slot

        if (spectrum.length - i < numSlots)
            return -1;

        return i;
    }

}
