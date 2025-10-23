import java.io.*;
import java.util.*;

public class LongestCommonSubstring_hashing {

    // static class Answer {
    //     int i, j, len;
    //     Answer(int i, int j, int len) {
    //         this.i = i;
    //         this.j = j;
    //         this.len = len;
    //     }
    // }
    // private static final long MOD1 = 1_000_000_007L;
    // private static final long MOD2 = 1_000_000_009L;
    // private static final long BASE = 911382323L; // base random grande
    // static class Hash {
    //     long[] h1, h2, pow1, pow2;
    //     public Hash(String s) {
    //         int n = s.length();
    //         h1 = new long[n + 1];
    //         h2 = new long[n + 1];
    //         pow1 = new long[n + 1];
    //         pow2 = new long[n + 1];
    //         pow1[0] = pow2[0] = 1;
    //         for (int i = 0; i < n; i++) {
    //             h1[i + 1] = (h1[i] * BASE + s.charAt(i)) % MOD1;
    //             h2[i + 1] = (h2[i] * BASE + s.charAt(i)) % MOD2;
    //             pow1[i + 1] = (pow1[i] * BASE) % MOD1;
    //             pow2[i + 1] = (pow2[i] * BASE) % MOD2;
    //         }
    //     }
    //     public long getHash1(int l, int r) { // [l, r)
    //         return (h1[r] - h1[l] * pow1[r - l] % MOD1 + MOD1) % MOD1;
    //     }
    //     public long getHash2(int l, int r) {
    //         return (h2[r] - h2[l] * pow2[r - l] % MOD2 + MOD2) % MOD2;
    //     }
    //     public long getPair(int l, int r) {
    //         return (getHash1(l, r) << 32) ^ getHash2(l, r);
    //     }
    // }
    // public Answer solve(String s, String t) {
    //     Hash hs = new Hash(s);
    //     Hash ht = new Hash(t);
    //     int left = 0, right = Math.min(s.length(), t.length());
    //     Answer best = new Answer(0, 0, 0);
    //     while (left <= right) {
    //         int mid = (left + right) / 2;
    //         Map<Long, Integer> seen = new HashMap<>();
    //         // tutte le sottostringhe di lunghezza mid in s
    //         for (int i = 0; i + mid <= s.length(); i++) {
    //             seen.put(hs.getPair(i, i + mid), i);
    //         }
    //         int foundI = -1, foundJ = -1;
    //         for (int j = 0; j + mid <= t.length(); j++) {
    //             long h = ht.getPair(j, j + mid);
    //             if (seen.containsKey(h)) {
    //                 foundI = seen.get(h);
    //                 foundJ = j;
    //                 break;
    //             }
    //         }
    //         if (foundI != -1) {
    //             best = new Answer(foundI, foundJ, mid);
    //             left = mid + 1; // cerca più lungo
    //         } else {
    //             right = mid - 1;
    //         }
    //     }
    //     return best;
    // }
    // public void run() throws IOException {
    //     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //     PrintWriter out = new PrintWriter(System.out);
    //     String line;
    //     while ((line = in.readLine()) != null) {
    //         StringTokenizer tok = new StringTokenizer(line);
    //         String s = tok.nextToken();
    //         String t = tok.nextToken();
    //         Answer ans = solve(s, t);
    //         out.format("%d %d %d\n", ans.i, ans.j, ans.len);
    //     }
    //     out.close();
    // }
    // public static void main(String[] args) throws IOException {
    //     new CommonSubstring().run();
    // }

    //PERFECT SOLUTION!!!  O((∣s∣+∣t∣) ⋅ log(min(∣s∣,∣t∣)))s
    //dopo la binary search, il valore L massimo trovato sarà la lunghezza della più lunga sottostringa comune, return also le posizioni i e j salvate durante il check.
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        while( in.hasNext() ){
            String s = in.next();
            String t = in.next();
            int[] ans = longestCommonSubstring(s, t);
            out.printf("%d %d %d\n", ans[0], ans[1], ans[2]);
        }
        out.flush();
    }
    static class FastScanner{  //xk BufferedReader+StringTokenizer è molto più veloce di Scanner per input grandi, ed eviti Time Limit Exceeded
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean hasNext() throws IOException {  //legge token per token, ricaricando la riga quando serve
            if( st!=null && st.hasMoreTokens() ) return true;
            String line = br.readLine();
            if( line==null ) return false;
            st = new StringTokenizer(line);
            return hasNext();
        }
        String next() throws IOException { hasNext(); return st.nextToken(); }  //return token convertito in ints
    }
    static int[] longestCommonSubstring( String s, String t ){
        int n = s.length(), m = t.length();
        long[] hs1 = new long[n + 1], hs2 = new long[n + 1];
        long[] ht1 = new long[m + 1], ht2 = new long[m + 1];
        long[] xp1 = new long[Math.max(n, m) + 1];
        long[] xp2 = new long[Math.max(n, m) + 1];
        precompute( s, hs1, hs2, xp1, xp2 );
        precompute( t, ht1, ht2, xp1, xp2 );
        int low = 0, high = Math.min(n, m);
        int bestLen = 0, bestI = 0, bestJ = 0;
        while( low <= high ){
            int mid = (low + high) / 2;
            int[] pos = commonSubstringOfLength( mid, s, t, hs1, hs2, ht1, ht2, xp1, xp2 );
            if( pos != null ){
                bestLen = mid;
                bestI = pos[0];
                bestJ = pos[1];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new int[]{ bestI, bestJ, bestLen };
    }
    static final long P1 = 1_000_000_007L;
    static final long P2 = 1_000_000_009L;
    static final long X = 263L;
    static void precompute( String s, long[] h1, long[] h2, long[] xp1, long[] xp2 ){
        int n = s.length();
        if( xp1[0] == 0 ) xp1[0] = xp2[0] = 1;
        for( int i=1; i<=n; i++ ){
            int code = s.charAt(i - 1);
            h1[i] = (h1[i - 1] * X + code) % P1;
            h2[i] = (h2[i - 1] * X + code) % P2;
            xp1[i] = (xp1[i - 1] * X) % P1;
            xp2[i] = (xp2[i - 1] * X) % P2;
        }
    }
    static int[] commonSubstringOfLength( int L, String s, String t,
                                         long[] hs1, long[] hs2,
                                         long[] ht1, long[] ht2,
                                         long[] xp1, long[] xp2 ){
        if (L == 0) return new int[]{0, 0};
        Map<Long, Integer> seen = new HashMap<>();
        int n = s.length(), m = t.length();
        for( int i=0; i+L<=n; i++ ){
            long h1 = ( hs1[i+L] - (xp1[L]*hs1[i]) % P1 + P1 ) % P1;
            long h2 = ( hs2[i+L] - (xp2[L]*hs2[i]) % P2 + P2 ) % P2;
            long combined = ( h1<<32 ) ^ h2;  // unisci due hash in un long
            seen.put(combined, i);
        }
        for( int j=0; j+L<=m; j++ ){
            long h1 = ( ht1[j+L] - (xp1[L]*ht1[j]) % P1 + P1 ) % P1;
            long h2 = ( ht2[j+L] - (xp2[L]*ht2[j]) % P2 + P2 ) % P2;
            long combined = ( h1<<32 ) ^ h2;
            if( seen.containsKey(combined) )
                return new int[]{ seen.get(combined), j };
        }
        return null;
    }


}
