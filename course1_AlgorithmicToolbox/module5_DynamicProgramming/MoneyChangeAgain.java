import java.util.Scanner;

public class MoneyChangeAgain {
    public static void main(String[] args) {
        //abbiamo a disposizione monete di valore 1, 3 e 4
        //dato un valore money, dobbiamo calcolare il numero minimo di monete necessarie per formarlo.
        //hint: la strategia greedy (prendere sempre la moneta più grande che non supera il resto) non funziona in questo caso,
        //xk e.g. money = 6, greedy → 4 + 1 + 1 = 3 monete, ma la soluzione ottimale è 3 + 3 = 2 monete !

        // Scanner input = new Scanner(System.in);  //solution naive by AI
        // int money = input.nextInt();
        // input.close();  
        // int[] dp = new int[money + 1];  //programmazione dynamica!! dp[i] = il numero minimo di monete necessarie per cambiare il valore i
        // dp[0] = 0; //dp[0] = 0 → per fare 0 non servono monete!
        //     //dunque array ora è  dp = [0, ?, ?, ?, ?, ?, ?]
        // for(int i = 1; i <= money; i++){
        //     dp[i] = Integer.MAX_VALUE;  //lo faccio diventare il piu grande numero intero possibile
        //     if (i >= 1) dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        //     if (i >= 3) dp[i] = Math.min(dp[i], dp[i - 3] + 1);
        //     if (i >= 4) dp[i] = Math.min(dp[i], dp[i - 4] + 1);
        // }
        // System.out.println(dp[money]);
    }

    public static int calculateMinCoinsBestMethod(int num){
        //e.g.money = 6, greedy → 4 + 1 + 1 = 3 monete, ma la soluzione ottimale è 3 + 3 = 2 monete !
        
    int[] coins = {1, 3, 4};  //coins utilizzabili, sono infinite
    //array per memorizzare la soluzione per ogni valore da 0 a money
    int[] minCoins = new int[money + 1];
    // Base case: 0 monete per cambiare 0
    minCoins[0] = 0;
    // Calcoliamo la soluzione per tutti gli importi da 1 a money
    for (int m = 1; m <= money; m++) {
        // inizializza con un valore grande
        minCoins[m] = Integer.MAX_VALUE;
        // prova tutte le monete disponibili
        for (int coin : coins) {
            if (m - coin >= 0) {
                int candidate = minCoins[m - coin] + 1;
                if (candidate < minCoins[m]) {
                    minCoins[m] = candidate;
                }
            }
        }
    }
    // Il valore ottimale per "money" è ora in minCoins[money]
    return minCoins[money];

    //greedy algh
    // int x= 4;
    // int y = 3;
    // int z = 1;
    // int count =0;
    // count+=num/x;
    // num=num%x;
    // count+=num/y;
    // num=num%y;
    // count+=num;
    // return count;
    }

}
