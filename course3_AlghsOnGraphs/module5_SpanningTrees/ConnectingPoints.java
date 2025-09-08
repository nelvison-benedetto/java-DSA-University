import java.util.Scanner;
import java.util.*;
import java.util.Locale;

public class ConnectingPoints {
    //Questo problema è il classico Minimum Spanning Tree (MST) su punti nel piano, calcolato usando Kruskal.
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
        Edge(int u, int v, double w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge other) { return Double.compare(this.w, other.w); }
    }

    private static double minimumDistance(int[] x, int[] y) {
        int n = x.length;
        if (n <= 1) return 0.0;

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
        double result = 0.0;
        int used = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                result += e.w;
                used++;
                if (used == n - 1) break;
            }
        }
        return result;
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
        double ans = minimumDistance(x, y);
        System.out.printf(Locale.US, "%.12f%n", ans);
    }
}

