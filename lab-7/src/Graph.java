import java.util.*;

import static java.lang.Math.min;

public class Graph {

    private int VERTEXMAXCOUNT = 17;
    private Vertex[] vertexList;
    private ArrayList<Edge> edgeList;
    private int vertexCount;
    private int[][] matrix;
    Queue queue;
    Stack stack;

    public Graph(int vertexAmount) {
        VERTEXMAXCOUNT = vertexAmount;
        matrix = new int[VERTEXMAXCOUNT][VERTEXMAXCOUNT];
        for(int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                matrix[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        vertexCount = 0;
        vertexList = new Vertex[VERTEXMAXCOUNT];
        edgeList = new ArrayList<>();

        queue = new Queue(VERTEXMAXCOUNT);
        stack = new Stack();
    }

    public void fillGraph(int vertexAmount, int edgeAmount) {
        for (int i = 0; i < vertexAmount; i++) {
            this.addVertex("v" + i);
        }
        for (int i = 0; i < edgeAmount; i++) {
            int nodePos1 = (int)(Math.random() * vertexAmount);
            int nodePos2 = (int)(Math.random() * vertexAmount);
            if (this.matrix[nodePos1][nodePos2] == 0) {
                this.addEdge(nodePos1, nodePos2, (int) (Math.random() * 49 + 1));
            }
        }
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
        }

        for(int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                if (dist[j][i] == 0 && i != j) {
                    dist[j][i] = dist[i][j];
                }
            }
        }

        //printMatrix(dist);

    }

    int[] bellmanFordAlg(Vertex source) {
        int[] dist = new int[VERTEXMAXCOUNT];
        source.setDistance(0);
        for (;;) {
            boolean statusChange = false;
            for (Edge edge : edgeList) {
                if (edge.first.getDistance() < Integer.MAX_VALUE) {
                    if (edge.second.getDistance() > edge.cost + edge.first.getDistance()) {
                        edge.second.setDistance(edge.cost + edge.first.getDistance());
                        statusChange = true;
                    }
                }
            }
            if (!statusChange) break;
        }
        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            dist[i] = vertexList[i].getDistance();
        }
        System.out.println("From " + source.getLabel());
        printDistances();
        // REFRESHING DISTANCES \\
        for(Vertex vertex : this.vertexList) {
            vertex.setDistance(Integer.MAX_VALUE / 2);
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
                if (i == j) dist[i][j] = 0;
            }
        }

        for (int k = 0; k < VERTEXMAXCOUNT; k++) {
            for (int i = 0; i < VERTEXMAXCOUNT; i++) {
                for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        //printMatrix(dist);
    }

    void dijkstra() {
        int[][] dist = new int[VERTEXMAXCOUNT][VERTEXMAXCOUNT];

        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            dist[i] = dijkstraAlg(i);
        }

        for(int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                if (dist[j][i] == 0 && i != j) {
                    dist[j][i] = dist[i][j];
                }
            }
        }

        //printMatrix(dist);

    }

    int[] dijkstraAlg (int source) {
        int INF = Integer.MAX_VALUE;
        int[][] matrix = this.matrix;
        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            for (int j = 0; j < VERTEXMAXCOUNT; j++) {
                if (matrix[i][j] == 0) matrix[i][j] = INF;
            }
        }
        int[] dist = new int[VERTEXMAXCOUNT];
        for (int i = 0; i < VERTEXMAXCOUNT; i++) {
            dist[i] = INF;
        }
        dist[source] = 0;
        for(;;) {
            int v = -1;
            for (int i = 0; i < VERTEXMAXCOUNT; i++) {
                if (!vertexList[i].isVisited && dist[i] < INF && (v == -1 || dist[v] > dist[i])) {
                    v = i;
                }
            }
            if (v == -1) break;
            vertexList[v].setVisited(true);
            for (int i = 0; i < VERTEXMAXCOUNT; i++) {
                if (!vertexList[i].isVisited && matrix[v][i] < INF) {
                    vertexList[i].setDistance(dist[i] = min(dist[i], dist[v] + matrix[v][i]));
                }
            }
        }
        System.out.println("From " + vertexList[source].getLabel());
        printDistances();
        // REFRESHING MARKS \\
        for(int j = 0; j < VERTEXMAXCOUNT; j++) {
            vertexList[j].isVisited = false;
        }
        // REFRESHING DISTANCES \\
        for(Vertex vertex : this.vertexList) {
            vertex.setDistance(Integer.MAX_VALUE / 2);
        }
        return dist;
    }


    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 100) matrix[i][j] = 0;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    void printDistances() {
        for(Vertex vertex : this.vertexList) {
            System.out.println(vertex.getLabel() + " : " + vertex.getDistance());
        }
        System.out.println();
    }
}