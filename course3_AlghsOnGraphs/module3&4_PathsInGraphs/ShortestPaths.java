import java.util.*;

public class ShortestPaths {  //MODULE 4, exchange money optimally

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
      int n = adj.length;
        distance[s] = 0;
        reachable[s] = 1;
        // Bellman-Ford base: n-1 rilassamenti
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                if (distance[u] == Long.MAX_VALUE) continue;
                for (int j = 0; j < adj[u].size(); j++) {
                    int v = adj[u].get(j);
                    long w = cost[u].get(j);
                    if (distance[u] + w < distance[v]) {
                        distance[v] = distance[u] + w;
                        reachable[v] = 1;
                    }
                }
            }
        }
        // Rilevamento dei cicli negativi
        boolean[] inNegativeCycle = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < n; u++) {
                if (distance[u] == Long.MAX_VALUE) continue;
                for (int j = 0; j < adj[u].size(); j++) {
                    int v = adj[u].get(j);
                    long w = cost[u].get(j);
                    if (distance[u] + w < distance[v]) {
                        distance[v] = distance[u] + w;
                        inNegativeCycle[v] = true;
                    }
                }
            }
        }
        // Propagazione dei cicli negativi a tutti i vertici raggiungibili da essi
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (inNegativeCycle[i]) {
                queue.add(i);
                visited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            shortest[u] = 0; // nessun cammino minimo valido
            for (int v : adj[u]) {
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                    shortest[v] = 0;
                }
            }
        }
        // Impostiamo reachable a 1 per tutti i vertici raggiungibili
        for (int i = 0; i < n; i++) {
            if (distance[i] != Long.MAX_VALUE) reachable[i] = 1;
        }
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

