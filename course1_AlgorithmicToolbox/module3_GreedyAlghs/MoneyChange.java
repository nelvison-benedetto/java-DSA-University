import java.util.Scanner;

public class MoneyChange {
    public static void main(String[] args) {
        //compute the minimum number of coins needed to change the given value into coins with denominations 1, 5, and 10.
        
        // Scanner scanner = new Scanner(System.in);  //solution by AI
        // int money = scanner.nextInt();
        // int coins = 0;
        // //greedy algh: start with the largest coin
        // coins += money / 10;  //number of 10¢ coins
        // money = money % 10;   //remainder
        // coins += money / 5;   //number of 5¢ coins
        // money = money % 5;    //remainder
        // coins += money;       //remaining 1¢ coins
        // System.out.println(coins);
        // scanner.close();
    }
    public static int calculateMinCoins(Integer num){
        //PERFECT SOLUTION!! O(1)
        int count =0;
        count = count + num/10;  //prendi max pezzi da 10, e aggiungi quel numero di pezzi al numero di pezzi che gia hai in count
        num = num %10;  //num ora cambia
        count += num/5;
        num = num%5;
        count = count + num/1;  //prendi max pezzi da 1
          //o anche solo count += num;
        num = num%5;  //posso anche non farlo cmnq voglio che si capisca
        return count;

        //solution 100% by me O(n)
        // int x = 1;
        // int y = 5;
        // int z = 10;
        // int sum =0;
        // int count = 0;
        // while(sum<num){
        //     if( (num-sum) >= z) sum+=z;  //anche = xk cosi se (num-sum) ==10 mi prende la monta da 10, ora sum e num sono esattamente uguali
        //     else if ( (num-sum) >= y) sum+=y;
        //     else sum+=x;
        //     count++;
        // }
        // return count;
    }
}
