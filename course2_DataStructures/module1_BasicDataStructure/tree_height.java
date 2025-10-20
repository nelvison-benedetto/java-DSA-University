import java.util.*;
import java.io.*;

public class tree_height {

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
    // public class TreeHeight {
    //     int n;
    //     int parent[];
    //     List<List<Integer>> children;
    //     void read() throws IOException {
    //         FastScanner in = new FastScanner();
    //         n = in.nextInt();
    //         parent = new int[n];
    //         children = new ArrayList<>();
    //         for (int i = 0; i < n; i++) children.add(new ArrayList<>());
    //         for (int i = 0; i < n; i++) {
    //             parent[i] = in.nextInt();
    //             if (parent[i] != -1) {
    //                 children.get(parent[i]).add(i); // costruisco la lista dei figli
    //             }
    //         }
    //     }
    //     int computeHeight() {
    //         int root = -1;
    //         for (int i = 0; i < n; i++) {
    //             if (parent[i] == -1) {
    //                 root = i;
    //                 break;
    //             }
    //         }
    //         return bfsHeight(root);
    //     }
    //     int bfsHeight(int root) {
    //         Queue<Integer> queue = new LinkedList<>();
    //         queue.add(root);
    //         int height = 0;
    //         while (!queue.isEmpty()) {
    //             int size = queue.size();
    //             for (int i = 0; i < size; i++) {
    //                 int node = queue.poll();
    //                 for (int child : children.get(node)) {
    //                     queue.add(child);
    //                 }
    //             }
    //             height++;
    //         }
    //         return height;
    //     }
    // }
    // static public void main(String[] args) throws IOException {
    //     new Thread(null, new Runnable() {
    //         public void run() {
    //             try {
    //                 new tree_height().run();
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }, "1", 1 << 26).start();
    // }
    // public void run() throws IOException {
    //     TreeHeight tree = new TreeHeight();
    //     tree.read();
    //     System.out.println(tree.computeHeight());
    // }

    //PEREFCT SOLUTION!!! O(n)
    static class Node {  //ogni node ha una lista di figli
        List<Integer> children = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = scanner.nextInt();  //legge i dati in input e li assegna dentro l'arr parent
        }
        scanner.close();
        System.out.println(computeHeight(n, parent));  //passi tot nodi + array parent che ha N boxes ognuno ripieno con input passato
    }
    private static int computeHeight(int n, int[] parent){
        // Crea la lista dei figli per ogni nodo
        List<List<Integer>> children = new ArrayList<>();  //macrodynamicarr 
        for (int i=0; i<n; i++) {
            children.add(new ArrayList<>());   //creiamo una dynamicarr per ogni idx i 0->n-1
        }
        int root = -1;  //root come start è sempre -1 per convetion
        for(int i=0; i<n; i++) {
            if(parent[i] == -1){  //se ricevi un -1 in input per convezione è la root
                root = i;  //setto puntatore root
            } else {
                children.get( parent[i] ).add(i); //get dynamicarr target che è dentro macrodynamicarr, e ci aggiungo dentro un item
            } //quindi primo slot di macrodynamicarr contiene dynamicarr con 1 item=0, il secondo slot di macrodynamicarr contiene dynamicarr con 1 item=1, ect
        }
        //🔥🔥BFS iterativa!! con DFS con n grande (>10^5) puoi avere error StackOverflowError
        Queue<int[]> queue = new ArrayDeque<>();  //coda, cioe 'serpente' rivolto verso il basso
        queue.add( new int[]{ root,1 } );  //starting  {nodo, altezza_corrente}, x questo avresto cnhe potuto usare custom class NodeState{ int node, depth;}, ma cosi con int[] è piu pratico e performante
        int maxHeight = 0;
        while( !queue.isEmpty() ){  //stop cycle quando queue is empty
            int[] current = queue.poll();  //poll() is dequeue, step1: tiriamo fuori l'item frontale(quello piu 'vecchio') 
            int node = current[0];  //remember current contiene 2 box: il primo x node, il secondo x altezza 
            int height = current[1];
            maxHeight = Math.max(maxHeight, height);  //step2: aggiornimo il maxHeight se è da aggiornare
            for( int child : children.get(node) ){  //children.get(node) (node si riferisce a idx del nodo nel tree) prende la lista dei figli del nodo “node”
                //mette in coda tutti i figli del nodo incrementando la profondità di 1
                queue.add( new int[]{ child, height+1 } );
            }
        }
        return maxHeight;
    }

}
