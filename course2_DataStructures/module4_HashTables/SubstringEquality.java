import java.io.*;
import java.util.StringTokenizer;

public class SubstringEquality {

    static class Solver {
        private String s;
        private long[] hash;   // hash prefix
        private long[] power;  // potenze del base
        private final int p = 31;
        private final int mod = 1000000009;

        public Solver(String s) {
            this.s = s;
            int n = s.length();
            hash = new long[n + 1];
            power = new long[n + 1];
            power[0] = 1;
            for (int i = 0; i < n; i++) {
                hash[i + 1] = (hash[i] * p + (s.charAt(i) - 'a' + 1)) % mod;
                power[i + 1] = (power[i] * p) % mod;
            }
        }

        public boolean ask(int a, int b, int l) {
            long hashA = (hash[a + l] - hash[a] * power[l] % mod + mod) % mod;
            long hashB = (hash[b + l] - hash[b] * power[l] % mod + mod) % mod;
            return hashA == hashB;
        }
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String s = in.next();
        int q = in.nextInt();
        Solver solver = new Solver(s);
        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int l = in.nextInt();
            out.println(solver.ask(a, b, l) ? "Yes" : "No");
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SubstringEquality().run();
    }

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
}
