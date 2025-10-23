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

    //PERFECT SOLUTION!!!  O(n · log m · k)  (naive bad O(n · m))
    //combina hashing efficiente e ricerca binaria per trovare tutte le occorrenze del pattern nel text field, tollerando fino a k differenze(mismatching), in tempo quasi lineare!
    //e.g.
    //k=1, t="ababab", p="baaa"
    //!!trova tutti gli indici i in t dove p compare (a partire da i) con al massimo k caratteri diversi.
    //1.calc hash prefixati del testo e del pattern (come nel Rabin–Karp migliorato o substring equality), questo permette di confrontare qualsiasi sottostringa di t e p in tempo O(1) usando due moduli (P1 e P2) per evitare collisioni.
    //2.per ogni posizione i nel testo dove il pattern potrebbe iniziare, l’algoritmo cerca di allineare p su t partendo da i, ma invece di confrontare carattere per carattere usa...
    //3.all’inizio leftT = i e leftP = 0. cerca il primo punto dove t e p differiscono nella finestra corrente, usando binary search + hashing. quindi invece di controllare ogni carattere (O(m)), fa log(m) confronti di sottostringhe usando hash: trova in O(log m) il primo mismatch!! 
    //4.ogni volta che trova una differenza incrementa mismatches e fa avanzare gli indici per saltare la parte già controllata: se si supera k mismatches → scarta quella posizione, se si arriva alla fine del pattern con ≤ k mismatches → aggiunge i ai risultati    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );
        PrintWriter out = new PrintWriter(System.out);
        String line;
        while( (line = in.readLine()) != null ){
            String[] parts = line.trim().split(" ");  //splitti la row input in 3 item: k t(text field) p(pattern)
            if( parts.length<3 ) continue;  //linea error in input, devi avere almeno 3 input per ciscuna row 
            int k = Integer.parseInt(parts[0]);  //k -> ints
            String t = parts[1];
            String p = parts[2];
            List<Integer> result = findMatches( t, p, k );  //!sreturn dynamicarr idxs in cui p appare in t con ≤ k mismatches
            out.print(result.size());
            for( int i : result ) out.print(" " + i);
            out.println();
        }
        out.flush();
    }
    static List<Integer> findMatches( String t, String p, int k ){
        List<Integer> res = new ArrayList<>();
        int n=t.length(), m=p.length();  //t=text filed, p=pattern
        if( m>n ) return res;  //se pattern p è più lungo del testo t, return no res
        Hash ht = new Hash(t);  //costruzione hash prefix
        Hash hp = new Hash(p);
        for( int i=0; i+m<=n; i++ ){ //cycle 0->n-m
            int mismatches = 0;  //conta quante differenze abbiamo già trovato per questa posizione
            int leftT=i, leftP=0;  //leftT e leftP sono gli offset correnti dentro testo e pattern da confrontare (inizialmente i e 0)
            while( mismatches<=k ){  //cycle fino a k+1 mismatch
                int pos = findNextMismatch( ht, hp, leftT, leftP, m - leftP );
                if(pos == -1){  //se tutto il resto è uguale → la sottostringa ha ≤ k mismatch → aggiungi i e interrompi il cycle while
                    res.add(i);
                    break;
                }
                mismatches++;  //altrimenti incrementa mismatches
                if( mismatches>k ) break;  //se superi k, interrompi (questa posizione i non va)
                //se non superi k, salti oltre il mismatch: leftT += pos + 1; leftP += pos + 1; e continui la ricerca (lo +1 sposta la finestra dopo il mismatch).
                leftT += pos + 1;
                leftP += pos + 1;
                if( leftP>=m ){  //se leftP >= m dopo lo spostamento, hai consumato l’intero pattern → match → aggiungi i
                    res.add(i);
                    break;
                }
            }
        }
        return res;
    }
    // Usa binary search per trovare il primo mismatch
    static int findNextMismatch( Hash ht, Hash hp, int startT, int startP, int len ){
        //cerca il primo mismatch nella porzione rimanente (lunghezza m - leftP) e restituisce la posizione relativa pos (0-based) del primo mismatch oppure -1 se non ci sono mismatch fino alla fine
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
    static final long P1 = 1_000_000_007L;  //module primo big big, riduce possibilita collisions
    static final long P2 = 1_000_000_009L;  //module primo big big, riduce possibilita collisionss
    static final long X  = 263L;  //moltiplicatore di base
    static class Hash {  //class Obj che costruisce e usa i prefissi hash
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
        //confronta entrambi gli hash (mod P1 e mod P2) per essere più sicuri contro collisioni
        //se entrambi i moduli coincidono, consideriamo le sottostringhe uguali con probabilità estremamente alta.
            return hash1(l1,r1) == other.hash1(l2,r2) &&
                   hash2(l1,r1) == other.hash2(l2,r2);
        }
    }
    
}
