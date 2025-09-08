import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {  //MODULE 2
    //topological sort usando DFS
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int v = 0; v < adj.length; v++) {
            if (used[v] == 0) {
                dfs(adj, used, order, v);
            }
        }
        Collections.reverse(order); // invertiamo l’ordine finale
        return order;
    }


    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        used[v] = 1;
        for (int neighbor : adj[v]) {
            if (used[neighbor] == 0) {
                dfs(adj, used, order, neighbor);
            }
        }
        order.add(v); // aggiungiamo il nodo dopo aver visitato tutti i vicini
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

