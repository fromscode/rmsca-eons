package com.rmsca;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Edge e = new Edge("A", "B", 1000);
        Map<Integer, Map<Integer, Channel>> channels = e.getChannels();
        for (Map<Integer, Channel> values : channels.values()) {
            for (Channel channel : values.values())
                System.out.println(channel);
        }
    }
}
