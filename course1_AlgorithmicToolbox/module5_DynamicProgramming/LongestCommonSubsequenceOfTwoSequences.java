import java.util.Scanner;

public class LongestCommonSubsequenceOfTwoSequences {
    public static void main(String[] args) {
        //Input: Two sequences.
        //Output: The maximum length of a common subsequence.
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = input.nextInt();
        }
        int m = input.nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = input.nextInt();
        }
        input.close();
        int[][] dp = new int[n + 1][m + 1];
        // riempiamo la matrice DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
