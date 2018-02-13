package org.javiersvg.holidayplanner;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class DijkstraPathFinder {

    private static final Set<Node> PROTOTYPE_SET = new TreeSet<>();
    private static final Pattern EDGE_PATTERN = Pattern.compile("([A-Z])([A-Z])(\\d{1,2})");

    Map<String, Node> getMap(String[] args) {
        Map<String, Node> nodes = new TreeMap<>();
        for (String route : args) {
            Matcher matcher = EDGE_PATTERN.matcher(route);
            if (matcher.find()) {
                Node originNode = nodes.computeIfAbsent(matcher.group(1), Node::new);
                Node destinationNode = nodes.computeIfAbsent(matcher.group(2), Node::new);
                String group = matcher.group(3);
                originNode.edges().put(destinationNode, Integer.parseInt(group));
            }

        }
        return nodes;
    }

    Optional<Node> dijkstra(Map<String, Node> nodes, String origin, String destination) {
        Set<Node> tentativeNodes = nodes.values()
                .stream()
                .map((node) -> {
                    if (node.equals(new Node(origin))) {
                        node.setDistance(0);
                    }
                    return node;
                })
                .collect(Collectors.toCollection(() -> PROTOTYPE_SET));

        Node result = null;
        while (!tentativeNodes.isEmpty()) {
            Node currentNode = tentativeNodes.stream().min(Comparator.comparing(Node::getDistance))
                    .orElseThrow(RuntimeException::new);
            tentativeNodes.remove(currentNode);
            if (currentNode.equals(new Node(destination))) {
                result = currentNode;
            } else {
                currentNode.edges().forEach((edgeNode, distance) -> {
                    int distanceToOrigin = currentNode.getDistance() + distance;
                    if (distanceToOrigin < edgeNode.getDistance()) {
                        edgeNode.setDistance(distanceToOrigin);
                        edgeNode.setPrevious(currentNode);
                    }
                });
            }
        }
        return Optional.ofNullable(result);
    }
}
