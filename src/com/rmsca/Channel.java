package com.rmsca;

public class Channel {
    private final static int MAX_SLOTS = 320;
    private int fromCore;
    private int toCore;
    private boolean[] spectrum;

    public Channel(int fromCore, int toCore) {
        this.fromCore = fromCore;
        this.toCore = toCore;
        this.spectrum = new boolean[MAX_SLOTS];
    }

    public static int getMaxSlots() {
        return MAX_SLOTS;
    }

    public int getFromCore() {
        return fromCore;
    }

    public void setFromCore(int fromCore) {
        this.fromCore = fromCore;
    }

    public int getToCore() {
        return toCore;
    }

    public void setToCore(int toCore) {
        this.toCore = toCore;
    }

    public boolean[] getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(boolean[] spectrum) {
        this.spectrum = spectrum;
    }

    @Override
    public String toString() {
        return "(" + this.fromCore + " -> " + this.toCore + ")";
    }
}
