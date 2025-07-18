package com.rmsca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        Graph graph = new Graph();
        graph.create(sc);
        System.out.println(graph);
        System.out.println();

        Engine engine = new Engine(graph);

        engine.build();
        sc.close();
    }
}
