package com.rmsca;

public class Edge {
    private final static int MAX_SLOTS = 320;
    private String from;
    private String to;
    private int weight;
    private boolean[] spectrum;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.spectrum = new boolean[MAX_SLOTS];
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

    public boolean[] getSpectrum() {
        return spectrum;
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
