import java.util.*;
import java.io.*;

public class BinaryTree_print3Traversals {
    
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
    //Nodo 0: key=4, left=1, right=2
    //Nodo 1: key=2, left=3, right=4
    //Nodo 2: key=5, left=-1, right=-1
    //Nodo 3: key=1, left=-1, right=-1
    //Nodo 4: key=3, left=-1, right=-1
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
    // --- IN-ORDER ITERATIVO ---
    static List<Integer> inOrder( int root ){
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int current = root;
        while( current!=-1 || !stack.isEmpty() ){
            while( current!=-1 ){
                stack.push(current);
                current = tree[current].left;
            }
            current = stack.pop();
            res.add( tree[current].key );
            current = tree[current].right;
        }
        return res;
    }
    // --- PRE-ORDER ITERATIVO ---
    static List<Integer> preOrder( int root ){
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        while( !stack.isEmpty() ){
            int node = stack.pop();
            res.add(tree[node].key);
            if( tree[node].right!=-1 ) stack.push( tree[node].right );
            if( tree[node].left!=-1 ) stack.push( tree[node].left );
        }
        return res;
    }
    // --- POST-ORDER ITERATIVO ---
    static List<Integer> postOrder( int root ){
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        while( !stack1.isEmpty() ){
            int node = stack1.pop();
            stack2.push(node);
            if( tree[node].left!=-1 ) stack1.push( tree[node].left );
            if( tree[node].right!=-1 ) stack1.push( tree[node].right );
        }
        while( !stack2.isEmpty() ){
            res.add( tree[stack2.pop()].key );
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
