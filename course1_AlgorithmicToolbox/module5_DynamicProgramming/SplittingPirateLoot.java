import java.util.Scanner;

public class SplittingPirateLoot {
    static int[] v;
    static int n;
    static int target;
    public static void main(String[] args) {
        //Input: n numeri interi Sequenza v1, v2, ..., vn
        //Output: 1 se è possibile dividere in 3 sottoinsiemi con somma uguale 0 altrimenti
        //Vincoli: 1 ≤ n ≤ 20 → possiamo provare tutte le combinazioni senza problemi di performance.
                 //1 ≤ vi ≤ 30

        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        v = new int[n];
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            v[i] = input.nextInt();
            totalSum += v[i];
        }
        input.close();
        if (totalSum % 3 != 0) {
            System.out.println(0);
            return;
        }
        target = totalSum / 3;
        boolean possible = canPartition(0, 0, 0, 0);
        System.out.println(possible ? 1 : 0);
    }
    // Funzione ricorsiva: prova a inserire v[i] in uno dei tre sottoinsiemi
    private static boolean canPartition(int i, int sum1, int sum2, int sum3) {
        if (sum1 > target || sum2 > target || sum3 > target) {
            return false;
        }

        if (i == n) {
            return sum1 == target && sum2 == target && sum3 == target;
        }

        return canPartition(i + 1, sum1 + v[i], sum2, sum3) ||
               canPartition(i + 1, sum1, sum2 + v[i], sum3) ||
               canPartition(i + 1, sum1, sum2, sum3 + v[i]);
    }
}
