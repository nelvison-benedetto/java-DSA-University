import java.io.*;
import java.util.*;

public class StockCharts {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new StockCharts().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int[][] stockData = readData();
        int result = minCharts(stockData);
        writeResponse(result);
        out.close();
    }

    int[][] readData() throws IOException {
        int numStocks = in.nextInt();
        int numPoints = in.nextInt();
        int[][] stockData = new int[numStocks][numPoints];
        for (int i = 0; i < numStocks; ++i)
            for (int j = 0; j < numPoints; ++j)
                stockData[i][j] = in.nextInt();
        return stockData;
    }

    private int minCharts(int[][] stockData) {
        int n = stockData.length;
        // Build DAG: edge i->j if stock i < stock j at all points
        boolean[][] adj = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && strictlyLess(stockData[i], stockData[j])) {
                    adj[i][j] = true;
                }
            }
        }

        // Maximum bipartite matching
        int[] matchTo = new int[n];
        Arrays.fill(matchTo, -1);
        int maxMatching = 0;
        for (int u = 0; u < n; u++) {
            boolean[] visited = new boolean[n];
            if (dfs(u, adj, matchTo, visited)) maxMatching++;
        }

        // Minimum number of chains = n - maxMatching
        return n - maxMatching;
    }

    private boolean strictlyLess(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= b[i]) return false;
        }
        return true;
    }

    private boolean dfs(int u, boolean[][] adj, int[] matchTo, boolean[] visited) {
        for (int v = 0; v < adj.length; v++) {
            if (adj[u][v] && !visited[v]) {
                visited[v] = true;
                if (matchTo[v] == -1 || dfs(matchTo[v], adj, matchTo, visited)) {
                    matchTo[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    private void writeResponse(int result) {
        out.println(result);
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
