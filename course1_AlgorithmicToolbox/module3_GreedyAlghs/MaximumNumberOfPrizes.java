import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MaximumNumberOfPrizes {
    public static void main(String[] args) {
        //hai un numero n di caramelle e vuoi distribuirle in premi distinti
        //ogni premio riceve un numero di caramelle maggiore del premio precedente
        //devi massimizzare il numero di premi k
        //strategy: inizia con 1 caramella per il primo premio->aumenta progressivamente il prossimo premio di 1 (2,3,4,...) finché la somma non supera n
           //->se la somma dei primi k numeri interi è minore di n, aggiungi la differenza all’ultimo premio

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        List<Integer> prizes = new ArrayList<>();
        int sum = 0;
        int next = 1;

        while (sum + next <= n){
            prizes.add(next);
            sum += next;
            next++;
        }
        //se la somma non è ancora n, aggiungiamo la differenza all'ultimo premio
        if (sum < n) {
            int lastIndex = prizes.size() - 1;
            prizes.set(lastIndex, prizes.get(lastIndex) + (n - sum));
        }
        System.out.println(prizes.size());
        for (int p : prizes) {
            System.out.print(p + " ");
        }
        input.close();
    }
}
