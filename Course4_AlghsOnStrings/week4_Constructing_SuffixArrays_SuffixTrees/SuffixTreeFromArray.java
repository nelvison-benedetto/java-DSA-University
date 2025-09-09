import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SuffixTreeFromArray {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    // Data structure to store edges of a suffix tree
    public class Edge {
        int node;   // nodo di arrivo
        int start;  // inizio substring nel testo
        int end;    // fine substring nel testo (esclusivo)

        Edge(int node, int start, int end) {
            this.node = node;
            this.start = start;
            this.end = end;
        }
    }

    Map<Integer, List<Edge>> SuffixTreeFromSuffixArray(int[] sa, int[] lcp, final String text) {
        Map<Integer, List<Edge>> tree = new HashMap<>();
        int n = text.length();
        int nodeId = 0; // nodo radice
        int[] stack = new int[n]; // stack dei nodi
        int[] stackLCP = new int[n]; // stack dei valori LCP
        int top = 0;

        stack[top] = nodeId; // radice
        stackLCP[top] = 0;
        top++;

        for (int i = 0; i < n; i++) {
            int curLCP = i == 0 ? 0 : lcp[i - 1];
            int curSuffix = sa[i];

            // riduci lo stack finché LCP corrente <= LCP in cima
            while (top > 0 && stackLCP[top - 1] > curLCP) {
                top--;
            }

            int parent = stack[top - 1];
            int edgeStart = curSuffix + stackLCP[top - 1];

            // crea nodo interno se necessario
            if (top > 0 && stackLCP[top - 1] < curLCP) {
                nodeId++;
                if (!tree.containsKey(parent)) tree.put(parent, new ArrayList<>());
                tree.get(parent).add(new Edge(nodeId, edgeStart, curSuffix + curLCP));
                stack[top] = nodeId;
                stackLCP[top] = curLCP;
                top++;
                parent = nodeId;
                edgeStart = curSuffix + curLCP;
            }

            // crea il nodo foglia
            nodeId++;
            if (!tree.containsKey(parent)) tree.put(parent, new ArrayList<>());
            tree.get(parent).add(new Edge(nodeId, edgeStart, n));
        }

        return tree;
    }

    static public void main(String[] args) throws IOException {
        new SuffixTreeFromArray().run();
    }

    public void print(ArrayList<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int n = text.length();
        int[] sa = new int[n];
        int[] lcp = new int[n - 1];

        for (int i = 0; i < n; ++i) sa[i] = scanner.nextInt();
        for (int i = 0; i + 1 < n; ++i) lcp[i] = scanner.nextInt();

        System.out.println(text);

        Map<Integer, List<Edge>> tree = SuffixTreeFromSuffixArray(sa, lcp, text);
        ArrayList<String> result = new ArrayList<>();

        // stampa gli edge in ordine corretto usando stack
        int[] nodeStack = new int[n];
        int[] edgeIndexStack = new int[n];
        nodeStack[0] = 0;
        edgeIndexStack[0] = 0;
        int stackSize = 1;

        while (stackSize > 0) {
            int node = nodeStack[stackSize - 1];
            int edgeIndex = edgeIndexStack[stackSize - 1];
            stackSize--;

            if (tree.get(node) == null) continue;

            if (edgeIndex + 1 < tree.get(node).size()) {
                nodeStack[stackSize] = node;
                edgeIndexStack[stackSize] = edgeIndex + 1;
                stackSize++;
            }

            Edge edge = tree.get(node).get(edgeIndex);
            result.add(edge.start + " " + edge.end);

            nodeStack[stackSize] = edge.node;
            edgeIndexStack[stackSize] = 0;
            stackSize++;
        }

        print(result);
    }
}
