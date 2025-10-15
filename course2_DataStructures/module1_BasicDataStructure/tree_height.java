import java.util.*;
import java.io.*;

public class tree_height {

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
    public class TreeHeight {
        int n;
        int parent[];
        List<List<Integer>> children;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            children = new ArrayList<>();
            for (int i = 0; i < n; i++) children.add(new ArrayList<>());
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
                if (parent[i] != -1) {
                    children.get(parent[i]).add(i); // costruisco la lista dei figli
                }
            }
        }
        int computeHeight() {
            int root = -1;
            for (int i = 0; i < n; i++) {
                if (parent[i] == -1) {
                    root = i;
                    break;
                }
            }
            return bfsHeight(root);
        }
        int bfsHeight(int root) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(root);
            int height = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int node = queue.poll();
                    for (int child : children.get(node)) {
                        queue.add(child);
                    }
                }
                height++;
            }
            return height;
        }
    }
    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
