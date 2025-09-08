import java.util.*;

public class Dijkstra {  //MODULE 4, minimum cost of a flight
    //calcola il costo minimo di un volo in un grafo diretto con pesi non negativi, puoi usare l’algoritmo di Dijkstra
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int v = current[0];
            long d = current[1];

            if (d > dist[v]) continue;

            for (int i = 0; i < adj[v].size(); i++) {
                int u = adj[v].get(i);
                long w = cost[v].get(i);
                if (dist[v] + w < dist[u]) {
                    dist[u] = dist[v] + w;
                    pq.add(new int[]{u, (int) dist[u]});
                }
            }
        }
        return dist[t] == Long.MAX_VALUE ? -1 : dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

