import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;

public class MaximumAdvertisementRevenue {
    public static void main(String[] args){
        //hai 2 sequenze di numeri interi positivi: Prezzi per click di ciascun inserzionista(price1,price2,...,pricen) 
        //and numero di click attesi per ciascuno slot pubblicitario (clicks1,clicks2,...,clicksn)
        //strategy: accoppia il prezzo piu alto con il numero di clicks piu alto, e cosi via. in pratica ordna le 2 sequenze i ordine decrescete e moltiplica le coppie
        
        // Scanner input = new Scanner(System.in);  //solution naive by AI
        // int n = input.nextInt(); // numero di slot
        // int[] prices = new int[n];
        // int[] clicks = new int[n];
        // for (int i = 0; i < n; i++) {
        //     prices[i] = input.nextInt();
        // }
        // for (int i = 0; i < n; i++) {
        //     clicks[i] = input.nextInt();
        // }
        // //ordina entrambe le sequenze in ordine crescente
        // Arrays.sort(prices);
        // Arrays.sort(clicks);
        // long revenue = 0;
        // for (int i = 0; i < n; i++) {
        //     revenue += (long) prices[i] * clicks[i];
        // }
        // System.out.println(revenue);
        // input.close();
    }

    //PERFECT SOLUTION!! by me 80%
    public static int getMaxCashFromAdsUcanEarn(int totspaces, int[] clicks, int[] insertionistsprice){
        //n è la length per entrambi gli arr
        //List<Integer> arrprices = new ArrayList<>( Arrays.stream(insertionistsprice).boxed().collect(Collectors.toList()) );
        //List<Integer> arrclicks = new ArrayList<>( Arrays.stream(clicks).boxed().collect(Collectors.toList()) );
        
        //Integer[] pricesBoxed = Arrays.stream(insertionistsprice).boxed().toArray(Integer[]::new);
        //Integer[] clicksBoxed = Arrays.stream(clicks).boxed().toArray(Integer[]::new);
        //Arrays.sort(pricesBoxed, (a,b)->Integer.compare(b,a) );
        //Arrays.sort(clicksBoxed, (a,b)->Integer.compare(b,a) );

        //è un greedy algh xk prendi la coppia piu high, poi la penultima coppia high, e cosi via
        Arrays.sort(insertionistsprice);  
        Arrays.sort(clicks);
        //here non ordino desc, basta che leggo nel for() end->start
        int tot = 0;
        for(int i=totspaces-1; i>=0; i--){
            tot+= insertionistsprice[i]*clicks[i];
        }
        return tot;
    }
}
