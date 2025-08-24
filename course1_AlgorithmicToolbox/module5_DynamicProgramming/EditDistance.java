
import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args) {
        //Input: Two strings.
        //Output: The minimum number of single-symbol insertions, deletions, and substitutions to transform one string into the other one.
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        String s2 = input.nextLine();
        input.close();
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        // base cases
        for (int i = 0; i <= n; i++) dp[i][0] = i; // delete all
        for (int j = 0; j <= m; j++) dp[0][j] = j; // insert all
        // riempiamo la tabella DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j], // delete
                        Math.min(
                            dp[i][j - 1],   // insert
                            dp[i - 1][j - 1] // substitute
                        )
                    );
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
