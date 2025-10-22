import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class DisjoinSetUnion_PathCompression_howMergingTables {

    // private final InputReader reader;
    // private final OutputWriter writer;
    // public MergingTables(InputReader reader, OutputWriter writer) {
    //     this.reader = reader;
    //     this.writer = writer;
    // }
    // public static void main(String[] args) {
    //     InputReader reader = new InputReader(System.in);
    //     OutputWriter writer = new OutputWriter(System.out);
    //     new MergingTables(reader, writer).run();
    //     writer.writer.flush();
    // }
    // class Table {
    //     Table parent;
    //     int rank;
    //     int numberOfRows;
    //     Table(int numberOfRows) {
    //         this.numberOfRows = numberOfRows;
    //         rank = 0;
    //         parent = this;
    //     }
    //     Table getParent() {
    //         if (parent != this) {
    //             parent = parent.getParent(); // path compression
    //         }
    //         return parent;
    //     }
    // }
    // int maximumNumberOfRows = -1;
    // void merge(Table destination, Table source) {
    //     Table realDestination = destination.getParent();
    //     Table realSource = source.getParent();
    //     if (realDestination == realSource) {
    //         return;
    //     }
    //     // union by rank
    //     if (realDestination.rank < realSource.rank) {
    //         realDestination.parent = realSource;
    //         realSource.numberOfRows += realDestination.numberOfRows;
    //         realDestination.numberOfRows = 0;
    //         maximumNumberOfRows = Math.max(maximumNumberOfRows, realSource.numberOfRows);
    //     } else {
    //         realSource.parent = realDestination;
    //         realDestination.numberOfRows += realSource.numberOfRows;
    //         realSource.numberOfRows = 0;
    //         maximumNumberOfRows = Math.max(maximumNumberOfRows, realDestination.numberOfRows);
    //         if (realDestination.rank == realSource.rank) {
    //             realDestination.rank++;
    //         }
    //     }
    // }
    // public void run() {
    //     int n = reader.nextInt();
    //     int m = reader.nextInt();
    //     Table[] tables = new Table[n];
    //     for (int i = 0; i < n; i++) {
    //         int numberOfRows = reader.nextInt();
    //         tables[i] = new Table(numberOfRows);
    //         maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
    //     }
    //     for (int i = 0; i < m; i++) {
    //         int destination = reader.nextInt() - 1;
    //         int source = reader.nextInt() - 1;
    //         merge(tables[destination], tables[source]);
    //         writer.printf("%d\n", maximumNumberOfRows);
    //     }
    // }
    // static class InputReader {
    //     public BufferedReader reader;
    //     public StringTokenizer tokenizer;
    //     public InputReader(InputStream stream) {
    //         reader = new BufferedReader(new InputStreamReader(stream), 32768);
    //         tokenizer = null;
    //     }
    //     public String next() {
    //         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
    //             try {
    //                 tokenizer = new StringTokenizer(reader.readLine());
    //             } catch (IOException e) {
    //                 throw new RuntimeException(e);
    //             }
    //         }
    //         return tokenizer.nextToken();
    //     }
    //     public int nextInt() {
    //         return Integer.parseInt(next());
    //     }
    // }
    // static class OutputWriter {
    //     public PrintWriter writer;
    //     OutputWriter(OutputStream stream) {
    //         writer = new PrintWriter(stream);
    //     }
    //     public void printf(String format, Object... args) {
    //         writer.print(String.format(Locale.ENGLISH, format, args));
    //     }
    // }

    //uso Disjoint-Set Union (DSU)(anche detto union-field) xk ogni insieme è rappresentato come un piccolo albero ed ogni nodo punta al suo genitore fino ad arrivare alla radice.
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //tot tabs in db
        int m = sc.nextInt();  //number ops merge tra 2 tabs
        int[] size = new int[n];  //staticarr, ogni slot indica le rows per X tab
        int[] parent = new int[n];  //staticarr, ogni slot indica il parent per X tab
        int maxSize = 0;  //massimo num rows in 1 singola tab
        for( int i=0; i<n; i++ ){  //per ogni tab...
            size[i] = sc.nextInt();  //save num rows x X tab
            parent[i] = i;  //set link for X tab, inizialmente ogni tabella è il suo stesso parent!!
            maxSize = Math.max( maxSize, size[i] );  //update maxsize se è da update
        }
        StringBuilder sb = new StringBuilder();
        for( int i=0; i<m; i++ ){  //x ogni merge leggi destinatione e source
            int destination = sc.nextInt() - 1;   //-1 xk input is 1-based, 
            int source = sc.nextInt() - 1;
            int realDest = find( destination, parent );  //trova il real alla fine dei links
            int realSource = find( source, parent );  //trova il real alla fine dei links

            if( realDest != realSource ){  //effettua merge, portando tutti i dati su realDest
                size[realDest] += size[realSource];  //update slot in staticarr size
                size[realSource] = 0;  //update slot in staticarr size
                parent[realSource] = realDest;  //update slot in staticarr parent, create link
                maxSize = Math.max( maxSize, size[realDest] );  //update maxsize se è da update
            }
            sb.append(maxSize).append("\n");  //append actual maxsize in string sb 
        }
        System.out.print( sb.toString() );  //return sb as solution
    }
    static int find( int i, int[] parent ){  //🔥🔥PATH COMPRESSION!xk trova il capo-> fai in modo che tutti i nodi lungo il path puntino direttamente al capo (così le prossime ricerche saranno O(1))
        if( i != parent[i] )  //quindi c'è almeno 1 link(cioe re-direction come sui link chrome)...
            parent[i] = find( parent[i], parent );  //COOL COOL! path compression, xk dopo che vai in profondita nei link, poi alla risalita setti here this parent[i] con il return del 'children', e poi passi te stesso al tuo genitore!!
        return parent[i];
        //e.g.
        //parent = [1, 2, 3, 3]
        //   quindi 0 →1 →2 →3
        //i=0   parent[0]=1 → i!=parent[i] 
            //call  find(parent[i]) = find(1)
            //find(1)   parent[1] = 2 → chiami find(2)
            //find(2)   parent[2] = 3 → chiami find(3)
            //find(3)   parent[3] = 3 → ritorna 3 (è la root) ✅
        //Ora la ricorsione torna indietro:
        //find(2) riceve 3 → parent[2] = 3
        //find(1) riceve 3 → parent[1] = 3
        //find(0) riceve 3 → parent[0] = 3
    }
}
