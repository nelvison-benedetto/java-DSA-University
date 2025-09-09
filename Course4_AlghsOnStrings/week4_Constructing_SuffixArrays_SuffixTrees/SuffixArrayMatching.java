import java.io.*;
import java.util.*;

public class SuffixArrayMatching {
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

    // Costruzione suffix array O(n log n)
    public int[] computeSuffixArray(String text) {
        int n = text.length();
        Integer[] sa = new Integer[n];
        for (int i = 0; i < n; i++) sa[i] = i;

        Arrays.sort(sa, (a, b) -> text.substring(a).compareTo(text.substring(b)));
        int[] result = new int[n];
        for (int i = 0; i < n; i++) result[i] = sa[i];
        return result;
    }

    // Cerca occorrenze del pattern usando binary search sul suffix array
    public List<Integer> findOccurrences(String pattern, String text, int[] sa) {
        List<Integer> result = new ArrayList<>();
        int n = sa.length;

        int left = 0, right = n - 1;

        // Trova il primo suffisso >= pattern
        while (left < right) {
            int mid = (left + right) / 2;
            String suf = text.substring(sa[mid]);
            if (suf.compareTo(pattern) >= 0) right = mid;
            else left = mid + 1;
        }
        int start = left;

        // Controlla se pattern effettivamente compare
        if (!text.substring(sa[start]).startsWith(pattern)) return result;

        // Trova l’ultimo suffisso che inizia con pattern
        left = start;
        right = n - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (text.substring(sa[mid]).startsWith(pattern)) left = mid;
            else right = mid - 1;
        }
        int end = left;

        // Aggiungi tutte le posizioni
        for (int i = start; i <= end; i++) result.add(sa[i]);
        return result;
    }

    static public void main(String[] args) throws IOException {
        new SuffixArrayMatching().run();
    }

    public void print(boolean[] x) {
        for (int i = 0; i < x.length; i++) {
            if (x[i]) System.out.print(i + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next() + "$";
        int[] sa = computeSuffixArray(text);

        int patternCount = scanner.nextInt();
        boolean[] occurs = new boolean[text.length()];

        for (int i = 0; i < patternCount; i++) {
            String pattern = scanner.next();
            List<Integer> positions = findOccurrences(pattern, text, sa);
            for (int pos : positions) occurs[pos] = true;
        }

        print(occurs);
    }
}
