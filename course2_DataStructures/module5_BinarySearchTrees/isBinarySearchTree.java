import java.util.*;
import java.io.*;

public class isBinarySearchTree {

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
    //         if (nodes == 0) return true; // albero vuoto è un BST
    //         return isBSTUtil(0, Long.MIN_VALUE, Long.MAX_VALUE);
    //     }
    //     boolean isBSTUtil(int index, long min, long max) {
    //         if (index == -1) return true;
    //         Node node = tree[index];
    //         if (node.key < min || node.key > max) {
    //             return false;
    //         }
    //         // attenzione: nel BST standard, il sottoalbero sinistro < node.key
    //         // e il sottoalbero destro >= node.key
    //         return isBSTUtil(node.left, min, (long) node.key - 1) &&
    //                isBSTUtil(node.right, (long) node.key, max);
    //     }
    // }
    // static public void main(String[] args) throws IOException {
    //     new Thread(null, new Runnable() {
    //         public void run() {
    //             try {
    //                 new is_bst().run();
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
    //return res 'CORRECT' se il tree dato ripetta le proprieta del BT( per ogni parent left>parent && right<parent), altrimenti return res 'INCORRECT'
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );
        int n = Integer.parseInt( in.readLine() );
        if( n==0 ){ // Albero vuoto è sempre corretto
            System.out.println("CORRECT");
            return;
        }
        tree = new Node[n];
        for( int i=0; i<n; i++ ){
            String[] parts = in.readLine().split(" ");
            int key = Integer.parseInt( parts[0] );
            int left = Integer.parseInt( parts[1] );
            int right = Integer.parseInt( parts[2] );
            tree[i] = new Node( key, left, right );
        }
        if( isBST(0, Long.MIN_VALUE, Long.MAX_VALUE) )
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
    // Funzione ricorsiva per controllare se il sottoalbero è BST
    static boolean isBST( int nodeIndex, long min, long max ){
        if( nodeIndex==-1 )
            return true; // nodo vuoto: sempre valido
        Node node = tree[nodeIndex];
        // Condizione di validità per un BST
        if( node.key<=min || node.key>=max )
            return false;
        // Controlla ricorsivamente i figli
        return isBST( node.left, min, node.key )
            && isBST( node.right, node.key, max );
    }

}
