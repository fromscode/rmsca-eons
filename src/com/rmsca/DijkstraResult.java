package com.rmsca;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

// The sole purpose of this class is so that it can be used to return the distance and prev vector
// from the shortest path algorithm in Graph class
public class DijkstraResult {
    private final Node source;
    private final Node dest;
    private final int distance;
    private final HashMap<Node, Node> previous;

    public DijkstraResult(Node source, Node dest, int distance, HashMap<Node, Node> previous) {
        this.source = source;
        this.dest = dest;
        this.distance = distance;
        this.previous = previous;
    }

    public int getDistance() {
        return this.distance;
    }

    public ArrayList<Node> getFullPath() {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(dest);
        Node curr = previous.get(dest);
        while (curr != null) {
            stack.push(curr);
            curr = previous.get(curr);
        }

        return new ArrayList<>(stack);
    }

    @Override
    public String toString() {
        String res = "Distance: " + this.distance + "\n";

        res += "Path from " + this.source + " to " + this.dest + ": " + getFullPath();
        return res;
    }

}
