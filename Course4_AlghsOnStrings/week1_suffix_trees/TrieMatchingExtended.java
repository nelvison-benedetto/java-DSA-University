import java.io.*;
import java.util.*;

class Node {
    public static final int Letters = 4;
    public static final int NA = -1;
    public int[] next;
    public boolean patternEnd;

    Node() {
        next = new int[Letters];
        Arrays.fill(next, NA);
        patternEnd = false;
    }
}

public class TrieMatchingExtended implements Runnable {
    int letterToIndex(char letter) {
        switch (letter) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: throw new IllegalArgumentException("Invalid char: " + letter);
        }
    }

    List<Node> buildTrie(List<String> patterns) {
        List<Node> trie = new ArrayList<>();
        trie.add(new Node()); // root

        for (String pattern : patterns) {
            int current = 0;
            for (char c : pattern.toCharArray()) {
                int idx = letterToIndex(c);
                if (trie.get(current).next[idx] == Node.NA) {
                    trie.add(new Node());
                    trie.get(current).next[idx] = trie.size() - 1;
                }
                current = trie.get(current).next[idx];
            }
            trie.get(current).patternEnd = true;
        }

        return trie;
    }

    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Integer> result = new ArrayList<>();
        List<Node> trie = buildTrie(patterns);

        for (int i = 0; i < text.length(); i++) {
            int current = 0;
            int j = i;
            boolean found = false;

            while (j < text.length()) {
                int idx = letterToIndex(text.charAt(j));
                if (trie.get(current).next[idx] != Node.NA) {
                    current = trie.get(current).next[idx];
                    if (trie.get(current).patternEnd) {
                        result.add(i);
                        found = true;
                        break; // possiamo fermarci qui: basta registrare l'inizio una volta
                    }
                    j++;
                } else {
                    break;
                }
            }
        }

        return result;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text = in.readLine();
            int n = Integer.parseInt(in.readLine());
            List<String> patterns = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                patterns.add(in.readLine());
            }

            List<Integer> ans = solve(text, n, patterns);

            for (int j = 0; j < ans.size(); j++) {
                System.out.print(ans.get(j));
                if (j + 1 < ans.size()) System.out.print(" ");
            }
            System.out.println();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Thread(new TrieMatchingExtended()).start();
    }
}
