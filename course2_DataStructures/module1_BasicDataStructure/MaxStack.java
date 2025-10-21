import java.io.*;
import java.util.*;

public class MaxStack {

    // class FastScanner {
    //     StringTokenizer tok = new StringTokenizer("");
    //     BufferedReader in;
    //     FastScanner() {
    //         in = new BufferedReader(new InputStreamReader(System.in));
    //     }
    //     String next() throws IOException {
    //         while (!tok.hasMoreElements())
    //             tok = new StringTokenizer(in.readLine());
    //         return tok.nextToken();
    //     }
    //     int nextInt() throws IOException {
    //         return Integer.parseInt(next());
    //     }
    // }
    // public void solve() throws IOException {
    //     FastScanner scanner = new FastScanner();
    //     int queries = scanner.nextInt();
    //     Stack<Integer> stack = new Stack<>();
    //     Stack<Integer> maxStack = new Stack<>(); // stack per tenere i massimi
    //     for (int qi = 0; qi < queries; ++qi) {
    //         String operation = scanner.next();
    //         if ("push".equals(operation)) {
    //             int value = scanner.nextInt();
    //             stack.push(value);
    //             if (maxStack.isEmpty() || value >= maxStack.peek()) {
    //                 maxStack.push(value);
    //             }
    //         } else if ("pop".equals(operation)) {
    //             if (!stack.isEmpty()) {
    //                 int removed = stack.pop();
    //                 if (removed == maxStack.peek()) {
    //                     maxStack.pop();
    //                 }
    //             }
    //         } else if ("max".equals(operation)) {
    //             if (!maxStack.isEmpty()) {
    //                 System.out.println(maxStack.peek());
    //             }
    //         }
    //     }
    // }

    //PERFECT SOLUTION!!! tutte 3 le opr sono O(1)
    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> maxStack = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int q = scanner.nextInt();
        MaxStack maxStack = new MaxStack();
        for( int i=0; i<q; i++ ){
            String command = scanner.next();  //get input che puo voler che si esegua push/pop/max
            switch( command ){
                case "push":
                    int value = scanner.nextInt();  //get input
                    maxStack.push(value);
                    break;
                case "pop":
                    maxStack.pop();
                    break;
                case "max":
                    System.out.println( maxStack.max() );
                    break;
            }
        }
        scanner.close();
    }
    public void push( int value ){
        stack.addFirst( value );
        if( maxStack.isEmpty() ){
            maxStack.addFirst( value );
        } else {
            maxStack.addFirst( Math.max(value, maxStack.peekFirst()) );  //find the max and add it, anche se rimane sempre stesso item duplicato, serve x track levels
        }
    }
    public void pop(){
        if( !stack.isEmpty() ){
            stack.pollFirst();
            maxStack.pollFirst();
        }
    }
    public int max() {
        return maxStack.peekFirst();
    }
    
}
