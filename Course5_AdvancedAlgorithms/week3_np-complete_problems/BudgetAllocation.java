import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class BudgetAllocation {
    private final InputReader reader;
    private final OutputWriter writer;

    public BudgetAllocation(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new BudgetAllocation(reader, writer).run();
        writer.writer.flush();
    }

    class ConvertILPToSat {
        int[][] A;
        int[] b;

        ConvertILPToSat(int n, int m) {
            A = new int[n][m];
            b = new int[n];
        }

        void printEquisatisfiableSatFormula() {
            int n = A.length;
            int m = A[0].length;
            int vars = m;
            List<String> clauses = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                // Trova indici delle variabili non nulle
                int[] idx = new int[3];
                int[] coeff = new int[3];
                int cnt = 0;
                for (int j = 0; j < m; j++) {
                    if (A[i][j] != 0) {
                        idx[cnt] = j + 1; // variabili 1-based
                        coeff[cnt] = A[i][j];
                        cnt++;
                    }
                }
                // Cicla su tutte le combinazioni di 0/1 delle variabili non nulle
                int combos = 1 << cnt;
                for (int mask = 0; mask < combos; mask++) {
                    int sum = 0;
                    for (int k = 0; k < cnt; k++) {
                        if (((mask >> k) & 1) == 1)
                            sum += coeff[k];
                    }
                    if (sum > b[i]) {
                        // questa combinazione viola la disuguaglianza
                        StringBuilder sb = new StringBuilder();
                        for (int k = 0; k < cnt; k++) {
                            if (((mask >> k) & 1) == 1)
                                sb.append("-").append(idx[k]).append(" ");
                            else
                                sb.append(idx[k]).append(" ");
                        }
                        sb.append("0");
                        clauses.add(sb.toString());
                    }
                }
            }

            // Stampa numero clausole e variabili
            writer.printf("%d %d\n", clauses.size(), vars);
            for (String c : clauses)
                writer.printf("%s\n", c);
        }

    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();

        ConvertILPToSat converter = new ConvertILPToSat(n, m);
        for (int i = 0; i < n; ++i) {
          for (int j = 0; j < m; ++j) {
            converter.A[i][j] = reader.nextInt();
          }
        }
        for (int i = 0; i < n; ++i) {
            converter.b[i] = reader.nextInt();
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
