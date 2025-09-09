import java.io.*;
import java.util.*;

class Node {
    public static final int Letters = 4;
    public static final int NA = -1;
    public int[] next;
    public boolean isPatternEnd;

    Node() {
        next = new int[Letters];
        Arrays.fill(next, NA);
        isPatternEnd = false;
    }
}

public class TrieMatching implements Runnable {
    int letterToIndex(char letter) {
        switch (letter) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: throw new IllegalArgumentException("Invalid char: " + letter);
        }
    }

    // Costruisce il trie dai pattern
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
            trie.get(current).isPatternEnd = true;
        }
        return trie;
    }

    // Ricerca dei match
    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Integer> result = new ArrayList<>();
        List<Node> trie = buildTrie(patterns);

        for (int i = 0; i < text.length(); i++) {
            int current = 0;
            int j = i;
            while (j < text.length()) {
                int idx = letterToIndex(text.charAt(j));
                if (trie.get(current).next[idx] != Node.NA) {
                    current = trie.get(current).next[idx];
                    if (trie.get(current).isPatternEnd) {
                        result.add(i); // trovato pattern che parte da i
                        break; // non servono match più lunghi da questa posizione
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
        new Thread(new TrieMatching()).start();
    }
}
