import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class CleaningApartment {
    private final InputReader reader;
    private final OutputWriter writer;

    public CleaningApartment(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new CleaningApartment(reader, writer).run();
        writer.writer.flush();
    }

    class Edge {
        int from;
        int to;
    }

    class ConvertHampathToSat {
        int numVertices;
        Edge[] edges;

        ConvertHampathToSat(int n, int m) {
            numVertices = n;
            edges = new Edge[m];
            for (int i = 0; i < m; ++i) {
                edges[i] = new Edge();
            }
        }

        void printEquisatisfiableSatFormula() {
            int n = numVertices;
            int m = edges.length;
            boolean[][] connected = new boolean[n + 1][n + 1];
            for (Edge e : edges)
                connected[e.from][e.to] = connected[e.to][e.from] = true;

            int vars = n * n; // x_v,p -> variabile: v*n + p
            List<String> clauses = new ArrayList<>();

            // Ogni vertice almeno una volta
            for (int v = 1; v <= n; v++) {
                StringBuilder sb = new StringBuilder();
                for (int p = 1; p <= n; p++)
                    sb.append((v - 1) * n + p).append(" ");
                sb.append("0");
                clauses.add(sb.toString());
            }

            // Ogni vertice al massimo una volta
            for (int v = 1; v <= n; v++) {
                for (int p1 = 1; p1 <= n; p1++) {
                    for (int p2 = p1 + 1; p2 <= n; p2++) {
                        clauses.add((-((v - 1) * n + p1)) + " " + (-((v - 1) * n + p2)) + " 0");
                    }
                }
            }

            // Ogni posizione ha un vertice
            for (int p = 1; p <= n; p++) {
                StringBuilder sb = new StringBuilder();
                for (int v = 1; v <= n; v++)
                    sb.append((v - 1) * n + p).append(" ");
                sb.append("0");
                clauses.add(sb.toString());
            }

            // Nessuna posizione con due vertici
            for (int p = 1; p <= n; p++) {
                for (int u = 1; u <= n; u++) {
                    for (int v = u + 1; v <= n; v++) {
                        clauses.add((-((u - 1) * n + p)) + " " + (-((v - 1) * n + p)) + " 0");
                    }
                }
            }

            // Vertici consecutivi devono essere connessi
            for (int p = 1; p < n; p++) {
                for (int u = 1; u <= n; u++) {
                    for (int v = 1; v <= n; v++) {
                        if (!connected[u][v]) {
                            clauses.add((-((u - 1) * n + p)) + " " + (-((v - 1) * n + (p + 1))) + " 0");
                        }
                    }
                }
            }

            // Stampa numero clausole e numero variabili
            writer.printf("%d %d\n", clauses.size(), vars);
            for (String c : clauses)
                writer.printf("%s\n", c);
        }

    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();

        ConvertHampathToSat converter = new ConvertHampathToSat(n, m);
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
