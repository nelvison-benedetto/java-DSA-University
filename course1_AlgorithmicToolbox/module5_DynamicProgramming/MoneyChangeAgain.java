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

    public static int calculateMinCoinsBestMethod(int money){
        //e.g.money=6, greedy → 4 + 1 + 1 = 3 monete, ma la soluzione ottimale è 3 + 3 = 2 monete !
        //minCoins[6]=2 cioe per fare money 6 servono 2 monete
    int[] coins = {1, 3, 4};  //coins utilizzabili, sono infinite
    int[] minCoins = new int[money + 1];  //arr per memorizzare la soluzione per ogni valore da 0 a money
    minCoins[0] = 0;  //0 monete per cambiare 0
    for(int m=1; m<money+1; m++){  //check tutte i subint di money
        minCoins[m] = Integer.MAX_VALUE;  //inizializzi item con un valore grande, se candidate sara piu piccolo allora lo editi 
        for(int coin : coins){  //prova tutte le coins disponibili
            if(m-coin >= 0){  //serve per non andare fuori arr minCoins!! e.g m=1 coin=3 allora false e non entra nella condition!
                int candidate = minCoins[m-coin] + 1;  //[m-coin] per trovare lo slot, quindi semplicemente aumenti di 1 il valore in quello slot
                //dunque ora candidate è il num di monete this.coin per formare this.m
                if(candidate < minCoins[m]){  //edita solo le la sol è inferiore alla sol gia trovata per questo slot
                    minCoins[m] = candidate; 
                }
            }
        }
    }
    //alcuni slots di minCoins rimmarranno cmnq con Integer.MAX_VALUE
    return minCoins[money];  //il valore ottimale per "money" è ora in minCoins[money] cioe l'ultimo cycle m che ha runnato.

    
    //e.g. money=7
    //coin1: candidate[1-1]

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
