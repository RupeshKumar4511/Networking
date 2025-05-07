// Dijstra Algorithm
import java.util.*;

public class DijkstraAlgorithm {

    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];           // Output array. dist[i] will hold the shortest distance from src to i
        boolean[] visited = new boolean[V]; // visited[i] will be true if vertex i is included in the shortest path tree

        // Initialize all distances as INFINITE and visited[] as false
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed.
            int u = minDistance(dist, visited);
            visited[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist, src);
    }

    // A utility method to find the vertex with the minimum distance value, from the set of vertices not yet processed.
    private static int minDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static void printSolution(int[] dist, int src) {
        System.out.println("Vertex\tDistance from Source " + src);
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + "\t\t" + dist[i]);
    }

    public static void main(String[] args) {
        // Sample graph as adjacency matrix
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        dijkstra(graph, 0); // Source node is 0
    }
}




