import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class GSMNetwork {
    private final InputReader reader;
    private final OutputWriter writer;

    public GSMNetwork(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new GSMNetwork(reader, writer).run();
        writer.writer.flush();
    }

    class Edge {
        int from;
        int to;
    }

    class ConvertGSMNetworkProblemToSat {
        int numVertices;
        Edge[] edges;

        ConvertGSMNetworkProblemToSat (int n, int m) {
            numVertices = n;
            edges = new Edge[m];
            for (int i = 0; i < m; ++i) {
                edges[i] = new Edge();
            }
        }

        void printEquisatisfiableSatFormula() {
            int n = numVertices;
            int m = edges.length;
            int vars = n * 3; // 3 colori per vertice
            StringBuilder sb = new StringBuilder();
            List<String> clauses = new ArrayList<>();

            // Ogni vertice ha almeno un colore
            for (int v = 1; v <= n; v++) {
                int x1 = 3 * (v - 1) + 1;
                int x2 = 3 * (v - 1) + 2;
                int x3 = 3 * (v - 1) + 3;
                clauses.add(x1 + " " + x2 + " " + x3 + " 0");
                // Ogni vertice ha al massimo un colore
                clauses.add(-x1 + " " + -x2 + " 0");
                clauses.add(-x1 + " " + -x3 + " 0");
                clauses.add(-x2 + " " + -x3 + " 0");
            }

            // Vertici adiacenti non possono avere lo stesso colore
            for (Edge e : edges) {
                int u = e.from;
                int v = e.to;
                int[] xu = { 3 * (u - 1) + 1, 3 * (u - 1) + 2, 3 * (u - 1) + 3 };
                int[] xv = { 3 * (v - 1) + 1, 3 * (v - 1) + 2, 3 * (v - 1) + 3 };
                for (int color = 0; color < 3; color++)
                    clauses.add(-xu[color] + " " + -xv[color] + " 0");
            }

            // Stampa numero clausole e numero variabili
            sb.append(clauses.size()).append(" ").append(vars).append("\n");
            for (String c : clauses)
                sb.append(c).append("\n");
            writer.printf(sb.toString());
        }
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();

        ConvertGSMNetworkProblemToSat  converter = new ConvertGSMNetworkProblemToSat (n, m);
        for (int i = 0; i < m; ++i) {
            converter.edges[i].from = reader.nextInt();
            converter.edges[i].to = reader.nextInt();
        }

        converter.printEquisatisfiableSatFormula();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
