import java.util.Scanner;

public class LastDigitFibonacciNumber {
    public static void main(String[] args){
        //fibonacci sequence, dato un n calcola il F_n, una volta trovato trova solo l'ultimo digit/char di quel result.
        //this va bene solo fino a n circa 10^6, per after usa 'Esponenziazione per matrici' / fasterer Pisano Period(ONLY IF u have modulo m≤ 10³)
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        if(n==0){System.out.println(0);}
        else if(n==1){System.out.println(1);}
        else {
            long slow = 0L;
            long fast = 1L;
            for(long i=2; i<=n; i++){  //long!
                long x = slow + fast;
                slow = fast;
                fast = x;
            }
            //String str = Long.toString(x);
            //int istrlast = str.length()-1;
            //Integer last = Integer.parseInt(String.valueOf(str.charAt(istrlast)));
            System.out.println(fast%10);
        }
        input.close();
    }
}
