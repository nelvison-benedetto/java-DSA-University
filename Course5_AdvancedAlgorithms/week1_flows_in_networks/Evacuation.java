import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayDeque;

public class Evacuation {
    
    private static FastScanner in;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();

        FlowGraph graph = readGraph();
        System.out.println(maxFlow(graph, 0, graph.size() - 1));
    }

    private static int maxFlow(FlowGraph graph, int from, int to) {
        int flow = 0;
        int[] parent = new int[graph.size()]; // salva il percorso della BFS

        while (true) {
            Arrays.fill(parent, -1);
            parent[from] = -2; // sorgente segnata

            int[] pathCapacity = new int[graph.size()];
            pathCapacity[from] = Integer.MAX_VALUE;

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(from);

            // BFS sul grafo residuo
            while (!queue.isEmpty() && parent[to] == -1) {
                int u = queue.poll();
                for (int id : graph.getIds(u)) {
                    FlowGraph.Edge e = graph.getEdge(id);
                    int v = e.to;
                    int residual = e.capacity - e.flow;
                    if (residual > 0 && parent[v] == -1) {
                        parent[v] = id;
                        pathCapacity[v] = Math.min(pathCapacity[u], residual);
                        queue.add(v);
                    }
                }
            }

            // Se non troviamo più percorso residuo, flusso massimo raggiunto
            if (parent[to] == -1) break;

            int increment = pathCapacity[to];
            flow += increment;

            // Aggiorna i flussi lungo il percorso trovato
            int v = to;
            while (v != from) {
                int id = parent[v];
                graph.addFlow(id, increment);
                v = graph.getEdge(id).from;
            }
        }

        return flow;
    }

    static FlowGraph readGraph() throws IOException {
        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();
        FlowGraph graph = new FlowGraph(vertex_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            graph.addEdge(from, to, capacity);
        }
        return graph;
    }

    static class Edge {
        int from, to, capacity, flow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    static class FlowGraph {
        private List<Edge> edges;
        private List<Integer>[] graph;

        public FlowGraph(int n) {
            this.graph = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; ++i)
                this.graph[i] = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int capacity) {
            Edge forwardEdge = new Edge(from, to, capacity);
            Edge backwardEdge = new Edge(to, from, 0);
            graph[from].add(edges.size());
            edges.add(forwardEdge);
            graph[to].add(edges.size());
            edges.add(backwardEdge);
        }

        public int size() {
            return graph.length;
        }

        public List<Integer> getIds(int from) {
            return graph[from];
        }

        public Edge getEdge(int id) {
            return edges.get(id);
        }

        public void addFlow(int id, int flow) {
            edges.get(id).flow += flow;
            edges.get(id ^ 1).flow -= flow;
        }
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
