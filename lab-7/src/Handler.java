import java.util.Arrays;

public class Handler {

    public static void main(String...args) {
        Graph graph = new Graph();
        {
            graph.addVertex("v0");
            graph.addVertex("v1");
            graph.addVertex("v2");
            graph.addVertex("v3");
            graph.addVertex("v4");
            graph.addVertex("v5");
            graph.addVertex("v6");
            graph.addVertex("v7");
            graph.addVertex("v8");
            graph.addVertex("v9");
            graph.addVertex("v10");
            graph.addVertex("v11");
            graph.addVertex("v12");
            graph.addVertex("v13");
            graph.addVertex("v14");
            graph.addVertex("v15");
            graph.addVertex("v16");

            graph.addEdge(0, 1, 3);
            graph.addEdge(0, 2, 4);
            graph.addEdge(0, 3, 12);
            graph.addEdge(1, 4, 4);
            graph.addEdge(1, 3, 3);
            graph.addEdge(2, 4, 1);
            graph.addEdge(2, 3, 5);
            graph.addEdge(4, 10, 2);
            graph.addEdge(4, 15, 5);
            graph.addEdge(3, 10, 11);
            graph.addEdge(4, 6, 5);
            graph.addEdge(10, 6, 9);
            graph.addEdge(10, 11, 1);
            graph.addEdge(6, 8, 2);
            graph.addEdge(15, 8, 9);
            graph.addEdge(11, 7, 10);
            graph.addEdge(15, 13, 2);
            graph.addEdge(8, 9, 3);
            graph.addEdge(7, 9, 3);
            graph.addEdge(7, 5, 1);
            graph.addEdge(13, 5, 4);
            graph.addEdge(8, 12, 8);
            graph.addEdge(9, 12, 14);
            graph.addEdge(9, 14, 9);
            graph.addEdge(12, 16, 3);
            graph.addEdge(14, 16, 4);
            graph.addEdge(5, 16, 8);
            graph.addEdge(5, 12, 9);
        }
        //graph.dfs(0);
        //graph.bfs(0);
       // graph.printMatrix();
       /* graph = Graph.dijkstra(graph, graph.getVertexList()[0]);

        for (int i = 0; i < 16; i++) {
            Vertex tempVertex = graph.getVertexList()[i];
            System.out.println(tempVertex.getLabel() + " : " + tempVertex.getDistance());
        } */
        graph.printMatrix();
        int[][] dist = graph.floydWarshall();
        for (int i = 0; i < dist.length; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }
    }

}
