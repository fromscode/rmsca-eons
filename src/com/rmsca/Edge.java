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

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean[] getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(boolean[] spectrum) {
        this.spectrum = spectrum;
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("(");
        output.append(getFrom()).append(", ");
        output.append(getTo()).append(", ");
        output.append(getWeight()).append(")");
        
        return output.toString();
    }
}
