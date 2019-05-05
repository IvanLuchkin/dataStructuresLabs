import java.util.*;

import static java.lang.Math.min;

public class Graph {

    private static final int VERTEXMAXCOUNT = 17;
    private Vertex[] vertexList;
    private ArrayList<Edge> edgeList;
    private int vertexCount;
    private int[][] matrix;
    Queue queue;
    Stack stack;

    public Graph() {
        matrix = new int[VERTEXMAXCOUNT][VERTEXMAXCOUNT];
        for(int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                matrix[i][j] = 0;
            }
        }
        vertexCount = 0;
        vertexList = new Vertex[VERTEXMAXCOUNT];
        edgeList = new ArrayList<>();

        queue = new Queue(VERTEXMAXCOUNT);
        stack = new Stack();
    }

    public Vertex[] getVertexList() {
        return this.vertexList;
    }

    public void addVertex(String label) {
        vertexList[vertexCount++] = new Vertex(label);
    }

    public void addEdge(int nodePos1, int nodePos2, int cost) {
        matrix[nodePos1][nodePos2] = cost;
        matrix[nodePos2][nodePos1] = cost;
        vertexList[nodePos1].addDestination(vertexList[nodePos2], cost);
        vertexList[nodePos2].addDestination(vertexList[nodePos1], cost);
        edgeList.add(new Edge(vertexList[nodePos1], vertexList[nodePos2], cost));
        edgeList.add(new Edge(vertexList[nodePos2], vertexList[nodePos1], cost));
    }

    void bfs(int v) {
        vertexList[v].isVisited = true;
        queue.insert(v);
        int vertex;

        System.out.println(vertexList[v].getLabel());

        while(!queue.isEmpty()) {
            int current = queue.pop();
            while((vertex = getSuccessor(current)) != -1) {
                vertexList[vertex].isVisited = true;
                queue.insert(vertex);
                System.out.println(vertexList[vertex].getLabel());
            }
        }
        // REFRESHING MARKS \\
        for(int j = 0; j < vertexCount; j++) {
            vertexList[j].isVisited = false;
        }
    }

    int getSuccessor (int v) {
        for(int j = 0; j < vertexCount; j++)
            if(matrix[v][j] != 0 && !vertexList[j].isVisited) {
                return j;
            }
        return -1;
    }

    void dfs(int v){
        vertexList[v].setVisited(true);
        stack.push(v);
        int i = 0;
        System.out.println(vertexList[v].getLabel());


        while(!stack.isEmpty()) {
            int current = stack.peek();
            int vertex = getSuccessor(current);
            if(vertex == -1)
                stack.pop();
            else {
                vertexList[vertex].setVisited(true);
                System.out.println(vertexList[vertex].getLabel());
                stack.push(vertex);
            }
        }
        // REFRESHING MARKS \\
        for(int j = 0; j < vertexCount; j++) {
            vertexList[j].setVisited(false);
        }

    }

    void bellmanFord() {
        int[][] dist = new int[VERTEXMAXCOUNT][VERTEXMAXCOUNT];

        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            dist[i] = bellmanFordAlg(this.getVertexList()[i]);
            System.out.println();
        }

        for(int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                if (dist[j][i] == 0 && i != j) {
                    dist[j][i] = dist[i][j];
                }
            }
        }
        printMatrix(dist);
        // REFRESHING DISTANCES \\
        for(Vertex vertex : this.vertexList) {
            vertex.setDistance(Integer.MAX_VALUE);
        }

    }

    int[] bellmanFordAlg(Vertex source) {
        int[] dist = new int[VERTEXMAXCOUNT];
        source.setDistance(0);
        for (;;) {
            boolean statusChange = false;
            for (Edge edge : edgeList) {
                if (edge.second.getDistance() > edge.cost + edge.first.getDistance()) {
                    edge.second.setDistance(edge.cost + edge.first.getDistance());
                    statusChange = true;
                }
            }
            if (!statusChange) break;
        }
        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            dist[i] = vertexList[i].getDistance();
        }
        return dist;
    }

    void floydWarshall() {
        int[][] dist = new int [VERTEXMAXCOUNT][VERTEXMAXCOUNT]; // dist[i][j] = минимальное_расстояние(i, j)
        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            System.arraycopy(this.matrix[i], 0, dist[i], 0, VERTEXMAXCOUNT);
        }

        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                if (dist[i][j] == 0) dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for (int k = 0; k < VERTEXMAXCOUNT; k++) {
            for (int i = 0; i < VERTEXMAXCOUNT; i++) {
                for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        printMatrix(dist);
    }

    public void dijkstra() {
        int[][] dist = new int[VERTEXMAXCOUNT][VERTEXMAXCOUNT];

        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            dijkstraAlg(this, vertexList[i]);
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                dist[i][j] = vertexList[j].getDistance();
            }
        }
        for(int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                if (dist[j][i] == 0 && i != j) {
                    dist[j][i] = dist[i][j];
                }
            }
        }
        printMatrix(dist);
        // REFRESHING DISTANCES \\
        for(Vertex vertex : this.vertexList) {
            vertex.setDistance(Integer.MAX_VALUE);
        }
    }

    public static Graph dijkstraAlg(Graph graph, Vertex source) {
        source.setDistance(0);

        Set<Vertex> settledVertexes = new HashSet<>();
        Set<Vertex> unsettledVertexes = new HashSet<>();

        unsettledVertexes.add(source);

        while (unsettledVertexes.size() != 0) {
            Vertex currentVertex = getLowestDistanceVertex(unsettledVertexes);
            unsettledVertexes.remove(currentVertex);
            for (Map.Entry<Vertex, Integer> adjacencyPair:
                    currentVertex.getAdjacentVertexes().entrySet()) {
                Vertex adjacentVertex = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledVertexes.contains(adjacentVertex)) {
                    calculateMinimumDistance(adjacentVertex, edgeWeight, currentVertex);
                    unsettledVertexes.add(adjacentVertex);
                }
            }
            settledVertexes.add(currentVertex);
        }
        return graph;
    }
    private static Vertex getLowestDistanceVertex(Set <Vertex> unsettledVertexes) {
        Vertex lowestDistanceVertex = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertex vertex : unsettledVertexes) {
            int nodeDistance = vertex.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceVertex = vertex;
            }
        }
        return lowestDistanceVertex;
    }
    private static void calculateMinimumDistance(Vertex evaluationNode, Integer edgeWeigh, Vertex sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Vertex> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    void printDistances() {
        for(Vertex vertex : this.vertexList) {
            System.out.println(vertex.getLabel() + " : " + vertex.getDistance());
        }
        System.out.println();
    }
}