import java.util.Scanner;

public class LongestCommonSubsequenceOfThreeSequences {
    public static void main(String[] args) {
        //Input: Three sequences.
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
        int l = input.nextInt();
        int[] C = new int[l];
        for (int i = 0; i < l; i++) {
            C[i] = input.nextInt();
        }
        input.close();
        int[][][] dp = new int[n + 1][m + 1][l + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= l; k++) {
                    if (A[i - 1] == B[j - 1] && A[i - 1] == C[k - 1]) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(
                            Math.max(dp[i - 1][j][k], dp[i][j - 1][k]),
                            dp[i][j][k - 1]
                        );
                    }
                }
            }
        }
        System.out.println(dp[n][m][l]);
    }
}
