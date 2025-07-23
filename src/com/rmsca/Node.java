package com.rmsca;

public class Node {
    private final int NUMCORES = 3;
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
