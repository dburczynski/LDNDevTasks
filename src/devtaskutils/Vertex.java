package devtaskutils;

import java.util.*;

/**
 * Class representing the vertex of a graph
 */
public class Vertex {

    private Integer vertex;
    private List<Integer> connections;

    public Vertex(Integer vertex) {
        this.vertex = vertex;
        this.connections = new ArrayList<>();
    }

    public Integer getVertex() {
        return vertex;
    }

    public void setVertex(Integer vertex) {
        this.vertex = vertex;
    }

    public List<Integer> getConnections() {
        return connections;
    }

    public void setConnections(List<Integer> connections) {
        this.connections = connections;
    }

    public void addConnection(Integer connection) {
      Optional<Integer> connectionOptional = connections.stream()
              .filter(c -> c.equals(connection))
              .findFirst();

      if(!connectionOptional.isPresent())
          connections.add(connection);
    }
}
