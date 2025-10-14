package com.rmsca;

public class Test {
    public static void main(String[] args) {
        Edge e = new Edge("A", "B", 1000);
        Channel[] channels = e.getChannels();
        for (Channel channel : channels) {
            System.out.println(channel);
        }
    }
}
