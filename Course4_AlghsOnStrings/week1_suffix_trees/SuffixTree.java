import java.util.*;
import java.io.*;

public class SuffixTree {
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
    }

    // Nodo del Trie
    class Node {
        Map<Character, Node> children = new HashMap<>();
        int start;   // indice nella stringa
        int length;  // lunghezza dell’etichetta dell’arco che porta qui

        Node(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    String text;

    public List<String> computeSuffixTreeEdges(String text) {
        this.text = text;
        Node root = new Node(-1, 0);

        // Step 1: Costruzione del Trie con tutti i suffissi
        for (int i = 0; i < text.length(); i++) {
            Node cur = root;
            for (int j = i; j < text.length(); j++) {
                char c = text.charAt(j);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Node(j, text.length() - j));
                }
                cur = cur.children.get(c);
            }
        }

        // Step 2: Compressione dei cammini
        List<String> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(Node node, List<String> result) {
        for (Node child : node.children.values()) {
            // compressione: se un nodo ha un solo figlio, estendiamo
            int start = child.start;
            int length = 1;
            Node cur = child;

            while (cur.children.size() == 1) {
                Node next = cur.children.values().iterator().next();
                length += 1;
                cur = next;
            }

            result.add(text.substring(start, start + length));
            dfs(cur, result);
        }
    }

    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }
}
