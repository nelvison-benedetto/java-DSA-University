import java.io.*;
import java.util.StringTokenizer;

public class SubstringEquality_hashprefix {

    // static class Solver {
    //     private String s;
    //     private long[] hash;   // hash prefix
    //     private long[] power;  // potenze del base
    //     private final int p = 31;
    //     private final int mod = 1000000009;
    //     public Solver(String s) {
    //         this.s = s;
    //         int n = s.length();
    //         hash = new long[n + 1];
    //         power = new long[n + 1];
    //         power[0] = 1;
    //         for (int i = 0; i < n; i++) {
    //             hash[i + 1] = (hash[i] * p + (s.charAt(i) - 'a' + 1)) % mod;
    //             power[i + 1] = (power[i] * p) % mod;
    //         }
    //     }
    //     public boolean ask(int a, int b, int l) {
    //         long hashA = (hash[a + l] - hash[a] * power[l] % mod + mod) % mod;
    //         long hashB = (hash[b + l] - hash[b] * power[l] % mod + mod) % mod;
    //         return hashA == hashB;
    //     }
    // }
    // public void run() throws IOException {
    //     FastScanner in = new FastScanner();
    //     PrintWriter out = new PrintWriter(System.out);
    //     String s = in.next();
    //     int q = in.nextInt();
    //     Solver solver = new Solver(s);
    //     for (int i = 0; i < q; i++) {
    //         int a = in.nextInt();
    //         int b = in.nextInt();
    //         int l = in.nextInt();
    //         out.println(solver.ask(a, b, l) ? "Yes" : "No");
    //     }
    //     out.close();
    // }
    // public static void main(String[] args) throws IOException {
    //     new SubstringEquality().run();
    // }
    // class FastScanner {
    //     StringTokenizer tok = new StringTokenizer("");
    //     BufferedReader in;
    //     FastScanner() {
    //         in = new BufferedReader(new InputStreamReader(System.in));
    //     }
    //     String next() throws IOException {
    //         while (!tok.hasMoreElements())
    //             tok = new StringTokenizer(in.readLine());
    //         return tok.nextToken();
    //     }
    //     int nextInt() throws IOException {
    //         return Integer.parseInt(next());
    //     }
    // }

    //PERFECT SOLUTION!!! O(n)
    //a differenza di alghRobinKarp standart(ricerca pattern in un text), here verifichi se due sottostringhe qualunque di una stessa stringa sono uguali (in tempo O(1) dopo precomputazione)!
    //e.g.
    //s = "abacaba"
    //query (a=0, b=4, l=3) → "aba" e "aba" → Yes
    //query (a=1, b=2, l=2) → "ba" e "ac" → No
    //quindi è un algh di confronto di sottostringhe da usare quando devi confrontare tante coppie di substring di una stessa stringa!!!
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String s = in.next();  //str in input
        int n = s.length();
        int q = in.nextInt();  //number of queries
        // Precompute prefix hashes and powers
        long[] h1 = new long[n + 1];  //x convenzione: h[0]=0, h[i]=hash di s[0..i-1]
        long[] h2 = new long[n + 1];  //x convenzione: h[0]=0, h[i]=hash di s[0..i-1]
        long[] x1 = new long[n + 1];  //potenze di X modulo P1/P2: x[i] = X^i mod P
        long[] x2 = new long[n + 1];  //potenze di X modulo P1/P2: x[i] = X^i mod P
        precomputeHashesAndPowers(s, h1, h2, x1, x2); //at the end hai h1[i] e h2[i] per tutti i prefissi, x1[i] e x2[i] per tutte le potenze necessarie.
        // Answer queries
        for( int i=0; i<q; i++ ){  //x each query...
            int a = in.nextInt();
            int b = in.nextInt();
            int l = in.nextInt();
            if( areEqual(a, b, l, h1, h2, x1, x2) ){
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
        out.flush();  //svuota il buffer
    }
    static class FastScanner {  //xk BufferedReader+StringTokenizer è molto più veloce di Scanner per input grandi, ed eviti Time Limit Exceeded
        BufferedReader br;
        StringTokenizer st;
        FastScanner(){ 
            br = new BufferedReader( new InputStreamReader(System.in) );
        }
        String next() throws IOException { //legge token per token, ricaricando la riga quando serve
            while(st == null || !st.hasMoreTokens())
                st = new StringTokenizer( br.readLine() );
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }  //return token convertito in int
    }
    static final long P1 = 1_000_000_007L;  //module primo big big, riduce possibilita collisions
    static final long P2 = 1_000_000_009L;  //module primo big big, riduce possibilita collisions
    static final long X = 263L;  //moltiplicatore di base
    // Precomputa hash prefissi e potenze di X
    static void precomputeHashesAndPowers( String s, long[] h1, long[] h2, long[] x1, long[] x2 ) {
        int n = s.length();
        h1[0] = h2[0] = 0;
        x1[0] = x2[0] = 1;  //xk X^0 = 1
        for( int i=1; i<=n; i++ ){
            int code = s.charAt(i - 1);  //prende il codice Unicode (per input solo lettere minuscole va bene)
            h1[i] = (X * h1[i - 1] + code) % P1;  //definisce il prefisso-hash nella forma highest-power-first accumulata incrementally: se h1[i-1] = H(s[0..i-2]), allora h1[i] = H(s[0..i-1]) = h1[i-1]*X + s[i-1] (mod P1)
            h2[i] = (X * h2[i - 1] + code) % P2;
            x1[i] = (x1[i - 1] * X) % P1;  //x1[i] memorizza X^i mod P1
            x2[i] = (x2[i - 1] * X) % P2;
        }
        //ora hai h1[i] e h2[i] per tutti i prefissi, x1[i] e x2[i] per tutte le potenze necessarie.
    }
    static boolean areEqual( int a, int b, int l,
        long[] h1, long[] h2, long[] x1, long[] x2 ){  //calcolo hash di substring s[a..a+l-1] (mod p1 e p2) e confronto doppio
            long hashA1 = (h1[a + l] - (x1[l] * h1[a]) % P1 + P1) % P1;  //cal hash substring
            long hashB1 = (h1[b + l] - (x1[l] * h1[b]) % P1 + P1) % P1;
            if (hashA1 != hashB1) return false;
            //se sono uguali, potrebbe essere vero match o collisione modulo P1 → si verifica con secondo hash(hashA2/hashB2)
            long hashA2 = (h2[a + l] - (x2[l] * h2[a]) % P2 + P2) % P2;
            long hashB2 = (h2[b + l] - (x2[l] * h2[b]) % P2 + P2) % P2;
            return hashA2 == hashB2;  //se anche i due secondi hash coincidono → ritorna true (probabilità di collisione estremamente bassa: ≤ ~1e-9 o molto più bassa, per questo esercizio è accettabile)
    }

}
