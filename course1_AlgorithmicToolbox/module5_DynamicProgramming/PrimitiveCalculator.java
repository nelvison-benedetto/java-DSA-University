import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class PrimitiveCalculator {
    public static void main(String[] args) {
        //classico problema di Dynamic Programming
        //Input: An integer n.
        //Output: The minimum number of operations “+1”, “×2”, and “×3” needed to get n from 1.

        // Scanner input = new Scanner(System.in);  //naive solution by AI
        // int n = input.nextInt();
        // input.close();
        // int[] dp = new int[n + 1];  // dp[i] = min operations per arrivare a i
        // int[] prev = new int[n + 1]; // prev[i] = predecessore ottimale di i
        // dp[1] = 0; // base case: da 1 a 1 servono 0 operazioni
        // for (int i = 2; i <= n; i++) {
        //     // caso base: arrivare a i da i-1 (+1)
        //     dp[i] = dp[i - 1] + 1;
        //     prev[i] = i - 1;
        //     // caso ×2
        //     if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
        //         dp[i] = dp[i / 2] + 1;
        //         prev[i] = i / 2;
        //     }
        //     // caso ×3
        //     if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
        //         dp[i] = dp[i / 3] + 1;
        //         prev[i] = i / 3;
        //     }
        // }
        // //1. Stampiamo il numero minimo di operazioni
        // System.out.println(dp[n]);
        // //2. Ricostruiamo la sequenza di numeri
        // List<Integer> sequence = new ArrayList<>();
        // for (int i = n; i > 0; i = prev[i]) {
        //     sequence.add(i);
        // }
        // Collections.reverse(sequence); // invertiamo per avere da 1 a n
        // for (int x : sequence) {
        //     System.out.print(x + " ");
        // }
    }

    //PERFECT SOLUTION!!
    public static List<Integer> primitiveCalculator(int n){
        //hai calcolatrice che parte da 1 e puo fare solo operazione +1 / *2 / *3 , dato N devi trovare il min numero di mosse per arrivarci, 
        //return the array che partendo da 1 contiene il risultato di ogni mossa con finale N.

        int[] minOps = new int[n + 1];  //minOps[i] è il minimo operazioni per arrivare 1->i, quindi i è il target num!
          //dunque nell'ultimo slot i= n+1, +1 xk l'idx deve essere esattamente uguale a target num, ed il nostro arr è 0-based
        int[] prev = new int[n + 1]; // serve per ricostruire la sequenza
        minOps[1] = 0;  //partiamo gia da 1, quindi immediatamente raggiuntamo target num, quindi zero mosse necessarie.
        for(int i=2; i<n+1; i++) {  //o anche solo <=
            // inizio assumendo di arrivare da i - 1
            minOps[i] = minOps[i - 1] + 1;  //first sol: arrivare a i prendendo il valore del box precedete ([i-1]) e facendo +1.
            prev[i] = i - 1;  //memo che per ottenere i in questa maniera, proveniamo da i-1
            // se posso arrivare da i/2 e uso meno operazioni
            if (i % 2 == 0 && minOps[i/2] +1 < minOps[i]){  //ovviamente i deve essere divisibile per 2, minOps[i/2] get il numero di mosse per arrivare a i/2  
                //piu facciamo +1 che rappresenta la nostra operation '*2' per cosi arrivare esattamente a i target.
                //ora la confrontiamo con la soluzione (sempre x i target) 'arrivo da i-1' gia salvata nel box target: dunque se per arrivare a i target con operation '*2' faccio meno ops allora override 
                minOps[i] = minOps[i / 2] + 1;
                prev[i] = i / 2;  //memo che per ottenere i in questa maniera, proveniamo da i/2
            }
            if (i % 3 == 0 && minOps[i / 3] + 1 < minOps[i]) {  //ora facciamo la stessa cosa ma per operation '*3'
                minOps[i] = minOps[i / 3] + 1;
                prev[i] = i / 3;
            }
        }
        // ricostruisco la sequenza partendo da n e risalendo ai predecessori
        List<Integer> sequence = new ArrayList<>();
        for (int i=n; i>0; i=prev[i]){  //remember prev[i] contiene il numero dal quale siamo arrivati a i nella sequenza ottimale
            //usiamo for all'indietro, perche prev[i] ti dice solo da dove proviene, non ti dice 'il futuro'
            sequence.add(i);
        }
        Collections.reverse(sequence);  //n→...→1  convers  1→...→n
        return sequence;

    }
    
}
