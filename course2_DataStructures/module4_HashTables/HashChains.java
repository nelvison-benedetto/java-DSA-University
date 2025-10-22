import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

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

    

}
