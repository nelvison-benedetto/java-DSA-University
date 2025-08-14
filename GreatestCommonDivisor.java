import java.util.Scanner;

public class GreatestCommonDivisor {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        long a = input.nextLong();
        long b = input.nextLong();

        //apply formula Euclid:  GCD(a,b) == GCD(b,a%b) uso ricorsione fino a quando uno dei due numeri non è ==0 allora return l'altro.
        System.out.println(calcGCD(a, b));    
        input.close();

    }
    public static long calcGCD(long a, long b){  
        if(a==0) return b;
        if(b==0) return a;
        return calcGCD(b, a%b);
    }

}
