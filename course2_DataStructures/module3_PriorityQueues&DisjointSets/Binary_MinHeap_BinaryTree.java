import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Binary_MinHeap_BinaryTree {

    // private int[] data;
    // private List<Swap> swaps;
    // private FastScanner in;
    // private PrintWriter out;
    // public static void main(String[] args) throws IOException {
    //     new BuildHeap().solve();
    // }
    // private void readData() throws IOException {
    //     int n = in.nextInt();
    //     data = new int[n];
    //     for (int i = 0; i < n; ++i) {
    //         data[i] = in.nextInt();
    //     }
    // }
    // private void writeResponse() {
    //     out.println(swaps.size());
    //     for (Swap swap : swaps) {
    //         out.println(swap.index1 + " " + swap.index2);
    //     }
    // }
    // private void generateSwaps() {
    //     swaps = new ArrayList<>();
    //     int n = data.length;
    //     // Partiamo dall'ultimo nodo non foglia e facciamo sift-down
    //     for (int i = n / 2 - 1; i >= 0; i--) {
    //         siftDown(i);
    //     }
    // }
    // private void siftDown(int i) {
    //     int minIndex = i;
    //     int left = 2 * i + 1;
    //     if (left < data.length && data[left] < data[minIndex]) {
    //         minIndex = left;
    //     }
    //     int right = 2 * i + 2;
    //     if (right < data.length && data[right] < data[minIndex]) {
    //         minIndex = right;
    //     }
    //     if (i != minIndex) {
    //         swaps.add(new Swap(i, minIndex));
    //         int temp = data[i];
    //         data[i] = data[minIndex];
    //         data[minIndex] = temp;
    //         siftDown(minIndex);
    //     }
    // }
    // public void solve() throws IOException {
    //     in = new FastScanner();
    //     out = new PrintWriter(new BufferedOutputStream(System.out));
    //     readData();
    //     generateSwaps();
    //     writeResponse();
    //     out.close();
    // }
    // static class Swap {
    //     int index1;
    //     int index2;
    //     public Swap(int index1, int index2) {
    //         this.index1 = index1;
    //         this.index2 = index2;
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
        //traform arr in minheap con max complexity O(n) (non è realmente O(n log n) xk anche se siftDown() puo scedere fino in fondo, la maggior parte dei nodes sono leaf/quasi leaf )
    public static void main( String[] args ){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //legth 
        int[] arr = new int[n];
        for( int i=0;i<n;i++ ) arr[i]=sc.nextInt();  //crea il staticarr catturando gli input
        buildHeap( arr );
        System.out.println( swaps.size() );
        for( Swap sw: swaps ) System.out.println(sw.i + " " + sw.j);
    }
    static List<Swap> swaps = new ArrayList<>();
    static void buildHeap(int[] arr){
        swaps.clear();  //clean the dynamicarr swaps
        int n = arr.length;
        for( int i=n/2-1; i>= 0; i-- ){  //🔥🔥i nodi da n/2 in poi (i≥n/2) sono foglie!!. tutti i nodi 0->n/2-1(incluso) SONO PARENTS!!
            //iteriamo per ogni parent, inziando quindi dal parent piu in fondo a dx possibile.
            siftDown( i, arr );
        }
    }
    //e.g.🔥🔥 BINARY MIN-HEAP (BINARY TREE)
    //index:  0  1  2  3  4  5  6
    //value: [A, B, C, D, E, F, G]
    //           A(0)
    //        /       \
    //     B(1)        C(2)
    //    /   \       /   \
    //  D(3)  E(4)  F(5)  G(6)
    //quindi per nodo i: figlio let → idx 2*i+1, figlio right → idx 2*i+2, father → idx (i-1)/2 no float

    static void siftDown( int i, int[] array ){  
        //chiamato anche 'HEAPIFY', here is min-heap cioe ogni parent è minore(/uguale) dei suoi figli
        //se array[i] è più grande di uno dei suoi figli, lo scambiamo con il figlio più piccolo e ripetiamo ricorsivamente sul nuovo indice.
        int minIndex = i;  //iniziamo presupponendo che il nodo parent sia gia il min value, poi...
        int left = 2*i+1; //idx target figlio sx in un heap 0-based
        int right = 2*i+2; //idx target figlio dx in un heap 0-based
        if( left < array.length && array[left] < array[minIndex] )  //se slot in posizione left exists && value in slot left < value in slot actual minIndex
            minIndex = left;  //update minIndex
        if( right < array.length && array[right] < array[minIndex] )
            minIndex = right; //update minIndex
        if( minIndex != i ){  
            //se almeno 1 dei 2if precedenti si sono attivati,significa che un/entrambi figli sono smallest del parent -> violazione proprieta min-heap(dove il parent deve essere smallest), necessario swap/swaps
            //scambio realmente
            swaps.add( new Swap(i, minIndex) );  //add new Obj to dynamicarr swaps, xk solution vuole come return tutti gli swaps che hai fatto
            int temp = array[i];  //save actual arr[i] in temp var
            array[i] = array[minIndex];  //swap
            array[minIndex] = temp;  //swap
            //ora l'item che era in arr[i] è ora in arr[minIndex], pero ora nella nuova position potrebbe ancora violare la proprieta min-heap in base ai suoi figli, quindi...
            siftDown( minIndex, array );  //e.g.hai scoperto qua sopra che figlio right è >parent, quindi hai fatto swap, here minIndex è cnora cooordinate figlio right (anche se il suo valore è cambiato)
        }
    }
    static class Swap{  //class Obj
        int i, j;
        Swap( int i, int j ) { this.i = i; this.j = j; }
    }
}
