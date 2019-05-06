public class Vertex {

    private String label;
    public boolean isVisited;
    private Integer distance = Integer.MAX_VALUE;

    public Vertex(String label) {
        this.label = label;
        isVisited = false;
    }

    public String getLabel() {
        return label;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

}