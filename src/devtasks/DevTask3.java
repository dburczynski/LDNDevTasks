package devtasks;

import devtaskutils.Graph;
import devtaskutils.Tuple;
import devtaskutils.Vertex;
import devtaskutils.GraphCmdLineReader;

import java.util.*;
import java.util.stream.Collectors;

public class DevTask3 {

    /**
     * Creates a list of graph vertexes from a list of connections
     * @param connections
     * @return
     */
    public List<Vertex> createVertexes(List<Tuple<Integer, Integer>> connections) {
        List<Vertex> vertexes = new ArrayList<>();
        for(Tuple<Integer, Integer> connection : connections) {
            //Searches for existing vertexes for connection
            Vertex vertex1 = getVertexIfExists(vertexes, connection.getX());
            Vertex vertex2 = getVertexIfExists(vertexes, connection.getY());

            //Creates new vertex if such doesnt exist
            if (vertex1 == null) {
                vertex1 = new Vertex(connection.getX());
                vertexes.add(vertex1);
            }
            //Creates new vertex if such doesnt exist and connection isnt to itself e.g vertex with no connection
            if (vertex2 == null && !connection.getX().equals(connection.getY())) {
                vertex2 = new Vertex(connection.getY());
                vertexes.add(vertex2);
            }

            //Creating connections between vertexes
            vertex1.addConnection(connection.getY());
            if(!connection.getX().equals(connection.getY()))
                vertex2.addConnection(connection.getX());
        }

        return vertexes;
    }

    /**
     * Creates graphs from vertexes
     * @param vertexes
     * @return
     */
    public List<Graph> createGraphs(List<Vertex> vertexes) {
        List<Graph> graphs = new ArrayList<>();

        for (Vertex vertex : vertexes) {
            //If no graph created create new one with current vertex in it;
            if (graphs.isEmpty()) {
                Graph graph = new Graph();
                graph.addVertex(vertex);
                graphs.add(graph);
                continue;
            }

            Boolean addedToExistingGraph = false;
            //Checks if a graph exists where atleast one of a vertexes connections is already in it
            for (Graph graph : graphs) {
                if (!graph.getVertexes().stream().map(v -> v.getVertex()).collect(Collectors.toList())
                        .stream().distinct().filter(vertex.getConnections()::contains)
                        .collect(Collectors.toSet()).isEmpty()) {

                    graph.addVertex(vertex);
                    addedToExistingGraph = true;
                    break;
                }
            }
            //If no such graph exists create new one
            if(!addedToExistingGraph) {
                Graph graph = new Graph();
                graph.addVertex(vertex);
                graphs.add(graph);
            }

        }

        return graphs;
    }

    /**
     * Checks if vertex already exists in list
     * @param vertexes
     * @param vertex
     * @return
     */
    private Vertex getVertexIfExists(List<Vertex> vertexes, Integer vertex) {
        Optional<Vertex> vertexOptional = vertexes.stream()
                .filter(v -> v.getVertex().equals(vertex))
                .findFirst();

        if(vertexOptional.isPresent())
            return vertexOptional.get();
        else
            return null;
    }

    /**
     * Intersects two lists of integers
     * @param list1
     * @param list2
     * @return
     */
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
