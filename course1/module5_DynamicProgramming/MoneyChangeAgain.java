import java.util.Scanner;

public class MoneyChangeAgain {
    public static void main(String[] args) {
        //abbiamo a disposizione monete di valore 1, 3 e 4
        //dato un valore money, dobbiamo calcolare il numero minimo di monete necessarie per formarlo.
        //hint: la strategia greedy (prendere sempre la moneta più grande che non supera il resto) non funziona in questo caso,
        //xk e.g. money = 6, greedy → 4 + 1 + 1 = 3 monete, ma la soluzione ottimale è 3 + 3 = 2 monete !

        Scanner input = new Scanner(System.in);
        int money = input.nextInt();
        input.close();
            
        int[] dp = new int[money + 1];  //programmazione dynamica!! dp[i] = il numero minimo di monete necessarie per cambiare il valore i
        dp[0] = 0; //dp[0] = 0 → per fare 0 non servono monete!
            //dunque array ora è  dp = [0, ?, ?, ?, ?, ?, ?]

        for(int i = 1; i <= money; i++){
            dp[i] = Integer.MAX_VALUE;  //lo faccio diventare il piu grande numero intero possibile
            if (i >= 1) dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            if (i >= 3) dp[i] = Math.min(dp[i], dp[i - 3] + 1);
            if (i >= 4) dp[i] = Math.min(dp[i], dp[i - 4] + 1);
        }

        System.out.println(dp[money]);
        //MORE INFO
    }
}
