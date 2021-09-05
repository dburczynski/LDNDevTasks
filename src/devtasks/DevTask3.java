package devtasks;

import devtaskutils.Graph;
import devtaskutils.Tuple;
import devtaskutils.Vertex;
import devtaskutils.GraphCmdLineReader;

import java.util.*;
import java.util.stream.Collectors;

public class DevTask3 {

    public List<Vertex> createVertexes(List<Tuple<Integer, Integer>> connections) {
        List<Vertex> vertexes = new ArrayList<>();
        for(Tuple<Integer, Integer> connection : connections) {
            Vertex vertex1 = getVertexIfExists(vertexes, connection.getX());
            Vertex vertex2 = getVertexIfExists(vertexes, connection.getY());

            if (vertex1 == null) {
                vertex1 = new Vertex(connection.getX());
                vertexes.add(vertex1);
            }
            if (vertex2 == null && !connection.getX().equals(connection.getY())) {
                vertex2 = new Vertex(connection.getY());
                vertexes.add(vertex2);
            }

            vertex1.addConnection(connection.getY());
            if(!connection.getX().equals(connection.getY()))
                vertex2.addConnection(connection.getX());
        }

        return vertexes;
    }

    public List<Graph> createGraphs(List<Vertex> vertexes) {
        List<Graph> graphs = new ArrayList<>();

        for (Vertex vertex : vertexes) {
            if (graphs.isEmpty()) {
                Graph graph = new Graph();
                graph.addVertex(vertex);
                graphs.add(graph);
                continue;
            }

            Boolean addedToExistingGraph = false;
            for (Graph graph : graphs) {
                if (!graph.getVertexes().stream().map(v -> v.getVertex()).collect(Collectors.toList())
                        .stream().distinct().filter(vertex.getConnections()::contains)
                        .collect(Collectors.toSet()).isEmpty()) {

                    graph.addVertex(vertex);
                    addedToExistingGraph = true;
                    break;
                }
            }
            if(!addedToExistingGraph) {
                Graph graph = new Graph();
                graph.addVertex(vertex);
                graphs.add(graph);
            }

        }

        return graphs;
    }

    private Vertex getVertexIfExists(List<Vertex> vertexes, Integer vertex) {
        Optional<Vertex> vertexOptional = vertexes.stream()
                .filter(v -> v.getVertex().equals(vertex))
                .findFirst();

        if(vertexOptional.isPresent())
            return vertexOptional.get();
        else
            return null;
    }

    private Boolean intersects(List<Integer> list1, List<Integer> list2) {
        for(Integer int1 : list1) {
            if(list2.contains(int1))
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Executing DevTask3...");
        GraphCmdLineReader reader = new GraphCmdLineReader();
        DevTask3 task3 = new DevTask3();
        try {
            List<Tuple<Integer, Integer>> graphConnections = reader.readGraphConnections();
            List<Vertex> vertexes = task3.createVertexes(graphConnections);
            System.out.println(task3.createGraphs(vertexes).size());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
