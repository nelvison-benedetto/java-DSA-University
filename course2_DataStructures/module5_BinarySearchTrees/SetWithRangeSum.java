import java.io.*;
import java.util.*;

public class SetWithRangeSum {

    // BufferedReader br;
    // PrintWriter out;
    // StringTokenizer st;
    // boolean eof;
    // // Nodo del Splay Tree
    // class Vertex {
    //     int key;
    //     long sum; // somma di tutto il sottoalbero
    //     Vertex left;
    //     Vertex right;
    //     Vertex parent;
    //     Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
    //         this.key = key;
    //         this.sum = sum;
    //         this.left = left;
    //         this.right = right;
    //         this.parent = parent;
    //     }
    // }
    // void update(Vertex v) {
    //     if (v == null) return;
    //     v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
    //     if (v.left != null) v.left.parent = v;
    //     if (v.right != null) v.right.parent = v;
    // }
    // void smallRotation(Vertex v) {
    //     Vertex parent = v.parent;
    //     if (parent == null) return;
    //     Vertex grandparent = parent.parent;
    //     if (parent.left == v) {
    //         Vertex m = v.right;
    //         v.right = parent;
    //         parent.left = m;
    //     } else {
    //         Vertex m = v.left;
    //         v.left = parent;
    //         parent.right = m;
    //     }
    //     update(parent);
    //     update(v);
    //     v.parent = grandparent;
    //     if (grandparent != null) {
    //         if (grandparent.left == parent) {
    //             grandparent.left = v;
    //         } else {
    //             grandparent.right = v;
    //         }
    //     }
    // }
    // void bigRotation(Vertex v) {
    //     if (v.parent.left == v && v.parent.parent.left == v.parent) {
    //         smallRotation(v.parent);
    //         smallRotation(v);
    //     } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
    //         smallRotation(v.parent);
    //         smallRotation(v);
    //     } else {
    //         smallRotation(v);
    //         smallRotation(v);
    //     }
    // }
    // Vertex splay(Vertex v) {
    //     if (v == null) return null;
    //     while (v.parent != null) {
    //         if (v.parent.parent == null) {
    //             smallRotation(v);
    //             break;
    //         }
    //         bigRotation(v);
    //     }
    //     return v;
    // }
    // class VertexPair {
    //     Vertex left;
    //     Vertex right;
    //     VertexPair() {}
    //     VertexPair(Vertex left, Vertex right) {
    //         this.left = left;
    //         this.right = right;
    //     }
    // }
    // VertexPair find(Vertex root, int key) {
    //     Vertex v = root;
    //     Vertex last = root;
    //     Vertex next = null;
    //     while (v != null) {
    //         if (v.key >= key && (next == null || v.key < next.key)) {
    //             next = v;
    //         }
    //         last = v;
    //         if (v.key == key) break;
    //         if (v.key < key) v = v.right;
    //         else v = v.left;
    //     }
    //     root = splay(last);
    //     return new VertexPair(next, root);
    // }
    // VertexPair split(Vertex root, int key) {
    //     VertexPair result = new VertexPair();
    //     VertexPair findAndRoot = find(root, key);
    //     root = findAndRoot.right;
    //     Vertex right = findAndRoot.left;
    //     if (right == null) {
    //         result.left = root;
    //         result.right = null;
    //         return result;
    //     }
    //     right = splay(right);
    //     result.right = right;
    //     result.left = right.left;
    //     right.left = null;
    //     if (result.left != null) result.left.parent = null;
    //     update(result.left);
    //     update(result.right);
    //     return result;
    // }
    // Vertex merge(Vertex left, Vertex right) {
    //     if (left == null) return right;
    //     if (right == null) return left;
    //     while (right.left != null) {
    //         right = right.left;
    //     }
    //     right = splay(right);
    //     right.left = left;
    //     update(right);
    //     return right;
    // }
    // // ---- RISOLUZIONE ----
    // Vertex root = null;
    // void insert(int x) {
    //     VertexPair leftRight = split(root, x);
    //     Vertex left = leftRight.left;
    //     Vertex right = leftRight.right;
    //     if (right == null || right.key != x) {
    //         Vertex new_vertex = new Vertex(x, x, null, null, null);
    //         root = merge(merge(left, new_vertex), right);
    //     } else {
    //         root = merge(left, right);
    //     }
    // }
    // void erase(int x) {
    //     VertexPair findAndRoot = find(root, x);
    //     root = findAndRoot.right;
    //     Vertex found = findAndRoot.left;
    //     if (found == null || found.key != x) return; // non trovato
    //     root = splay(found);
    //     if (root.left == null) {
    //         root = root.right;
    //         if (root != null) root.parent = null;
    //     } else {
    //         Vertex leftSubtree = root.left;
    //         leftSubtree.parent = null;
    //         Vertex maxLeft = leftSubtree;
    //         while (maxLeft.right != null) maxLeft = maxLeft.right;
    //         leftSubtree = splay(maxLeft);
    //         leftSubtree.right = root.right;
    //         if (root.right != null) root.right.parent = leftSubtree;
    //         update(leftSubtree);
    //         root = leftSubtree;
    //     }
    // }
    // boolean find(int x) {
    //     VertexPair result = find(root, x);
    //     root = result.right;
    //     return (result.left != null && result.left.key == x);
    // }
    // long sum(int from, int to) {
    //     VertexPair leftMiddle = split(root, from);
    //     Vertex left = leftMiddle.left;
    //     Vertex middle = leftMiddle.right;
    //     VertexPair middleRight = split(middle, to + 1);
    //     middle = middleRight.left;
    //     Vertex right = middleRight.right;
    //     long ans = (middle != null ? middle.sum : 0);
    //     root = merge(merge(left, middle), right);
    //     return ans;
    // }
    // // ---- I/O ----
    // public static final int MODULO = 1000000001;
    // void solve() throws IOException {
    //     int n = nextInt();
    //     int last_sum_result = 0;
    //     for (int i = 0; i < n; i++) {
    //         char type = nextChar();
    //         switch (type) {
    //             case '+' : {
    //                 int x = nextInt();
    //                 insert((x + last_sum_result) % MODULO);
    //             } break;
    //             case '-' : {
    //                 int x = nextInt();
    //                 erase((x + last_sum_result) % MODULO);
    //             } break;
    //             case '?' : {
    //                 int x = nextInt();
    //                 out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
    //             } break;
    //             case 's' : {
    //                 int l = nextInt();
    //                 int r = nextInt();
    //                 long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
    //                 out.println(res);
    //                 last_sum_result = (int)(res % MODULO);
    //             }
    //         }
    //     }
    // }
    // SetRangeSum() throws IOException {
    //     br = new BufferedReader(new InputStreamReader(System.in));
    //     out = new PrintWriter(System.out);
    //     solve();
    //     out.close();
    // }
    // public static void main(String[] args) throws IOException {
    //     new SetRangeSum();
    // }
    // String nextToken() {
    //     while (st == null || !st.hasMoreTokens()) {
    //         try {
    //             st = new StringTokenizer(br.readLine());
    //         } catch (Exception e) {
    //             eof = true;
    //             return null;
    //         }
    //     }
    //     return st.nextToken();
    // }
    // int nextInt() throws IOException {
    //     return Integer.parseInt(nextToken());
    // }
    // char nextChar() throws IOException {
    //     return nextToken().charAt(0);
    // }

    //PERFECT SOLUTION!!! O(n)
    //Splay Tree (ipotizzato best tree in assoluto): dopo ogni operazione (search/insert/delete/split/merge) di nodo x, fai rotazioni per spostarlo in cima al tree. //processo 'splaying', cosi la prossima volta lo trovi piu velocemente (piu vicino alla root è il target desiderato meno complexity!!)
    //lo splaying(portare X alla root) di solito lo fai alla fine della funct.
    //è cache-friendly e user-oriented quindi no bisogno bilanciamento.
    static long lastSum = 0;  //risultato dell'ultima op rangeSum()
    static final long M = 1_000_000_001L;  //modulo 
    static Vertex root = null;  //root dello Splay Tree (inizialmente null)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // tot ops
        for( int i=0; i<n; i++ ){
            String op = sc.next(); //op: ?/+/-/s
            if( op.equals("?") ){
                long iVal = ( sc.nextLong() + lastSum ) % M;
                System.out.println( find(iVal) ? "Found" : "Not found" );
            } else if ( op.equals("+") ){
                long iVal = (sc.nextLong() + lastSum) % M;
                add(iVal);
            } else if ( op.equals("-") ){
                long iVal = ( sc.nextLong() + lastSum ) % M;
                del(iVal);
            } else if ( op.equals("s") ){
                long l = ( sc.nextLong() + lastSum ) % M;
                long r = ( sc.nextLong() + lastSum ) % M;
                long s = rangeSum(l, r);  //run funct rangeSum() w the 2 inputs
                System.out.println(s);
                lastSum = s;
            }
        }
    }
    static class Vertex {  //class Obj, node dello splay tree contains: key, sum(sum all keys in the subtree, always updated), left right parent are the pointers
        long key, sum;
        Vertex left, right, parent;
        Vertex( long key ){
            this.key = key;
            this.sum = key;
        }
    }
    static long sum( Vertex v ) { return v == null ? 0 : v.sum; }  //return la somma del subtree (return 0 if null).
    static void update( Vertex v ){  //ricalcola v.sum e aggiorna i puntatori parent dei figli (importante dopo rotazioni / merge / split).
        if( v==null ) return;
        v.sum = v.key + sum(v.left) + sum(v.right);
        if( v.left != null ) v.left.parent = v;
        if ( v.right != null ) v.right.parent = v;
    }
    // rotate, splay, split, merge (classiche funzioni di Splay Tree) ...
    // (omesse qui per brevità, ma posso scrivertele complete se vuoi)

    // ---- Operazioni principali ----
    static void add(long key) {  //TODO
        // evita duplicati, poi inserisci come nel BST e splay
    }

    static void del(long key) {
        // trova, splay, poi rimuovi se esiste
    }

    static boolean find(long key) {
        // splay e ritorna true/false
        return false;
    }

    static long rangeSum(long l, long r) {
        // split, somma, merge
        return 0;
    }

}
