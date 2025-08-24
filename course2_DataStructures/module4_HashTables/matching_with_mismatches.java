import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class matching_with_mismatches {

    public List<Integer> solve(int k, String text, String pattern) {
        ArrayList<Integer> pos = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i + m <= n; i++) {
            int mismatches = 0;
            for (int j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    mismatches++;
                    if (mismatches > k) break; // pruning
                }
            }
            if (mismatches <= k) {
                pos.add(i);
            }
        }
        return pos;
    }

    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        in.lines().forEach(line -> {
            StringTokenizer tok = new StringTokenizer(line);
            int k = Integer.valueOf(tok.nextToken());
            String text = tok.nextToken();
            String pattern = tok.nextToken();
            List<Integer> ans = solve(k, text, pattern);
            out.format("%d ", ans.size());
            out.println(ans.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(" "))
            );
        });
        out.close();
    }

    static public void main(String[] args) {
        new matching_with_mismatches().run();
    }
}
