import java.util.*;
import java.io.*;

public class BinaryTree_3Traversals {
    
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
    // public class TreeOrders {
    //     int n;
    //     int[] key, left, right;
    //     void read() throws IOException {
    //         FastScanner in = new FastScanner();
    //         n = in.nextInt();
    //         key = new int[n];
    //         left = new int[n];
    //         right = new int[n];
    //         for (int i = 0; i < n; i++) {
    //             key[i] = in.nextInt();
    //             left[i] = in.nextInt();
    //             right[i] = in.nextInt();
    //         }
    //     }
    //     List<Integer> inOrder() {
    //         ArrayList<Integer> result = new ArrayList<>();
    //         inOrderTraversal(0, result);
    //         return result;
    //     }
    //     private void inOrderTraversal(int node, List<Integer> result) {
    //         if (node == -1) return;
    //         inOrderTraversal(left[node], result);
    //         result.add(key[node]);
    //         inOrderTraversal(right[node], result);
    //     }
    //     List<Integer> preOrder() {
    //         ArrayList<Integer> result = new ArrayList<>();
    //         preOrderTraversal(0, result);
    //         return result;
    //     }
    //     private void preOrderTraversal(int node, List<Integer> result) {
    //         if (node == -1) return;
    //         result.add(key[node]);
    //         preOrderTraversal(left[node], result);
    //         preOrderTraversal(right[node], result);
    //     }
    //     List<Integer> postOrder() {
    //         ArrayList<Integer> result = new ArrayList<>();
    //         postOrderTraversal(0, result);
    //         return result;
    //     }
    //     private void postOrderTraversal(int node, List<Integer> result) {
    //         if (node == -1) return;
    //         postOrderTraversal(left[node], result);
    //         postOrderTraversal(right[node], result);
    //         result.add(key[node]);
    //     }
    // }
    // static public void main(String[] args) throws IOException {
    //     new Thread(null, new Runnable() {
    //         public void run() {
    //             try {
    //                 new tree_orders().run();
    //             } catch (IOException e) {
    //             }
    //         }
    //     }, "1", 1 << 26).start();
    // }
    // public void print(List<Integer> x) {
    //     for (Integer a : x) {
    //         System.out.print(a + " ");
    //     }
    //     System.out.println();
    // }
    // public void run() throws IOException {
    //     TreeOrders tree = new TreeOrders();
    //     tree.read();
    //     print(tree.inOrder());
    //     print(tree.preOrder());
    //     print(tree.postOrder());
    // }

    //PERFECT SOLUTION!!! ogni traveral visita ogni nodo soloa 1 volta, quindi O(n)
    //ogni nodo ha: key, idx left(-1 se non eiste), idx right(-1 se non esiste). root ha idx 0.
    //e.g. input 
    //5
    //4 1 2
    //2 3 4
    //5 -1 -1
    //1 -1 -1
    //3 -1 -1
    //Node0:  key=4, left=node1, right=node2
    //Node1:  key=2, left=node3, right=node4
    //Node2:  key=5, left=-1, right=-1
    //Node3:  key=1, left=-1, right=-1
    //Node4:  key=3, left=-1, right=-1
    //      4
    //     / \
    //    2   5
    //  / \
    // 1   3
    //return print tree nei 3 metodi di attraversamento(traversal): in-order, pre-order, post-order
    //1 2 3 4 5  ,  4 2 1 3 5  ,  1 3 2 5 4
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(System.in) ); //lettore di testo per read input faster than classic Scanner
        int n = Integer.parseInt( in.readLine() );  //tot nodes
        tree = new Node[n];  //staticarr con n slots, ognuno puo puntare ad un obj Node
        // Lettura dell’albero
        for( int i=0; i<n; i++ ){  //read n rows in input, ciascuna con i dati x 1 node
            String[] parts = in.readLine().split(" ");
            int key = Integer.parseInt( parts[0] );
            int left = Integer.parseInt( parts[1] );
            int right = Integer.parseInt( parts[2] );
            tree[i] = new Node( key, left, right );  //create&save new node in slot arr
        }
        if( n==0 ){
            // stampa tre righe vuote (o come richiesto)
            System.out.println();
            System.out.println();
            System.out.println();
            return;
        }
        // Traversal
        List<Integer> inorder = inOrder(0);
        List<Integer> preorder = preOrder(0);
        List<Integer> postorder = postOrder(0);
        // Output
        printList(inorder);
        printList(preorder);
        printList(postorder);
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
    // --- IN-ORDER (read Left→Node→Right), ITERATIVO ---
    static List<Integer> inOrder( int root ){
        List<Integer> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();  //doublequeue we use only Last
        int current = root;
        while( current!=-1 || !stack.isEmpty() ){  //baste che anche solo 1 di queste 2 condition sia true, che il cycle start
            while( current!=-1 ){
                stack.addLast(current);
                current = tree[current].left;  //update reference
            }
            //stack ha immagazzinato tutti i nodes da cui siamo passati,
            //ora current points to nullnode left figlio di node real lowest-leftest in the tree.
            current = stack.removeLast();  //update current con l'ultimo item added in the stack: cioe node real piu in basso a sx nel tree
            res.add( tree[current].key );  //lo aggiungiamo alla risposta
            current = tree[current].right;  //ci spostiamo sul ramo dx e ripetiamo()
        }
        return res;
    }
    // --- PRE-ORDER (read Node→Left→Right), ITERATIVO ---
    static List<Integer> preOrder( int root ){
        List<Integer> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(root);
        while( !stack.isEmpty() ){
            int node = stack.removeLast();
            res.add( tree[node].key );  //add in res
            if( tree[node].right!=-1 ) stack.addLast( tree[node].right );  //add first right xk we are using stack LIFO, quindi left verra ESTRATTO prima di right!
            if( tree[node].left!=-1 ) stack.addLast( tree[node].left );
        }
        return res;
    }
    // --- POST-ORDER (Left→Right→Node), ITERATIVO ---
    static List<Integer> postOrder( int root ){
        List<Integer> res = new ArrayList<>();
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        stack1.addLast(root);
        while( !stack1.isEmpty() ){
            int node = stack1.removeLast();
            stack2.addLast(node);  //e.g.se il tree sono 3 nodes(parent,left,right): added here in order parent(1st cycle)->right(2nd cycly)->left(3rd cycle)
            if( tree[node].left!=-1 ) stack1.addLast( tree[node].left );  //add first right xk we are using stack LIFO, quindi left verra ESTRATTO prima di right!
            if( tree[node].right!=-1 ) stack1.addLast( tree[node].right );
        }
        while( !stack2.isEmpty() ){
            res.add( tree[stack2.removeLast()].key );  //estrae & aggiunge in res in order left->right->parent, ok!!!
        }
        return res;
    }
    static void printList( List<Integer> list ){
        for( int i=0; i<list.size(); i++ ){
            if( i>0 ) System.out.print(" ");
            System.out.print( list.get(i) );
        }
        System.out.println();
    }

}
