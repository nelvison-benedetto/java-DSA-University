import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

    String inverseBWT(String bwt) {
        int n = bwt.length();

        // Step 1: Conta occorrenze in BWT
        Map<Character, Integer> count = new HashMap<>();
        int[] occRank = new int[n]; // rank della k-esima occorrenza
        for (int i = 0; i < n; i++) {
            char c = bwt.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
            occRank[i] = count.get(c);
        }

        // Step 2: Costruisci la prima colonna F
        List<Character> chars = new ArrayList<>(count.keySet());
        Collections.sort(chars); // ordine alfabetico: $, A, C, G, T

        Map<Character, Integer> firstColStart = new HashMap<>();
        int pos = 0;
        for (char c : chars) {
            firstColStart.put(c, pos);
            pos += count.get(c);
        }

        // Step 3: Ricostruisci usando last-to-first mapping
        StringBuilder result = new StringBuilder();
        int row = 0; // riga con il simbolo '$'
        for (int i = 0; i < n; i++) {
            char c = bwt.charAt(row);
            result.append(c);

            // trova la prossima riga in F
            row = firstColStart.get(c) + occRank[row] - 1;
        }

        // risultato al contrario
        return result.reverse().toString();
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
