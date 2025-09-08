import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {  //MODULE 2
    //trovare il numero di componenti fortemente connesse (SCC) in un grafo diretto, usando l’algoritmo di Kosaraju.
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        ArrayList<Integer> order = new ArrayList<>();

        // 1. Prima DFS per ottenere l'ordine di completamento
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs1(adj, visited, order, v);
            }
        }

        // 2. Trasponiamo il grafo
        ArrayList<Integer>[] adjRev = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjRev[i] = new ArrayList<>();
        }
        for (int v = 0; v < n; v++) {
            for (int u : adj[v]) {
                adjRev[u].add(v);
            }
        }

        // 3. Seconda DFS sull'ordine inverso
        visited = new boolean[n];
        int sccCount = 0;
        Collections.reverse(order); // invertiamo l'ordine per la seconda DFS
        for (int v : order) {
            if (!visited[v]) {
                dfs2(adjRev, visited, v);
                sccCount++;
            }
        }

        return sccCount;
    }
    private static void dfs1(ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> order, int v) {
        visited[v] = true;
        for (int u : adj[v]) {
            if (!visited[u]) {
                dfs1(adj, visited, order, u);
            }
        }
        order.add(v);
    }
    private static void dfs2(ArrayList<Integer>[] adjRev, boolean[] visited, int v) {
        visited[v] = true;
        for (int u : adjRev[v]) {
            if (!visited[u]) {
                dfs2(adjRev, visited, u);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

