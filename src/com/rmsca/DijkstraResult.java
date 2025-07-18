package com.rmsca;

import java.util.ArrayDeque;
import java.util.HashMap;

// The sole purpose of this class is so that it can be used to return the distance and prev vector
// from the shortest path algorithm in Graph class
public class DijkstraResult {
    private final String source;
    private final String dest;
    private final int distance;
    private final HashMap<String, String> previous;

    public DijkstraResult(String source, String dest, int distance, HashMap<String, String> previous) {
        this.source = source;
        this.dest = dest;
        this.distance = distance;
        this.previous = previous;
    }

    public int getDistance() {
        return this.distance;
    }

    public ArrayDeque<String> getFullPath() {
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push(dest);
        String curr = previous.get(dest);
        while (curr != "-") {
            stack.push(curr);
            curr = previous.get(curr);
        }

        return stack;
    }

    @Override
    public String toString() {
        String res = "Distance: " + this.distance + "\n";

        res += "Path from " + this.source + " to " + this.dest + ": " + getFullPath();
        return res;
    }

}
