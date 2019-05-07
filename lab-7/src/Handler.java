public class Handler {

    public static void main(String...args) {
        Graph graph = new Graph(17);
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
        graph.bellmanFord();
        System.out.println();
        graph.dijkstra();

        //graph.dfs(0);
        //graph.bfs(0);
        /*for (int i = 1000; i <= 20000; i += 1000) {
            Graph graph = new Graph(i);
            graph.fillGraph(i, (int)(Math.random() * (i * 10 - i * 3) + i * 3));

            long startTime1 = System.currentTimeMillis();
            graph.dijkstra();
            long timeSpent1 = System.currentTimeMillis() - startTime1;
            System.out.println("Дэйкстры: " + timeSpent1 + " миллисекунд");

            long startTime2 = System.currentTimeMillis();
            graph.bellmanFord();
            long timeSpent2 = System.currentTimeMillis() - startTime2;
            System.out.println("Беллмана-Форда: " + timeSpent2 + " миллисекунд");

            long startTime3 = System.currentTimeMillis();
            graph.floydWarshall();
            long timeSpent3 = System.currentTimeMillis() - startTime3;
            System.out.println("Флойда-Уоршелла: " + timeSpent3 + " миллисекунд");
        }*/

    }

}
