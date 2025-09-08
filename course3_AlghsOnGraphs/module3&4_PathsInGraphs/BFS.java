import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {  //MODULE 3
    //calcola la distanza minima in un grafo non orientato, puoi usare BFS a partire dal nodo sorgente s.
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int n = adj.length;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = -1; // inizialmente tutti i nodi non raggiunti
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        dist[s] = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int neighbor : adj[v]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[v] + 1;
                    queue.add(neighbor);
                    if (neighbor == t) {
                        return dist[neighbor]; // abbiamo raggiunto il nodo destinazione
                    }
                }
            }
        }
        return dist[t]; // se dist[t] = -1, il nodo t non è raggiungibile
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

