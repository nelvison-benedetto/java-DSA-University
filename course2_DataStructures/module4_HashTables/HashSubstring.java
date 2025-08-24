import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static final int prime = 1000000007;
    private static final int multiplier = 263;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur + " ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern;
        String t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<>();

        long patternHash = 0;
        long textHash = 0;
        long power = 1;

        // Calcolo hash del pattern e del primo segmento di testo
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * multiplier + s.charAt(i)) % prime;
            textHash = (textHash * multiplier + t.charAt(i)) % prime;
            if (i < m - 1) power = (power * multiplier) % prime;
        }

        for (int i = 0; i + m <= n; i++) {
            if (patternHash == textHash) {
                // Verifica reale per evitare collisione
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (s.charAt(j) != t.charAt(i + j)) {
                        match = false;
                        break;
                    }
                }
                if (match) occurrences.add(i);
            }
            if (i + m < n) {
                // Aggiorna rolling hash
                textHash = (textHash - t.charAt(i) * power % prime + prime) % prime;
                textHash = (textHash * multiplier + t.charAt(i + m)) % prime;
            }
        }

        return occurrences;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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
