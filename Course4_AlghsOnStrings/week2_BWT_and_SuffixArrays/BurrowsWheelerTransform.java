import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
        int n = text.length();
        List<String> rotations = new ArrayList<>();

        // genera tutte le rotazioni
        for (int i = 0; i < n; i++) {
            String rotation = text.substring(i) + text.substring(0, i);
            rotations.add(rotation);
        }

        // ordina le rotazioni lessicograficamente
        Collections.sort(rotations);

        // costruisci la colonna finale
        StringBuilder result = new StringBuilder();
        for (String r : rotations) {
            result.append(r.charAt(n - 1));
        }

        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
