package com.rmsca;

import java.util.Scanner;

public class Engine {
    private Graph graph;

    public Engine(Graph graph) {
        this.graph = graph;
    }

    private DijkstraResult getShortestPath(String source, String destination) {
        return graph.shortestPath(source, destination);
    }

    private int getSlotsRequired(String source, String dest, int strength, DijkstraResult res) {
        int R = strength;
        double B = 12.5;
        int SE = 0;

        int distance = res.getDistance();

        if (distance <= 250) SE = 6;
        else if (distance <= 500) SE = 5;
        else if (distance <= 1000) SE = 4;
        else if (distance <= 2000) SE = 3;
        else if (distance <= 4000) SE = 2;
        else SE = 6;

        return  (int) Math.ceil(R / (B * SE));
    }
    
    private void processRequest(String source, String dest, int strength) {
        DijkstraResult shortestPath = getShortestPath(source, dest);
        int numSlots = getSlotsRequired(source, dest, strength, shortestPath);
        
        this.graph.assignSlots(shortestPath.getFullPath(), numSlots); 
    }

    public void build(Scanner sc) {
        System.out.println("Leave input empty to quit");
        System.out.println();
        while (true) {
            System.out.print("Enter request (source destination strength): ");
            String input = sc.nextLine();
            if (input.equals("")) break;
            String[] arr = input.split(" ");

            processRequest(arr[0], arr[1], Integer.valueOf(arr[2]));
            System.out.println();
        }
    }
}