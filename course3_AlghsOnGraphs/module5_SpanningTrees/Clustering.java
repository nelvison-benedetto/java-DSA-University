import java.util.Scanner;
import java.util.*;
import java.util.Locale;

public class Clustering { 
    //Il problema richiede di calcolare il massimo spacing tra cluster per un insieme di punti, 
    //che è un classico problema risolvibile con una variante di Kruskal: si costruisce l’albero dei cluster minimo e si interrompe quando rimangono k cluster.
    //La distanza d massima tra cluster è l’ultimo arco aggiunto prima di avere esattamente k cluster.
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int v) {
            if (parent[v] != v) parent[v] = find(parent[v]);
            return parent[v];
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            if (rank[a] < rank[b]) {
                int t = a; a = b; b = t;
            }
            parent[b] = a;
            if (rank[a] == rank[b]) rank[a]++;
            return true;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double w;

        Edge(int u, int v, double w) {
            this.u = u; this.v = v; this.w = w;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.w, other.w);
        }
    }

    private static double clustering(int[] x, int[] y, int k) {
        int n = x.length;

        // Costruisci tutti gli archi con distanza euclidea
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long dx = (long) x[i] - x[j];
                long dy = (long) y[i] - y[j];
                double dist = Math.sqrt((double) dx * dx + (double) dy * dy);
                edges.add(new Edge(i, j, dist));
            }
        }
        Collections.sort(edges);

        DSU dsu = new DSU(n);
        int components = n;

        for (Edge e : edges) {
            int ru = dsu.find(e.u);
            int rv = dsu.find(e.v);
            if (ru != rv) {
                // Quando abbiamo già k componenti, il prossimo arco che collegherebbe due componenti
                // determina lo spacing massimo.
                if (components == k) {
                    return e.w;
                }
                dsu.union(ru, rv);
                components--;
            }
        }

        // Caso limite k == 1: nessun "inter-cluster", spacing = 0
        return 0.0;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // assicura il punto come separatore decimale
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();

        double ans = clustering(x, y, k);
        System.out.printf(Locale.US, "%.12f%n", ans);
    }
}

