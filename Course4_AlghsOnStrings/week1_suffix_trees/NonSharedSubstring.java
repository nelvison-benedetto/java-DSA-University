import java.io.*;
import java.util.*;

public class NonSharedSubstring implements Runnable {

    String solve(String p, String q) {
        int n = p.length();

        // proviamo tutte le lunghezze dalla più piccola alla più grande
        for (int len = 1; len <= n; len++) {
            // usiamo un set per evitare duplicati
            for (int i = 0; i + len <= n; i++) {
                String sub = p.substring(i, i + len);
                if (!q.contains(sub)) {
                    return sub; // la prima sottostringa che manca in q
                }
            }
        }
        return ""; // in teoria non dovrebbe mai accadere
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String p = in.readLine().trim();
            String q = in.readLine().trim();

            String ans = solve(p, q);
            System.out.println(ans);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Thread(new NonSharedSubstring()).start();
    }
}
