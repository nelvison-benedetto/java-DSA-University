import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class PatternMatchingWithMismatches {

    // public List<Integer> solve(int k, String text, String pattern) {
    //     ArrayList<Integer> pos = new ArrayList<>();
    //     int n = text.length();
    //     int m = pattern.length();
    //     for (int i = 0; i + m <= n; i++) {
    //         int mismatches = 0;
    //         for (int j = 0; j < m; j++) {
    //             if (text.charAt(i + j) != pattern.charAt(j)) {
    //                 mismatches++;
    //                 if (mismatches > k) break; // pruning
    //             }
    //         }
    //         if (mismatches <= k) {
    //             pos.add(i);
    //         }
    //     }
    //     return pos;
    // }
    // public void run() {
    //     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //     PrintWriter out = new PrintWriter(System.out);
    //     in.lines().forEach(line -> {
    //         StringTokenizer tok = new StringTokenizer(line);
    //         int k = Integer.valueOf(tok.nextToken());
    //         String text = tok.nextToken();
    //         String pattern = tok.nextToken();
    //         List<Integer> ans = solve(k, text, pattern);
    //         out.format("%d ", ans.size());
    //         out.println(ans.stream()
    //                 .map(n -> String.valueOf(n))
    //                 .collect(Collectors.joining(" "))
    //         );
    //     });
    //     out.close();
    // }
    // static public void main(String[] args) {
    //     new matching_with_mismatches().run();
    // }

    //PERFECT SOLUTION!!!
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );
        PrintWriter out = new PrintWriter(System.out);
        String line;
        while( (line = in.readLine()) != null ){
            String[] parts = line.trim().split(" ");
            if (parts.length < 3) continue;
            int k = Integer.parseInt(parts[0]);
            String t = parts[1];
            String p = parts[2];
            List<Integer> result = findMatches( t, p, k );
            out.print(result.size());
            for( int i : result ) out.print(" " + i);
            out.println();
        }
        out.flush();
    }
    static final long P1 = 1_000_000_007L;
    static final long P2 = 1_000_000_009L;
    static final long X  = 263L;
    static class Hash {
        long[] h1, h2, pow1, pow2;
        Hash(String s) {
            int n = s.length();
            h1 = new long[n + 1];
            h2 = new long[n + 1];
            pow1 = new long[n + 1];
            pow2 = new long[n + 1];
            pow1[0] = pow2[0] = 1;
            for( int i=0; i<n; i++ ){
                int c = s.charAt(i);
                h1[i + 1] = (h1[i] * X + c) % P1;
                h2[i + 1] = (h2[i] * X + c) % P2;
                pow1[i + 1] = (pow1[i] * X) % P1;
                pow2[i + 1] = (pow2[i] * X) % P2;
            }
        }
        long hash1( int l, int r ){
            return ( h1[r] - h1[l] * pow1[r-l] % P1 + P1 ) % P1;
        }
        long hash2( int l, int r ){
            return ( h2[r] - h2[l] * pow2[r-l] % P2 + P2 ) % P2;
        }
        boolean equals( int l1, int r1, Hash other, int l2, int r2 ){
            return hash1(l1,r1) == other.hash1(l2,r2) &&
                   hash2(l1,r1) == other.hash2(l2,r2);
        }
    }
    static List<Integer> findMatches( String t, String p, int k ){
        List<Integer> res = new ArrayList<>();
        int n = t.length(), m = p.length();
        if (m > n) return res;
        Hash ht = new Hash(t);
        Hash hp = new Hash(p);
        for( int i=0; i+m<=n; i++ ){
            int mismatches = 0;
            int leftT = i, leftP = 0;
            while( mismatches<=k ){
                int pos = findNextMismatch( ht, hp, leftT, leftP, m - leftP );
                if(pos == -1){  // no mismatch
                    res.add(i);
                    break;
                }
                mismatches++;
                if( mismatches>k ) break;
                leftT += pos + 1;
                leftP += pos + 1;
                if( leftP>=m ){
                    res.add(i);
                    break;
                }
            }
        }
        return res;
    }
    // Usa binary search per trovare il primo mismatch
    static int findNextMismatch( Hash ht, Hash hp, int startT, int startP, int len ){
        int low = 0, high = len;
        while( low<high ){
            int mid = (low + high) / 2;
            if( ht.equals(startT, startT + mid + 1, hp, startP, startP + mid + 1) )
                low = mid + 1;
            else
                high = mid;
        }
        if( low == len ) return -1;
        return low;
    }
    

}
