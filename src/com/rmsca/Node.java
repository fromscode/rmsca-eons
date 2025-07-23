package com.rmsca;

public class Node {
    private static final int NUM_CORES = 3;
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static int getNUM_CORES() {
        return NUM_CORES;
    }
}
