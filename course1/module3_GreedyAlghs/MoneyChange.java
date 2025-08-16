import java.util.Scanner;

public class MoneyChange {
    public static void main(String[] args) {
        //compute the minimum number of coins needed to change the given value into coins with denominations 1, 5, and 10.
        
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        int coins = 0;

        //greedy algh: start with the largest coin
        coins += money / 10;  //number of 10¢ coins
        money = money % 10;   //remainder

        coins += money / 5;   //number of 5¢ coins
        money = money % 5;    //remainder

        coins += money;       //remaining 1¢ coins

        System.out.println(coins);
        scanner.close();
    }
}
