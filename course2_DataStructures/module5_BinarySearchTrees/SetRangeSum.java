import java.io.*;
import java.util.*;

public class SetRangeSum {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Nodo del Splay Tree
    class Vertex {
        int key;
        long sum; // somma di tutto il sottoalbero
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) v.left.parent = v;
        if (v.right != null) v.right.parent = v;
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) return;
        Vertex grandparent = parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            smallRotation(v);
            smallRotation(v);
        }
    }

    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;
        VertexPair() {}
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) break;
            if (v.key < key) v = v.right;
            else v = v.left;
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        root = findAndRoot.right;
        Vertex right = findAndRoot.left;
        if (right == null) {
            result.left = root;
            result.right = null;
            return result;
        }
        right = splay(right);
        result.right = right;
        result.left = right.left;
        right.left = null;
        if (result.left != null) result.left.parent = null;
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // ---- RISOLUZIONE ----
    Vertex root = null;

    void insert(int x) {
        VertexPair leftRight = split(root, x);
        Vertex left = leftRight.left;
        Vertex right = leftRight.right;
        if (right == null || right.key != x) {
            Vertex new_vertex = new Vertex(x, x, null, null, null);
            root = merge(merge(left, new_vertex), right);
        } else {
            root = merge(left, right);
        }
    }

    void erase(int x) {
        VertexPair findAndRoot = find(root, x);
        root = findAndRoot.right;
        Vertex found = findAndRoot.left;
        if (found == null || found.key != x) return; // non trovato
        root = splay(found);
        if (root.left == null) {
            root = root.right;
            if (root != null) root.parent = null;
        } else {
            Vertex leftSubtree = root.left;
            leftSubtree.parent = null;
            Vertex maxLeft = leftSubtree;
            while (maxLeft.right != null) maxLeft = maxLeft.right;
            leftSubtree = splay(maxLeft);
            leftSubtree.right = root.right;
            if (root.right != null) root.right.parent = leftSubtree;
            update(leftSubtree);
            root = leftSubtree;
        }
    }

    boolean find(int x) {
        VertexPair result = find(root, x);
        root = result.right;
        return (result.left != null && result.left.key == x);
    }

    long sum(int from, int to) {
        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;
        long ans = (middle != null ? middle.sum : 0);
        root = merge(merge(left, middle), right);
        return ans;
    }

    // ---- I/O ----
    public static final int MODULO = 1000000001;

    void solve() throws IOException {
        int n = nextInt();
        int last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            switch (type) {
                case '+' : {
                    int x = nextInt();
                    insert((x + last_sum_result) % MODULO);
                } break;
                case '-' : {
                    int x = nextInt();
                    erase((x + last_sum_result) % MODULO);
                } break;
                case '?' : {
                    int x = nextInt();
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                } break;
                case 's' : {
                    int l = nextInt();
                    int r = nextInt();
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(res);
                    last_sum_result = (int)(res % MODULO);
                }
            }
        }
    }

    SetRangeSum() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SetRangeSum();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }
}
