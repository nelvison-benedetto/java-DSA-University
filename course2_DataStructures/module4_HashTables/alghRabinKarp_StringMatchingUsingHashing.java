import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class alghRabinKarp_StringMatchingUsingHashing {

    // private static FastScanner in;
    // private static PrintWriter out;
    // private static final int prime = 1000000007;
    // private static final int multiplier = 263;
    // public static void main(String[] args) throws IOException {
    //     in = new FastScanner();
    //     out = new PrintWriter(new BufferedOutputStream(System.out));
    //     printOccurrences(getOccurrences(readInput()));
    //     out.close();
    // }
    // private static Data readInput() throws IOException {
    //     String pattern = in.next();
    //     String text = in.next();
    //     return new Data(pattern, text);
    // }
    // private static void printOccurrences(List<Integer> ans) throws IOException {
    //     for (Integer cur : ans) {
    //         out.print(cur + " ");
    //     }
    // }
    // private static List<Integer> getOccurrences(Data input) {
    //     String s = input.pattern;
    //     String t = input.text;
    //     int m = s.length(), n = t.length();
    //     List<Integer> occurrences = new ArrayList<>();
    //     long patternHash = 0;
    //     long textHash = 0;
    //     long power = 1;
    //     // Calcolo hash del pattern e del primo segmento di testo
    //     for (int i = 0; i < m; i++) {
    //         patternHash = (patternHash * multiplier + s.charAt(i)) % prime;
    //         textHash = (textHash * multiplier + t.charAt(i)) % prime;
    //         if (i < m - 1) power = (power * multiplier) % prime;
    //     }
    //     for (int i = 0; i + m <= n; i++) {
    //         if (patternHash == textHash) {
    //             // Verifica reale per evitare collisione
    //             boolean match = true;
    //             for (int j = 0; j < m; j++) {
    //                 if (s.charAt(j) != t.charAt(i + j)) {
    //                     match = false;
    //                     break;
    //                 }
    //             }
    //             if (match) occurrences.add(i);
    //         }
    //         if (i + m < n) {
    //             // Aggiorna rolling hash
    //             textHash = (textHash - t.charAt(i) * power % prime + prime) % prime;
    //             textHash = (textHash * multiplier + t.charAt(i + m)) % prime;
    //         }
    //     }
    //     return occurrences;
    // }
    // static class Data {
    //     String pattern;
    //     String text;
    //     public Data(String pattern, String text) {
    //         this.pattern = pattern;
    //         this.text = text;
    //     }
    // }
    // static class FastScanner {
    //     private BufferedReader reader;
    //     private StringTokenizer tokenizer;
    //     public FastScanner() {
    //         reader = new BufferedReader(new InputStreamReader(System.in));
    //         tokenizer = null;
    //     }
    //     public String next() throws IOException {
    //         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
    //             tokenizer = new StringTokenizer(reader.readLine());
    //         }
    //         return tokenizer.nextToken();
    //     }
    //     public int nextInt() throws IOException {
    //         return Integer.parseInt(next());
    //     }
    // }

    //PERFECT SOLUTION!!! 
    //naive O(n*m) confronta pattern con ogni posizione nel testo carattere per carattere
    //!!RobinKarp O(n+m) usa hash per confronti O(1), poi controlla solo in caso di match di hash!
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.next();  //text target da trovare all'interno della stringa molto lunga
        String text = sc.next();  //stringa molto lunga 
        sc.close();
        List<Integer> result = getOccurrences( pattern, text );  //run funct che esegue tutto, e return res as solution
        //output return lista idx di slots dove pattern inizia dentro la stringa lunga text.
        for( int i=0; i<result.size(); i++ ){
            System.out.print( result.get(i) );
            if( i+1<result.size() ) System.out.print(" ");
        }
    }
    static List<Integer> getOccurrences( String pattern, String text ){
        int m = pattern.length();
        int n = text.length();
        List<Integer> result = new ArrayList<>();
        if( m>n ) return result;  //se il pattern target è piu lungo del text field, return bc non ha soluzione
        long patternHash = polyHash( pattern );
        long[] H = precomputeHashes( text, m );
        for( int i=0; i<=n-m; i++ ){
            //scorre tutto il text: 
            //check l’hash del pattern (patternHash) con l’hash della finestra di testo corrispondente (H[i])
            //se gli hash sono uguali, fa un controllo diretto sui caratteri (per evitare falsi positivi)
            //se anche il confronto diretto va bene, aggiunge la posizione i all’elenco dei risultati.
            if( H[i]==patternHash && text.regionMatches( i, pattern, 0, m ) ){
                result.add(i);
            }
        }
        return result;
    }
    static final long P = 1_000_000_007L;  //modulo primo grande, per avoid collisions&overflow
    static final long X = 263L;  //moltiplicatore di base
    // Calcola hash di una stringa (highest-power-first)
    static long polyHash( String s ){  //costruisce l’hash x target pattern nella forma highest-power-first
        long hash = 0;
        for( int i=0; i<s.length(); i++ ){
            hash = ( hash * X + s.charAt(i) ) % P;
        }
        return hash;
    }
    // Precalcola hash di tutte le sottostringhe di lunghezza m
    static long[] precomputeHashes( String text, int m ){ //calcola in O(n) l’hash di ogni sottostringa text[i .. i+m-1] per tutti gli i possibili
        //calcola l’hash di tutte le sottostringhe consecutive di lunghezza m dentro text, ROLLING HASH.
        int n = text.length();
        long[] H = new long[n-m+1];  //create staticarr
        String S = text.substring( n-m );  //ultima finestra
        H[n-m] = polyHash(S);  //calc hash dell’ultima finestra (text[n-m .. n-1])
        long y = 1; //y è la potenza di X^m mod P
        for( int i=0; i<m; i++ ){  //calc y finale, cosi quando togli un char dalla windows dx (al quale hai gia calcolato l'hash) con y togli semplicemente quell'effetto SENZA ricalcolare tutto l'hash da capo!
            y = (y*X) % P;
        }
        for( int i=n-m-1; i>=0; i-- ){  //🔥ROLLING HASH, calc hash della finestra text[i .. i+m-1] a partire da quello successivo H[i+1], cioè della finestra text[i+1 .. i+m].
            H[i] = ( X*H[i + 1] + text.charAt(i) - y*text.charAt(i + m) ) % P;
            if( H[i]<0 ) H[i] += P; //evita valori negativi
        }
        return H;  //return staticarr, ogni H[i] rappresenta l’hash della sottostringa text[i .. i+m-1] (cioè la finestra di lunghezza m che inizia in posizione i)
    }

}
