import java.io.*;
import java.util.*;

public class CommonSubstring {
    static class Answer {
        int i, j, len;
        Answer(int i, int j, int len) {
            this.i = i;
            this.j = j;
            this.len = len;
        }
    }

    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE = 911382323L; // base random grande

    static class Hash {
        long[] h1, h2, pow1, pow2;

        public Hash(String s) {
            int n = s.length();
            h1 = new long[n + 1];
            h2 = new long[n + 1];
            pow1 = new long[n + 1];
            pow2 = new long[n + 1];
            pow1[0] = pow2[0] = 1;
            for (int i = 0; i < n; i++) {
                h1[i + 1] = (h1[i] * BASE + s.charAt(i)) % MOD1;
                h2[i + 1] = (h2[i] * BASE + s.charAt(i)) % MOD2;
                pow1[i + 1] = (pow1[i] * BASE) % MOD1;
                pow2[i + 1] = (pow2[i] * BASE) % MOD2;
            }
        }

        public long getHash1(int l, int r) { // [l, r)
            return (h1[r] - h1[l] * pow1[r - l] % MOD1 + MOD1) % MOD1;
        }

        public long getHash2(int l, int r) {
            return (h2[r] - h2[l] * pow2[r - l] % MOD2 + MOD2) % MOD2;
        }

        public long getPair(int l, int r) {
            return (getHash1(l, r) << 32) ^ getHash2(l, r);
        }
    }

    public Answer solve(String s, String t) {
        Hash hs = new Hash(s);
        Hash ht = new Hash(t);

        int left = 0, right = Math.min(s.length(), t.length());
        Answer best = new Answer(0, 0, 0);

        while (left <= right) {
            int mid = (left + right) / 2;
            Map<Long, Integer> seen = new HashMap<>();

            // tutte le sottostringhe di lunghezza mid in s
            for (int i = 0; i + mid <= s.length(); i++) {
                seen.put(hs.getPair(i, i + mid), i);
            }

            int foundI = -1, foundJ = -1;
            for (int j = 0; j + mid <= t.length(); j++) {
                long h = ht.getPair(j, j + mid);
                if (seen.containsKey(h)) {
                    foundI = seen.get(h);
                    foundJ = j;
                    break;
                }
            }

            if (foundI != -1) {
                best = new Answer(foundI, foundJ, mid);
                left = mid + 1; // cerca più lungo
            } else {
                right = mid - 1;
            }
        }
        return best;
    }

    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line;
        while ((line = in.readLine()) != null) {
            StringTokenizer tok = new StringTokenizer(line);
            String s = tok.nextToken();
            String t = tok.nextToken();
            Answer ans = solve(s, t);
            out.format("%d %d %d\n", ans.i, ans.j, ans.len);
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new CommonSubstring().run();
    }
}
