package com.rmsca;

public class Edge {
    private static final int NUM_CHANNELS = Node.getNUM_CORES() *  Node.getNUM_CORES();
    private String from;
    private String to;
    private int weight;
    private Channel[] channels;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.channels = new Channel[NUM_CHANNELS];
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
