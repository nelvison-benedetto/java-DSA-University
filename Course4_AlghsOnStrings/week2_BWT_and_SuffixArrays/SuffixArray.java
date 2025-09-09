import java.util.*;
import java.io.*;

public class SuffixArray {
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

    public class Suffix implements Comparable<Suffix> {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Suffix other) {
            return this.suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text
    public int[] computeSuffixArray(String text) {
        int n = text.length();
        Suffix[] suffixes = new Suffix[n];

        // genera tutti i suffissi
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(text.substring(i), i);
        }

        // ordina i suffissi
        Arrays.sort(suffixes);

        // costruisci il risultato con gli indici di partenza
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = suffixes[i].start;
        }

        return result;
    }

    static public void main(String[] args) throws IOException {
        new SuffixArray().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffixArray = computeSuffixArray(text);
        print(suffixArray);
    }
}
