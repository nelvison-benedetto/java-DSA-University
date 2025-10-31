import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MaximumNumberOfPrizes {
    // public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);  naive solution by AI
        // int n = input.nextInt();
        // List<Integer> prizes = new ArrayList<>();
        // int sum = 0;
        // int next = 1;
        // while (sum + next <= n){
        //     prizes.add(next);
        //     sum += next;
        //     next++;
        // }
        // //se la somma non è ancora n, aggiungiamo la differenza all'ultimo premio
        // if (sum < n) {
        //     int lastIndex = prizes.size() - 1;
        //     prizes.set(lastIndex, prizes.get(lastIndex) + (n - sum));
        // }
        // System.out.println(prizes.size());
        // for (int p : prizes) {
        //     System.out.print(p + " ");
        // }
        // input.close();
    // }


    //hai un numero n di caramelle e vuoi distribuirle in premi distinti
    //ogni premio riceve un numero di caramelle maggiore del premio precedente
    //devi massimizzare il numero di premi k
    //strategy: inizia con 1 caramella per il primo premio->aumenta progressivamente il prossimo premio di 1 (2,3,4,...) finché la somma non supera n
    //->se la somma dei primi k numeri interi è minore di n, aggiungi la differenza all’ultimo premio

    public static List<Long> getDistinctSummands(long n) {
        List<Long> summands = new ArrayList<>();
        long sum = 0;  //x tenere traccia delle somma esatta di tutti gli item in dynamicarr summands
        long next = 1;  //next cadidate che vogliamo provare ad aggiungere alla dynamiclist
        while( sum+next<= n ){  //non possiamo usare piu caramelle di quelle max disponibili cioe n!!
            summands.add(next);  //fs(first cycle) aggiunto 'premio' per 1 caramella
            sum += next;  //update
            next++;  //update
        }
        // Se rimane un resto, aggiungilo all'ultimo elemento
        //ora è possibile che sum≤n (perché sommare next avrebbe superato n)
        long remainder = n-sum;
        if( remainder>0 && !summands.isEmpty() ){
            long last = summands.remove( summands.size()-1 );  //rimuoviamo ultimo item inserito, per editarlo e rimetterlo dentro
            summands.add( last + remainder );
        }
        return summands;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        scanner.close();
        List<Long> result = getDistinctSummands(n);
        System.out.println(result.size());
        for( int i=0; i<result.size(); i++ ){
            System.out.print( result.get(i) );
            if( i<result.size()-1 ) System.out.print(" ");
        }
        System.out.println();
    }

}
