import java.io.*;
import java.util.*;

public class StackWithMax {

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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>(); // stack per tenere i massimi

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
                if (maxStack.isEmpty() || value >= maxStack.peek()) {
                    maxStack.push(value);
                }
            } else if ("pop".equals(operation)) {
                if (!stack.isEmpty()) {
                    int removed = stack.pop();
                    if (removed == maxStack.peek()) {
                        maxStack.pop();
                    }
                }
            } else if ("max".equals(operation)) {
                if (!maxStack.isEmpty()) {
                    System.out.println(maxStack.peek());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
