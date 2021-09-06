package devtaskutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class representing a Graph
 */
public class Graph {

    List<Vertex> vertexes;

    public Graph() {
        this.vertexes = new ArrayList<>();
    }
    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public void addVertex(Vertex vertex) {
        Optional<Vertex> vertexOptional = vertexes.stream()
                .filter(v -> v.getVertex().equals(vertex.getVertex()))
                .findFirst();

        if(!vertexOptional.isPresent())
            vertexes.add(vertex);
    }
}
