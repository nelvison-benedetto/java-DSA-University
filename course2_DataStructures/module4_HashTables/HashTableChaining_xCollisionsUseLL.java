import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HashTableChaining_xCollisionsUseLL {

    // private FastScanner in;
    // private PrintWriter out;
    // private int bucketCount;
    // private List<String>[] table;
    // private final int prime = 1000000007;
    // private final int multiplier = 263;
    // public static void main(String[] args) throws IOException {
    //     new HashChains().processQueries();
    // }
    // private int hashFunc(String s) {
    //     long hash = 0;
    //     for (int i = s.length() - 1; i >= 0; --i)
    //         hash = (hash * multiplier + s.charAt(i)) % prime;
    //     return (int) (hash % bucketCount);
    // }
    // private Query readQuery() throws IOException {
    //     String type = in.next();
    //     if (!type.equals("check")) {
    //         String s = in.next();
    //         return new Query(type, s);
    //     } else {
    //         int ind = in.nextInt();
    //         return new Query(type, ind);
    //     }
    // }
    // private void writeSearchResult(boolean wasFound) {
    //     out.println(wasFound ? "yes" : "no");
    // }
    // private void processQuery(Query query) {
    //     switch (query.type) {
    //         case "add":
    //             int hashAdd = hashFunc(query.s);
    //             if (!table[hashAdd].contains(query.s)) {
    //                 table[hashAdd].add(0, query.s); // inserimento in testa
    //             }
    //             break;
    //         case "del":
    //             int hashDel = hashFunc(query.s);
    //             table[hashDel].remove(query.s);
    //             break;
    //         case "find":
    //             int hashFind = hashFunc(query.s);
    //             writeSearchResult(table[hashFind].contains(query.s));
    //             break;
    //         case "check":
    //             int ind = query.ind;
    //             for (String s : table[ind]) {
    //                 out.print(s + " ");
    //             }
    //             out.println();
    //             break;
    //         default:
    //             throw new RuntimeException("Unknown query: " + query.type);
    //     }
    // }
    // public void processQueries() throws IOException {
    //     in = new FastScanner();
    //     out = new PrintWriter(new BufferedOutputStream(System.out));
    //     bucketCount = in.nextInt();
    //     int queryCount = in.nextInt();
    //     // inizializza array di bucket
    //     table = new ArrayList[bucketCount];
    //     for (int i = 0; i < bucketCount; i++) {
    //         table[i] = new ArrayList<>();
    //     }
    //     for (int i = 0; i < queryCount; ++i) {
    //         processQuery(readQuery());
    //     }
    //     out.close();
    // }
    // static class Query {
    //     String type;
    //     String s;
    //     int ind;
    //     public Query(String type, String s) {
    //         this.type = type;
    //         this.s = s;
    //     }
    //     public Query(String type, int ind) {
    //         this.type = type;
    //         this.ind = ind;
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

    //PERFECT SOLUTION!!! ogni op O(1) grazie a buona hash funct
    //hashtable associa chiave->valore quickly: ti viene passato in input 'nelvil'->trasformi in int w hashing->ora grazie a questo idx vai allo slot->GESTISCI COLLISIONI W CHAINING cioe lo slot puo anche contenere >1 item. w find() fa lo stesso arrivi a slot e poi cerchi nella LL.
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();  //tot buckets (cioe liste)
        int n = sc.nextInt();  //tot ops
        // Tabella hash con chaining
        List<String>[] table = new LinkedList[m];  
        //dynamicarr table ma con m slots fissi, e poi in ciscun slot sai che userai LinkedList.
        for( int i=0; i<m; i++ )
            table[i] = new LinkedList<>();  //riempio gli slot di dynamicarr table con LLs
        StringBuilder sb = new StringBuilder();
        for( int i=0; i<n; i++ ){
            String command = sc.next();  //save command from input
            //here non uso switch xk seno perdo block-scope per nome vars (visto che si chiamano tutti uguale)
            if( command.equals("add") ){  //inserisce string nella tabella (se già presente, ignora)
                String str = sc.next();  //str in input da aggiungere
                int hash = hashFunc(str, m);  //get int molto probabilmente UNIQUE come idx di dynamicarr table
                if( !table[hash].contains(str) ){  //solo se avvenute NO COLLISIONI
                    table[hash].addFirst(str);  //add in testa della target LL(linkedlist)
                }
            } else if ( command.equals("del") ){  //elimina string (se non c’è, ignora)
                String str = sc.next();
                int hash = hashFunc(str, m);  //calc hash and save in var
                table[hash].remove(str);  //remove target item da target LL 
            } else if ( command.equals("find") ){  //stampa "yes" se presente, "no" altrimenti
                String s = sc.next();
                int hash = hashFunc(s, m);
                sb.append(table[hash].contains(s) ? "yes" : "no").append("\n");
            } else if ( command.equals("check") ){  //print tutti gli elementi della lista (bucket) con indice i, in ordine di inserimento inverso
                int idx = sc.nextInt();
                for( String str : table[idx] )  //itera per ciascun item contenuti da LL target(che è in target slot di dynamicarr table)
                    sb.append(str).append(" ");
                sb.append("\n");
            }
        }
        System.out.print(sb.toString()); //return sb as solution
    }
    static final long P = 1_000_000_007;
    static final long X = 263;
    //polynomial rolling hash function
    static int hashFunc( String s, int m ){  //funzione Hash data nella consegna dell'esercizio, return int probabilmente unique come idx di dynamicarr table
        long hash = 0;
        long xPow = 1;
        for( int i=0; i<s.length(); i++ ){
            hash = ( hash + (s.charAt(i) * xPow) % P ) % P;
            xPow = ( xPow * X ) % P;
        }
        return (int)( hash % m );
    }

}
