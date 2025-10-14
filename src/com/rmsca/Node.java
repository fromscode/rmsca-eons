package com.rmsca;

public class Node {
    private static final int NUM_CORES = 3;
    private String name;
    private int currCore;

    public Node(String name) {
        this.name = name;
        this.currCore = 0;
    }

    public int getCurrCore() {
        return this.currCore;
    }

    public void setCurrCore() {
        this.currCore = (this.currCore + 1) % NUM_CORES;
    }

    public String toString() {
        return this.name;
    }

    public static int getNUM_CORES() {
        return NUM_CORES;
    }
}
