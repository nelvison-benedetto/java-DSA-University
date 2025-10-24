import java.util.*;
import java.io.*;

public class isBST_2 {

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
    // public class IsBST {
    //     class Node {
    //         int key;
    //         int left;
    //         int right;
    //         Node(int key, int left, int right) {
    //             this.left = left;
    //             this.right = right;
    //             this.key = key;
    //         }
    //     }
    //     int nodes;
    //     Node[] tree;
    //     void read() throws IOException {
    //         FastScanner in = new FastScanner();
    //         nodes = in.nextInt();
    //         tree = new Node[nodes];
    //         for (int i = 0; i < nodes; i++) {
    //             tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
    //         }
    //     }
    //     boolean isBinarySearchTree() {
    //         if (nodes == 0) return true; // albero vuoto è BST
    //         return isBSTUtil(0, Long.MIN_VALUE, Long.MAX_VALUE);
    //     }
    //     boolean isBSTUtil(int index, long min, long max) {
    //         if (index == -1) return true;
    //         Node node = tree[index];
    //         if (node.key <= min || node.key >= max) {
    //             return false;
    //         }
    //         return isBSTUtil(node.left, min, node.key) &&
    //                isBSTUtil(node.right, node.key, max);
    //     }
    // }
    // static public void main(String[] args) throws IOException {
    //     new Thread(null, new Runnable() {
    //         public void run() {
    //             try {
    //                 new is_bst_hard().run();
    //             } catch (IOException e) {
    //             }
    //         }
    //     }, "1", 1 << 26).start();
    // }
    // public void run() throws IOException {
    //     IsBST tree = new IsBST();
    //     tree.read();
    //     if (tree.isBinarySearchTree()) {
    //         System.out.println("CORRECT");
    //     } else {
    //         System.out.println("INCORRECT");
    //     }
    // }

    //PERFECT SOLUTION!!!
    //in questa versione di isBST, tutte le chiavi nel sottoalbero destro devono essere maggiori o uguali a x, quindi possibili duplicati.

    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );  //lettore di testo per read input faster than classic Scanner
        int n = Integer.parseInt( in.readLine() );  //tot nodes
        if( n==0 ){  //tree vuoto è sempre corretto
            System.out.println("CORRECT");
            return;
        }
        tree = new Node[n];   //staticarr con n slots, ognuno puo puntare ad un obj Node
        for( int i=0; i<n; i++ ){   //read n rows in input, ciascuna con i dati x 1 node
            String[] parts = in.readLine().split(" ");
            int key = Integer.parseInt(parts[0]);
            int left = Integer.parseInt(parts[1]);
            int right = Integer.parseInt(parts[2]);
            tree[i] = new Node(key, left, right);   //create&save new node in slot staticarrs
        }
        if( isBST(0, Long.MIN_VALUE, Long.MAX_VALUE) )   //for starting use root idx, enormous numb min, enormous numb max
            System.out.println("CORRECT");
        else
            System.out.println("INCORRECT");
    }
        static class Node {  //class Obj
        int key;
        int left;
        int right;
        Node( int key, int left, int right ){
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
    static boolean isBST( int index, long min, long max ){
        if( index==-1 ) return true;  //nodo vuoto: sempre valido
        Node node = tree[index];
        if( node.key<min || node.key>=max ) return false;  //la chiave deve rispettare i limiti di isBST version2
        return isBST( node.left, min, node.key )
            && isBST( node.right, node.key, max );
    }

}
