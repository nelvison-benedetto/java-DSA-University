import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] size = new int[n];
        int[] parent = new int[n];
        int maxSize = 0;
        for( int i=0; i<n; i++ ){
            size[i] = sc.nextInt();
            parent[i] = i;  // inizialmente ogni tabella è il suo parent
            maxSize = Math.max( maxSize, size[i] );
        }
        StringBuilder sb = new StringBuilder();
        for( int i=0; i<m; i++ ){
            int dest = sc.nextInt() - 1;   // input 1-based
            int source = sc.nextInt() - 1;
            int realDest = find( dest, parent );
            int realSource = find( source, parent );
            if( realDest != realSource ){
                size[realDest] += size[realSource];
                size[realSource] = 0;
                parent[realSource] = realDest;
                maxSize = Math.max( maxSize, size[realDest] );
            }
            sb.append(maxSize).append("\n");
        }
        System.out.print( sb.toString() );
    }
    static int find( int i, int[] parent ){
        if( i != parent[i] )
            parent[i] = find( parent[i], parent );  // path compression
        return parent[i];
    }

}
