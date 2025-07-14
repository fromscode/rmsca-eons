package com.rmsca;

import java.util.HashMap;

// The sole purpose of this class is so that it can be used to return the distance and prev vector
// from the shortest path algorithm in Graph class
public class DijkstraResult {
    private final int distance;
    private final HashMap<String, String> previous;

    public DijkstraResult(int distance, HashMap<String, String> previous) {
        this.distance = distance;
        this.previous = previous;
    }

    public HashMap<String, String> getPrevious() {
        return this.previous;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        String res = "distance: " + this.distance + "\n\n";

        for (HashMap.Entry<String, String> itr : previous.entrySet()) {
            res += itr.getKey() + ": " + itr.getValue() + "\n";
        }
        return res;
    }

}
