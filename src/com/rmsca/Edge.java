package com.rmsca;

import java.util.HashMap;
import java.util.Map;

public class Edge {
    private static final int NUM_CHANNELS = Node.getNUM_CORES() * Node.getNUM_CORES();
    private Node from;
    private Node to;
    private int weight;
    private Map<Integer, Map<Integer, Channel>> channels;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.channels = new HashMap<>();

        int fromCore = 1;
        int toCore = 1;

        for (int i = 0; i < NUM_CHANNELS; ++i) {
            Channel channel = new Channel(fromCore, toCore);

            if (!channels.containsKey(fromCore)) {
                channels.put(fromCore, new HashMap<>());
            }

            channels.get(fromCore).put(toCore, channel);

            ++toCore;
            if (toCore > Node.getNUM_CORES()) {
                ++fromCore;
                toCore = 1;
            }
        }
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    public Map<Integer, Map<Integer, Channel>> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("(")
                .append(getFrom())
                .append(", ")
                .append(getTo())
                .append(", ")
                .append(getWeight()).append(")");

        return output.toString();
    }
}
