import java.io.*;
import java.util.*;

public class RopeProblem {

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Node {
        char c;
        Node left, right, parent;
        int size;

        Node(char c) {
            this.c = c;
            this.size = 1;
        }
    }

    class Rope {
        Node root;

        Rope(String s) {
            for (char ch : s.toCharArray()) {
                root = merge(root, new Node(ch));
            }
        }

        int size(Node n) {
            return n == null ? 0 : n.size;
        }

        void update(Node n) {
            if (n != null) {
                n.size = 1 + size(n.left) + size(n.right);
                if (n.left != null) n.left.parent = n;
                if (n.right != null) n.right.parent = n;
            }
        }

        void smallRotation(Node v) {
            Node parent = v.parent;
            if (parent == null) return;
            Node grandparent = parent.parent;
            if (parent.left == v) {
                parent.left = v.right;
                v.right = parent;
            } else {
                parent.right = v.left;
                v.left = parent;
            }
            update(parent);
            update(v);
            v.parent = grandparent;
            if (grandparent != null) {
                if (grandparent.left == parent) grandparent.left = v;
                else grandparent.right = v;
            }
        }

        void bigRotation(Node v) {
            if ((v.parent.left == v && v.parent.parent.left == v.parent) ||
                (v.parent.right == v && v.parent.parent.right == v.parent)) {
                smallRotation(v.parent);
                smallRotation(v);
            } else {
                smallRotation(v);
                smallRotation(v);
            }
        }

        Node splay(Node v) {
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

        Node find(Node root, int index) {
            Node v = root;
            while (v != null) {
                int leftSize = size(v.left);
                if (index == leftSize + 1) return splay(v);
                else if (index <= leftSize) v = v.left;
                else {
                    index = index - leftSize - 1;
                    v = v.right;
                }
            }
            return null;
        }

        Node[] split(Node root, int index) {
            if (root == null) return new Node[]{null, null};
            if (index >= size(root)) return new Node[]{root, null};
            if (index <= 0) return new Node[]{null, root};

            Node right = find(root, index + 1);
            Node left = right.left;
            if (left != null) left.parent = null;
            right.left = null;
            update(left);
            update(right);
            return new Node[]{left, right};
        }

        Node merge(Node left, Node right) {
            if (left == null) return right;
            if (right == null) return left;
            Node minRight = right;
            while (minRight.left != null) minRight = minRight.left;
            right = splay(minRight);
            right.left = left;
            update(right);
            return right;
        }

        void process(int i, int j, int k) {
            // Cut substring [i, j]
            Node[] leftMiddle = split(root, i);
            Node left = leftMiddle[0];
            Node middleRight = leftMiddle[1];

            Node[] middleRightSplit = split(middleRight, j - i + 1);
            Node middle = middleRightSplit[0];
            Node right = middleRightSplit[1];

            // Remove substring
            root = merge(left, right);

            // Insert at position k
            Node[] newSplit = split(root, k);
            root = merge(merge(newSplit[0], middle), newSplit[1]);
        }

        void inorder(Node n, StringBuilder sb) {
            if (n == null) return;
            inorder(n.left, sb);
            sb.append(n.c);
            inorder(n.right, sb);
        }

        String result() {
            StringBuilder sb = new StringBuilder();
            inorder(root, sb);
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        new RopeProblem().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        Rope rope = new Rope(in.next());
        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();
            rope.process(i, j, k);
        }
        out.println(rope.result());
        out.close();
    }
}
