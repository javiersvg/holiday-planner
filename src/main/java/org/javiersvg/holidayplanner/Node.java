package org.javiersvg.holidayplanner;

import java.util.HashMap;
import java.util.Map;

class Node implements Comparable<Node> {
    private String name;
    private Map<Node, Integer> edges = new HashMap<>();
    private int distance = Integer.MAX_VALUE;
    private Node previous;

    Node(String name) {
        this.name = name;
    }

    Map<Node, Integer> edges() {
        return edges;
    }

    int getDistance() {
        return distance;
    }

    void setDistance(int distance) {
        this.distance = distance;
    }

    void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        if (name == null) {
            return "NOT FOUND";
        } else {
            return (previous != null ?  previous.toString() + "-" : "") + name;
        }
    }
}
