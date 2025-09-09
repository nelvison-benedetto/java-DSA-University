import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BWMatching {
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

    // Preprocessing step
    private void PreprocessBWT(String bwt,
                               Map<Character, Integer> starts,
                               Map<Character, int[]> occ_counts_before) {

        int n = bwt.length();
        char[] bwtArr = bwt.toCharArray();

        // First column = sorted bwt
        char[] firstCol = bwt.toCharArray();
        Arrays.sort(firstCol);

        // Build starts: first occurrence of each char in firstCol
        for (int i = 0; i < n; i++) {
            char c = firstCol[i];
            if (!starts.containsKey(c)) {
                starts.put(c, i);
            }
        }

        // Initialize occ_counts_before for A,C,G,T,$
        char[] alphabet = {'$', 'A', 'C', 'G', 'T'};
        for (char c : alphabet) {
            occ_counts_before.put(c, new int[n + 1]);
        }

        // Fill occ_counts_before
        for (int i = 0; i < n; i++) {
            char c = bwtArr[i];
            for (char a : alphabet) {
                occ_counts_before.get(a)[i + 1] = occ_counts_before.get(a)[i];
            }
            occ_counts_before.get(c)[i + 1]++;
        }
    }

    // Count pattern occurrences in text using preprocessed data
    int CountOccurrences(String pattern,
                         String bwt,
                         Map<Character, Integer> starts,
                         Map<Character, int[]> occ_counts_before) {
        int top = 0;
        int bottom = bwt.length() - 1;

        for (int i = pattern.length() - 1; i >= 0 && top <= bottom; i--) {
            char symbol = pattern.charAt(i);
            if (!occ_counts_before.containsKey(symbol)) {
                return 0;
            }

            int topCount = occ_counts_before.get(symbol)[top];
            int bottomCount = occ_counts_before.get(symbol)[bottom + 1];

            if (bottomCount - topCount > 0) {
                top = starts.get(symbol) + topCount;
                bottom = starts.get(symbol) + bottomCount - 1;
            } else {
                return 0;
            }
        }

        return (top <= bottom) ? bottom - top + 1 : 0;
    }

    static public void main(String[] args) throws IOException {
        new BWMatching().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();

        Map<Character, Integer> starts = new HashMap<>();
        Map<Character, int[]> occ_counts_before = new HashMap<>();
        PreprocessBWT(bwt, starts, occ_counts_before);

        int patternCount = scanner.nextInt();
        String[] patterns = new String[patternCount];
        int[] result = new int[patternCount];

        for (int i = 0; i < patternCount; ++i) {
            patterns[i] = scanner.next();
            result[i] = CountOccurrences(patterns[i], bwt, starts, occ_counts_before);
        }

        print(result);
    }
}
