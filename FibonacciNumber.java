import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciNumber {
    public static void main(String[] args){
        //fibonacci sequence, dato un n calcola il F_n. 
        //this va bene solo fino a n circa 10^6, per after usa 'Esponenziazione per matrici' / fasterer Pisano Period(ONLY IF u have modulo m≤ 10³)
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        if(n==0){System.out.println(0);}
        else if(n==1){System.out.println(1);}
        else{
            long slow = 0L;
            long fast = 1L;
            for(long i=2; i<=n; i++){  //long! cosi sopporta se n >2,147,483,647
                long x = slow + fast;
                slow = fast;
                fast = x;
            }
            System.out.println(fast);
        }
        input.close();
    }
}
