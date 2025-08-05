import java.util.Scanner;

public class LastDigitSumFibonacciNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        long m = 10;  //xk mi chiede solo 
        int period = getPisanoPeriod(m);
        //now apply formula  F(n) mod m == F(n%P(m)) mod m   //il 'mod' è un %
        long newN = (n +2)%period;  //add +2 two more cycle,  part of apply formula sum fibonacci F_0+F_1+F_2+...+F_n == F_n+2 -1 
        long slow=0L, fast=1L, x;

        if(newN == 0){System.out.println(0);}
        else if(newN == 1){
            System.out.println(0);  //xk F_{n+2}=1 dunque ora devi sottrarre -1, quindi 1-1=0
        }
        else{
            for(int i=2; i<newN; i++){
                x = (slow+fast) %m;
                slow = fast;
                fast = x;
            }
            //now apply part formula sum fibonacci F_0+F_1+F_2+...+F_n == F_n+2 -1, voglio fare -1
            // (fast-1) mod 10 == (fast+9) mod 10 , faccio modulo 10 xk Fast puo essere un numero davvero enorme e lungo,
            //ma io voglio solo l'ultima cifra!
            long result = (fast + 9) % 10;
            System.out.println(result);
        }
        input.close();
    }

    public static int getPisanoPeriod(int m){
        long slow=0L, fast=1L, x;
        for(int i=2; i<m*m; i++){
            x = (slow + fast) %m;
            slow=fast;
            fast = x;
            if(slow==0 && fast==1){ return i+1;}
        }
        return -1;  //x error
    }
}
