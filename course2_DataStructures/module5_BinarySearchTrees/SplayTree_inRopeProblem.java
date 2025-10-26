import java.io.*;
import java.util.*;

public class SplayTree_inRopeProblem {

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
    // class Node {
    //     char c;
    //     Node left, right, parent;
    //     int size;
    //     Node(char c) {
    //         this.c = c;
    //         this.size = 1;
    //     }
    // }
    // class Rope {
    //     Node root;
    //     Rope(String s) {
    //         for (char ch : s.toCharArray()) {
    //             root = merge(root, new Node(ch));
    //         }
    //     }
    //     int size(Node n) {
    //         return n == null ? 0 : n.size;
    //     }
    //     void update(Node n) {
    //         if (n != null) {
    //             n.size = 1 + size(n.left) + size(n.right);
    //             if (n.left != null) n.left.parent = n;
    //             if (n.right != null) n.right.parent = n;
    //         }
    //     }
    //     void smallRotation(Node v) {
    //         Node parent = v.parent;
    //         if (parent == null) return;
    //         Node grandparent = parent.parent;
    //         if (parent.left == v) {
    //             parent.left = v.right;
    //             v.right = parent;
    //         } else {
    //             parent.right = v.left;
    //             v.left = parent;
    //         }
    //         update(parent);
    //         update(v);
    //         v.parent = grandparent;
    //         if (grandparent != null) {
    //             if (grandparent.left == parent) grandparent.left = v;
    //             else grandparent.right = v;
    //         }
    //     }
    //     void bigRotation(Node v) {
    //         if ((v.parent.left == v && v.parent.parent.left == v.parent) ||
    //             (v.parent.right == v && v.parent.parent.right == v.parent)) {
    //             smallRotation(v.parent);
    //             smallRotation(v);
    //         } else {
    //             smallRotation(v);
    //             smallRotation(v);
    //         }
    //     }
    //     Node splay(Node v) {
    //         if (v == null) return null;
    //         while (v.parent != null) {
    //             if (v.parent.parent == null) {
    //                 smallRotation(v);
    //                 break;
    //             }
    //             bigRotation(v);
    //         }
    //         return v;
    //     }
    //     Node find(Node root, int index) {
    //         Node v = root;
    //         while (v != null) {
    //             int leftSize = size(v.left);
    //             if (index == leftSize + 1) return splay(v);
    //             else if (index <= leftSize) v = v.left;
    //             else {
    //                 index = index - leftSize - 1;
    //                 v = v.right;
    //             }
    //         }
    //         return null;
    //     }
    //     Node[] split(Node root, int index) {
    //         if (root == null) return new Node[]{null, null};
    //         if (index >= size(root)) return new Node[]{root, null};
    //         if (index <= 0) return new Node[]{null, root};
    //         Node right = find(root, index + 1);
    //         Node left = right.left;
    //         if (left != null) left.parent = null;
    //         right.left = null;
    //         update(left);
    //         update(right);
    //         return new Node[]{left, right};
    //     }
    //     Node merge(Node left, Node right) {
    //         if (left == null) return right;
    //         if (right == null) return left;
    //         Node minRight = right;
    //         while (minRight.left != null) minRight = minRight.left;
    //         right = splay(minRight);
    //         right.left = left;
    //         update(right);
    //         return right;
    //     }
    //     void process(int i, int j, int k) {
    //         // Cut substring [i, j]
    //         Node[] leftMiddle = split(root, i);
    //         Node left = leftMiddle[0];
    //         Node middleRight = leftMiddle[1];
    //         Node[] middleRightSplit = split(middleRight, j - i + 1);
    //         Node middle = middleRightSplit[0];
    //         Node right = middleRightSplit[1];
    //         // Remove substring
    //         root = merge(left, right);
    //         // Insert at position k
    //         Node[] newSplit = split(root, k);
    //         root = merge(merge(newSplit[0], middle), newSplit[1]);
    //     }
    //     void inorder(Node n, StringBuilder sb) {
    //         if (n == null) return;
    //         inorder(n.left, sb);
    //         sb.append(n.c);
    //         inorder(n.right, sb);
    //     }
    //     String result() {
    //         StringBuilder sb = new StringBuilder();
    //         inorder(root, sb);
    //         return sb.toString();
    //     }
    // }
    // public static void main(String[] args) throws IOException {
    //     new RopeProblem().run();
    // }
    // public void run() throws IOException {
    //     FastScanner in = new FastScanner();
    //     PrintWriter out = new PrintWriter(System.out);
    //     Rope rope = new Rope(in.next());
    //     for (int q = in.nextInt(); q > 0; q--) {
    //         int i = in.nextInt();
    //         int j = in.nextInt();
    //         int k = in.nextInt();
    //         rope.process(i, j, k);
    //     }
    //     out.println(rope.result());
    //     out.close();
    // }

    //PERFECT SOLUTION!!!
    //Rope is data structure x per strs, che permette di fare cut concatenate split in O(log n) tempo!
      //implementabile w SplayTree or Treap, dove ogni node contiene char/smallsubstr and dimension subtree (numero di caratteri totali)
    //
    static Vertex root = null;
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner( System.in );
        String s = in.next();  //str rope iniziale
        int q = in.nextInt();  //tot query da processares
        // Crea albero iniziale (uno per carattere)
        for( char c : s.toCharArray() ){
            root = merge( root, new Vertex(c) ); //build the tree(ogni node 1 char)
        }
        for( int t=0; t<q; t++ ){  //processa all queries
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();
            process(i, j, k);
        }
        //print final res
        StringBuilder sb = new StringBuilder();
        print( root, sb );
        System.out.println(sb.toString());
    }
    static class FastScanner {  //xk BufferedReader+StringTokenizer è molto più veloce di Scanner per input grandi, ed eviti Time Limit Exceeded
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream stream) { br = new BufferedReader(new InputStreamReader(stream)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
    static class Vertex {  //class Obj, node dello splay tree contains, left right parent are the pointers
        char ch;             //char saved in this node
        int size;            //tot n chars in the subtree of this node
        Vertex left, right, parent;  //pointers in the 3 direction linked to this node
        Vertex( char ch ){
            this.ch = ch;
            this.size = 1;
        }
    }
    static class VertexPair {  //support x split, semplice struct per ritornare due alberi dalla funzione split (left e right)
        Vertex left, right;
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }


    static Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        Vertex minRight = right;
        while (minRight.left != null) //spostati sempre se segmento a sx (partendo da segmento estremo dx) finche arrivi all'ultimo segemento estremo a sx
            minRight = minRight.left;
        right = splay(minRight);
        right.left = left;
        update(right);
        return right;
    }
    static Vertex splay(Vertex v) {  //🔥 porta node v in posizione root, usando sequenze smallRot bigRot
        if( v == null ) return null;  //node v è  gia root
        while( v.parent != null ){  
            if( v.parent.parent == null ){  //se il parent.parent è null, allora caso zig fai solo 1 smallRot
                smallRotation(v);
                break;
            }
            bigRotation(v);  //fai bigRot
        }
        return v;
    }

    static void smallRotation( Vertex v ){  //goog good good soluzione comppatta ed efficente!
        //🔥🔥sposta il nodo v un livello più SU rispetto al suo parent. Se v era figlio sinistro fa una rotazione a destra, Se v era figlio destro fa una rotazione a sinistra.
        Vertex parent = v.parent;  //get node parent
        if( parent == null ) return;  //se parent==nul non puoi ruotare
        Vertex grandparent = parent.parent;  //save reference grandparent
        if( parent.left == v ){  //se v è figlio sx, allora ROTAZIONE DX
            parent.left = v.right;  //🔥 sposti il sottoalbero destro di v a sinistra del parent
            v.right = parent;  //🔥 il figlio dx di v diventa parent
        } else {  //se v è figlio dx, allora ROTAZIONE SX
            parent.right = v.left; 
            v.left = parent; 
        }
        update( parent );  //aggiorna le informazioni (size e parent) del nodo coinvolto dopo la rotaziones
        update(v);  //aggiorna le informazioni (size e parent) del nodo coinvolto dopo la rotazione
        v.parent = grandparent;  //link v to grandparent side v
        if( grandparent != null ){
            if( grandparent.left == parent )
                grandparent.left = v;  //link v to grandparent side grandparent
            else
                grandparent.right = v; //link v to grandparent side grandparent
        }
    }
    static void bigRotation(Vertex v) {   //goog good good soluzione comppatta ed efficente!
        if( v.parent.left == v && v.parent.parent.left == v.parent ){  //apply caso left-left (zig-zig). il genitore di v è figlio sinistro del suo genitore (nonno) e v è figlio sinistro del genitore
            //v è figlio sinistro, e il parent è anch’esso figlio sinistro
            //zIg-zIg (x e parent sono entrambi a sx, quindi le due rotazioni saranno entrambe a destra)
            smallRotation(v.parent);
            smallRotation(v);
        } else if( v.parent.right == v && v.parent.parent.right == v.parent ){  //apply caso right-right (zig-zig)
            //v è figlio destro, e il parent è anch’esso figlio destro
            //zIg-zIg (x e parent sono entrambi a dx, quindi le due rotazioni saranno entrambe a sinistra)
            smallRotation(v.parent);
            smallRotation(v);
        } else {  //apply caso zig-zag
            //zIg-zAg (x e prent non sono entrambi a sx/dx, quindi fai 2rotation in direzione opposta)
            //here la prima rotazione ruota v verso il genitore, la seconda completa.
            smallRotation(v);
            smallRotation(v);
        }
    }

    static void update(Vertex v) {
        if( v == null ) return;
        v.size = 1 + getSize(v.left) + getSize(v.right);
        if( v.left != null ) v.left.parent = v;
        if( v.right != null ) v.right.parent = v;
    }
    static int getSize(Vertex v) {
        return v == null ? 0 : v.size;
    }
    static Vertex find(Vertex v, int index) {  //trova node(quindi anche il suo char) in index-esimo carattere
        //find è scritto con indici 1-baseds
        if( v == null ) return null;
        int leftSize = getSize( v.left );
        if( index == leftSize + 1 ) //se il nodo corrente v contiene il index-esimo carattere: allora Splay(v) e lo restituisce.
            return splay(v);
        else if( index < leftSize + 1 )  //cerca ricorsivamente a sinistra
            return find( v.left, index );
        else  //scende a destra sottraendo leftSize+1
            return find( v.right, index - leftSize - 1 );
    }
    static void process( int i, int j, int k ){  //process(cut and paste)
        //rimuovere la sottostringa che va da i a j (inclusi) dalla stringa e reinserirla prima della posizione k (o nella posizione definita da k in base alla convenzione)
        //TODO 
        // Step 1: cut [i..j]
        VertexPair leftMiddle = split( root, i );
        Vertex left = leftMiddle.left;
        Vertex middleRight = leftMiddle.right;
        VertexPair middlePair = split( middleRight, j - i + 1 );
        Vertex middle = middlePair.left;
        Vertex right = middlePair.right;
        root = merge( left, right ); // remove [i..j]
        // Step 2: insert middle after k-th char
        VertexPair insertPair = split( root, k );
        root = merge( merge( insertPair.left, middle ), insertPair.right );
    }
    static VertexPair split( Vertex root, int index ){
        if( root == null ) return new VertexPair(null, null);  //se tree è vuoto return 2 alberi vuoti
        if( index == 0 ) return new VertexPair(null, root);  //se index == 0, left è vuoto (primi 0 caratteri), right è tutto
        if( index >= getSize(root) ) return new VertexPair(root, null);  //se index >= dimensione, left è tutto, right è vuoto
        Vertex right = find(root, index + 1);  
          //chiamo find con index+1 (1-based). Questo porta in radice il nodo che contiene il carattere in posizione index+1.
          //Dopo lo splay, quel nodo sarà radice ed avrà un sottoalbero sinistro che contiene esattamente i primi index caratteri!!
        Vertex left = right.left;  //salva il sottoalbero sinistro (i primi index caratteri)
        right.left = null;  //salva il sottoalbero sinistro (i primi index caratteri)
        if( left != null ) left.parent = null;  //rimuove il puntatore al genitore del sottoalbero sinistro
        update(left);  //aggiorna le informazioni (size e parent) del nodo coinvolto
        update(right);  //aggiorna le informazioni (size e parent) del nodo coinvolto
        return new VertexPair( left, right );  //ritorna la coppia: left contiene i primi index caratteri, right il resto.
        //split(root, index) restituisce due alberi: il primo con esattamente i primi index caratteri (quindi index qui è il numero di caratteri da mettere a sinistra)
        //split index è 0-based come conteggio di elementi, non come posizione 1-based — è il numero di elementi nella parte sinistra
    }

    static void print( Vertex v, StringBuilder sb ){  //in-order traversal print, get res str
        if( v == null ) return;
        print(v.left, sb);
        sb.append(v.ch);
        print( v.right, sb );
    }

}
