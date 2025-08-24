import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class PrimitiveCalculator {
    public static void main(String[] args) {
        //classico problema di Dynamic Programming
        //Input: An integer n.
        //Output: The minimum number of operations “+1”, “×2”, and “×3” needed to get n from 1.

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.close();
        int[] dp = new int[n + 1];  // dp[i] = min operations per arrivare a i
        int[] prev = new int[n + 1]; // prev[i] = predecessore ottimale di i
        dp[1] = 0; // base case: da 1 a 1 servono 0 operazioni
        for (int i = 2; i <= n; i++) {
            // caso base: arrivare a i da i-1 (+1)
            dp[i] = dp[i - 1] + 1;
            prev[i] = i - 1;
            // caso ×2
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                prev[i] = i / 2;
            }
            // caso ×3
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                prev[i] = i / 3;
            }
        }
        //1. Stampiamo il numero minimo di operazioni
        System.out.println(dp[n]);
        //2. Ricostruiamo la sequenza di numeri
        List<Integer> sequence = new ArrayList<>();
        for (int i = n; i > 0; i = prev[i]) {
            sequence.add(i);
        }
        Collections.reverse(sequence); // invertiamo per avere da 1 a n
        for (int x : sequence) {
            System.out.print(x + " ");
        }
    }
}
