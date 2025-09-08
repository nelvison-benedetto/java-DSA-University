import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {  //MODULE 4, detecting anomalies in currency exchange rates
    //rileva un ciclo negativo in un grafo diretto con pesi negativi, puoi usare l’algoritmo di Bellman-Ford.
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int n = adj.length;
        int[] distance = new int[n];
        // Proviamo a eseguire Bellman-Ford da ogni nodo, necessario per grafi non connessi
        for (int start = 0; start < n; start++) {
            for (int i = 0; i < n; i++) {
                distance[i] = 0;
            }
            // rilassamento degli archi n volte
            for (int i = 0; i < n; i++) {
                boolean updated = false;
                for (int u = 0; u < n; u++) {
                    for (int j = 0; j < adj[u].size(); j++) {
                        int v = adj[u].get(j);
                        int w = cost[u].get(j);
                        if (distance[v] > distance[u] + w) {
                            distance[v] = distance[u] + w;
                            updated = true;
                            if (i == n - 1) { // ciclo negativo rilevato
                                return 1;
                            }
                        }
                    }
                }
                if (!updated) break; // ottimizzazione: nessuna modifica
            }
        }
        return 0; // nessun ciclo negativo
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

