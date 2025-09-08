import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {  //MODULE 1
    //calcola il numero di componenti connesse in un grafo non orientato, usando una semplice DFS
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        boolean[] visited = new boolean[adj.length];
        int result = 0;
        for (int v = 0; v < adj.length; v++) {
            if (!visited[v]) {
                dfs(adj, v, visited);
                result++;
            }
        }
        return result;
    }

    private static void dfs(ArrayList<Integer>[] adj, int v, boolean[] visited) {
        visited[v] = true;
        for (int neighbor : adj[v]) {
            if (!visited[neighbor]) {
                dfs(adj, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // numero di vertici
        int m = scanner.nextInt();  // numero di archi
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

