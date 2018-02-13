package org.javiersvg.holidayplanner;

import java.util.Map;
import java.util.Scanner;

public class HolidayPlannerApplication {

    public static void main(String... args) {
        findShortestRoute(args);
    }

    private static void findShortestRoute(String[] args) {
        DijkstraPathFinder dijkstraPathFinder = new DijkstraPathFinder();
        Map<String, Node> nodes = dijkstraPathFinder.getMap(args);

        System.out.println(nodes);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter origin");
        String origin = scanner.nextLine();
        System.out.println("enter destination");
        String destination = scanner.nextLine();


        Node resultNode = dijkstraPathFinder.dijkstra(nodes, origin, destination).orElse(new Node(null));
        System.out.println(resultNode + " " + resultNode.getDistance());
    }


}