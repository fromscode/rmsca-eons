package com.rmsca;

public class Edge {
    private static final int NUM_CHANNELS = Node.getNUM_CORES() * Node.getNUM_CORES();
    private String from;
    private String to;
    private int weight;
    private Channel[] channels;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.channels = new Channel[NUM_CHANNELS];

        int fromCore = 1;
        int toCore = 1;

        for (int i = 0; i < NUM_CHANNELS; ++i) {
            this.channels[i] = new Channel(String.valueOf(fromCore), String.valueOf(toCore));
            ++toCore;
            if (toCore > Node.getNUM_CORES()) {
                ++fromCore;
                toCore = 1;
            }
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    public Channel[] getChannels() {
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
