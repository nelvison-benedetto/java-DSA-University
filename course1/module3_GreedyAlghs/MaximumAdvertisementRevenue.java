public class MaximumAdvertisementRevenue {
    public static void main(String[] args){
        //hai 2 sequenze di numeri interi positivi: Prezzi per click di ciascun inserzionista(price1,price2,...,pricen) 
        //and numero di click attesi per ciascuno slot pubblicitario (clicks1,clicks2,...,clicksn)
        //strategy: accoppia il prezzo piu alto con il numero di clicks piu alto, e cosi via. in pratica ordna le 2 sequenze i ordine decrescete e moltiplica le coppie

        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // numero di slot
        int[] prices = new int[n];
        int[] clicks = new int[n];

        for (int i = 0; i < n; i++) {
            prices[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            clicks[i] = input.nextInt();
        }

        //ordina entrambe le sequenze in ordine crescente
        Arrays.sort(prices);
        Arrays.sort(clicks);

        long revenue = 0;
        for (int i = 0; i < n; i++) {
            revenue += (long) prices[i] * clicks[i];
        }
        System.out.println(revenue);
        input.close();
    }
}
