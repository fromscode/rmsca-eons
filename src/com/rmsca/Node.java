package com.rmsca;

public class Node {
    private static final int NUM_CORES = 3;
    private String name;
    private int currCore;

    public Node(String name) {
        this.name = name;
        this.currCore = 1;
    }

    public int getCurrCore() {
        return this.currCore;
    }

    public void setCurrCore() {
        this.currCore = ((this.currCore) % NUM_CORES) + 1;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public static int getNUM_CORES() {
        return NUM_CORES;
    }

}
