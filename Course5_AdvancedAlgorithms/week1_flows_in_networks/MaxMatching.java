import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MaxMatching {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new MaxMatching().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        boolean[][] bipartiteGraph = readData();
        int[] matching = findMatching(bipartiteGraph);
        writeResponse(matching);
        out.close();
    }

    boolean[][] readData() throws IOException {
        int numLeft = in.nextInt();
        int numRight = in.nextInt();
        boolean[][] adjMatrix = new boolean[numLeft][numRight];
        for (int i = 0; i < numLeft; ++i)
            for (int j = 0; j < numRight; ++j)
                adjMatrix[i][j] = (in.nextInt() == 1);
        return adjMatrix;
    }

    private int[] findMatching(boolean[][] bipartiteGraph) {
        int numLeft = bipartiteGraph.length;
        int numRight = bipartiteGraph[0].length;
        int[] matchTo = new int[numRight]; // quale flight è assegnato a questo crew
        Arrays.fill(matchTo, -1);

        for (int u = 0; u < numLeft; u++) {
            boolean[] visited = new boolean[numRight];
            dfs(u, bipartiteGraph, matchTo, visited);
        }

        int[] result = new int[numLeft];
        Arrays.fill(result, -1);
        for (int j = 0; j < numRight; j++) {
            if (matchTo[j] != -1) {
                result[matchTo[j]] = j;
            }
        }
        return result;
    }

    private boolean dfs(int u, boolean[][] graph, int[] matchTo, boolean[] visited) {
        for (int v = 0; v < graph[0].length; v++) {
            if (graph[u][v] && !visited[v]) {
                visited[v] = true;
                if (matchTo[v] == -1 || dfs(matchTo[v], graph, matchTo, visited)) {
                    matchTo[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    private void writeResponse(int[] matching) {
        for (int i = 0; i < matching.length; ++i) {
            if (i > 0) out.print(" ");
            if (matching[i] == -1) out.print("-1");
            else out.print(matching[i] + 1); // da 0-based a 1-based
        }
        out.println();
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
