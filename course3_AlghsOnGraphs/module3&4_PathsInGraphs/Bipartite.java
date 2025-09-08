import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {  //MODULE 3
    //verifica se un grafo non orientato è bipartito. Puoi usare BFS con colorazione dei nodi (due colori).
    private static int bipartite(ArrayList<Integer>[] adj) {
        int n = adj.length;
        int[] color = new int[n]; // 0 = non colorato, 1 = colore1, -1 = colore2

        for (int start = 0; start < n; start++) {
            if (color[start] == 0) {
                // BFS
                Queue<Integer> queue = new LinkedList<>();
                queue.add(start);
                color[start] = 1;

                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    for (int neighbor : adj[v]) {
                        if (color[neighbor] == 0) {
                            color[neighbor] = -color[v]; // coloriamo con colore opposto
                            queue.add(neighbor);
                        } else if (color[neighbor] == color[v]) {
                            return 0; // stesso colore su due estremità di un arco → non bipartito
                        }
                    }
                }
            }
        }
        return 1; // bipartito
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
        System.out.println(bipartite(adj));
    }
}

