import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vertex {

    private String label;
    public boolean isVisited;
    private List<Vertex> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<Vertex, Integer> adjacentVertexes = new HashMap<>();

    public Vertex(String label) {
        this.label = label;
        isVisited = false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void addDestination(Vertex destination, int distance) {
        adjacentVertexes.put(destination, distance);
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Vertex> getShortestPath() {
        return this.shortestPath;
    }

    public Map<Vertex, Integer> getAdjacentVertexes() {
        return this.adjacentVertexes;
    }
}