package com.rmsca;

public class Channel {
    private final static int MAX_SLOTS = 320;
    private String fromCore;
    private String toCore;
    private boolean[] spectrum;

    public Channel(String fromCore, String toCore) {
        this.fromCore = fromCore;
        this.toCore = toCore;
        this.spectrum = new boolean[MAX_SLOTS];
    }

    public static int getMaxSlots() {
        return MAX_SLOTS;
    }

    public String getFromCore() {
        return fromCore;
    }

    public void setFromCore(String fromCore) {
        this.fromCore = fromCore;
    }

    public String getToCore() {
        return toCore;
    }

    public void setToCore(String toCore) {
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
