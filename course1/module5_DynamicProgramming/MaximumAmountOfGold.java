import java.util.Scanner;

public class MaximumAmountOfGold {
    public static void main(String[] args) {
        //Input: A set of n gold bars of integer weights w1, . . . ,wn and a backpack that can hold at most W pounds.
        //Output: A subset of gold bars of maximum total weight not exceeding W.
        Scanner input = new Scanner(System.in);
        int W = input.nextInt(); // capacità zaino
        int n = input.nextInt(); // numero di barre
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = input.nextInt();
        }
        input.close();
        // DP table: dp[i][w] = max peso con le prime i barre e capacità w
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            int wi = weights[i - 1]; // peso della barra corrente
            for (int w = 1; w <= W; w++) {
                dp[i][w] = dp[i - 1][w]; // non prendo la barra
                if (wi <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - wi] + wi);
                }
            }
        }
        System.out.println(dp[n][W]);
    }
}
