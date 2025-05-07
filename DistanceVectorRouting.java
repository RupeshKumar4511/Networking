public class DistanceVectorRouting {

    static final int INF = 999; // Represents infinity
    static final int N = 4;     // Number of nodes

    public static void main(String[] args) {
        // Cost matrix (graph), INF means no direct link
        int[][] cost = {
            {0, 1, 3, INF},
            {1, 0, 1, 4},
            {3, 1, 0, 2},
            {INF, 4, 2, 0}
        };

        // Initialize routing tables for each node
        int[][][] table = new int[N][N][2]; // [node][destination][0: cost, 1: next hop]

        // Initial setup of tables
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                table[i][j][0] = cost[i][j];
                table[i][j][1] = (cost[i][j] != INF && i != j) ? j : -1;
            }
        }

        boolean updated;

        // Distance Vector algorithm: repeat until no updates
        do {
            updated = false;
            for (int i = 0; i < N; i++) { // For each router
                for (int j = 0; j < N; j++) { // For each destination
                    for (int k = 0; k < N; k++) { // Check all neighbors
                        if (cost[i][k] != INF && table[k][j][0] != INF) {
                            int newCost = cost[i][k] + table[k][j][0];
                            if (newCost < table[i][j][0]) {
                                table[i][j][0] = newCost;
                                table[i][j][1] = k;
                                updated = true;
                            }
                        }
                    }
                }
            }
        } while (updated);

        // Print the final routing tables
        for (int i = 0; i < N; i++) {
            System.out.println("Routing table for Node " + i + ":");
            System.out.println("Destination\tCost\tNext Hop");
            for (int j = 0; j < N; j++) {
                System.out.println(j + "\t\t" + table[i][j][0] + "\t" + (table[i][j][1] == -1 ? "-" : table[i][j][1]));
            }
            System.out.println();
        }
    }
}

