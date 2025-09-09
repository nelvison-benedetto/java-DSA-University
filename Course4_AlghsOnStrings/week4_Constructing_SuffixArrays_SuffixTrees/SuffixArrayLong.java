import java.util.*;
import java.io.*;

public class SuffixArrayLong {
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

    // Build suffix array in O(n log n)
    public int[] computeSuffixArray(String text) {
        int n = text.length();
        int[] sa = new int[n];     // suffix array
        int[] rank = new int[n];   // current ranks
        int[] tmp = new int[n];    // temporary ranks
        int[] cnt;                 // counting sort array

        // Initialize rank by characters (ASCII mapping)
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = text.charAt(i);
        }

        for (int k = 1; k < n; k <<= 1) {
            final int K = k;

            // radix/counting sort by second key (rank[i + k])
            cnt = new int[Math.max(256, n)]; // enough for ASCII + n
            for (int i = 0; i < n; i++) {
                int idx = i + K < n ? rank[i + K] : 0;
                cnt[idx]++;
            }
            for (int i = 1; i < cnt.length; i++) cnt[i] += cnt[i - 1];
            int[] sa2 = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                int idx = sa[i] + K < n ? rank[sa[i] + K] : 0;
                sa2[--cnt[idx]] = sa[i];
            }
            sa = sa2;

            // radix/counting sort by first key (rank[i])
            cnt = new int[Math.max(256, n)];
            for (int i = 0; i < n; i++) cnt[rank[i]]++;
            for (int i = 1; i < cnt.length; i++) cnt[i] += cnt[i - 1];
            sa2 = new int[n];
            for (int i = n - 1; i >= 0; i--) sa2[--cnt[rank[sa[i]]]] = sa[i];
            sa = sa2;

            // assign new ranks
            tmp[sa[0]] = 0;
            int r = 0;
            for (int i = 1; i < n; i++) {
                if (rank[sa[i]] != rank[sa[i - 1]] ||
                    ((sa[i] + K < n ? rank[sa[i] + K] : -1) != (sa[i - 1] + K < n ? rank[sa[i - 1] + K] : -1))) {
                    r++;
                }
                tmp[sa[i]] = r;
            }
            System.arraycopy(tmp, 0, rank, 0, n);
            if (r == n - 1) break; // all ranks are distinct, done
        }

        return sa;
    }

    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
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
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
