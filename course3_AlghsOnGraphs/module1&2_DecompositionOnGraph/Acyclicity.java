import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {  //MODULE 2
    //rileva se un grafo diretto contiene un ciclo (problema “Acyclicity”), utilizza DFS con colorazione dei nodi.
    private static int acyclic(ArrayList<Integer>[] adj) {
        int[] visited = new int[adj.length]; // 0 = non visitato, 1 = in corso, 2 = visitato
        for (int v = 0; v < adj.length; v++) {
            if (visited[v] == 0) {
                if (dfs(adj, visited, v)) {
                    return 1; // ciclo trovato
                }
            }
        }
        return 0;  // nessun ciclo
    }

    private static boolean dfs(ArrayList<Integer>[] adj, int[] visited, int v) {
        visited[v] = 1; // nodo in corso
        for (int neighbor : adj[v]) {
            if (visited[neighbor] == 0) {
                if (dfs(adj, visited, neighbor)) {
                    return true;
                }
            } else if (visited[neighbor] == 1) {
                return true; // ciclo trovato
            }
        }
        visited[v] = 2; // nodo completamente visitato
        return false;
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
        System.out.println(acyclic(adj));
    }
}

