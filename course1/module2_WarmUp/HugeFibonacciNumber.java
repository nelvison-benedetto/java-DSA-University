import java.util.Scanner;

public class HugeFibonacciNumber {
    public static void main(String[] args){
        //calcola il F_n modulo m, dato n and m. tieni conto che n > circa 10^6, ma m ≤ 10³
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        long m = input.nextLong();

        int period = getPisanoPeriod(m);
        //now apply formula  F(n) mod m == F(n%P(m)) mod m   //il 'mod' è un %
        long newN = n % period;  
        long slow=0L, fast=1L, x;
        if(newN==0){System.out.println(0);}
        else{
            for (int i=2; i <= newN; i++) {
                //here NON calcoliamo la seq di fibonacci, ma la seq fibonacci DEL MODULO m
                //e.g.w m=3 otterremo   Modulo 3: 0, 1, 1, 2, 0, 2, 2, 1, 0, 1, ...
                x = (slow + fast) % m;  //vogliamo solo il resto della divisione per m
                slow = fast;
                fast = x;
            }
            System.out.println(fast);
        }
    }

    public static int getPisanoPeriod(long m){
        long slow=0L, fast=1L, x;
        for(int i=0; i<m*m; i++){  //max period is m*m
            //here NON calcoliamo la seq di fibonacci, ma la seq fibonacci DEL MODULO m
            //e.g.w m=3 otterremo   Modulo 3: 0, 1, 1, 2, 0, 2, 2, 1, 0, 1, ...
            x = (slow + fast) % m;
            slow = fast;
            fast = x;
            if(slow == 0 && fast == 1) return i+1;  //inizio del nuovo ciclo (xk also seq fibonacci del modulo inizia sempre w (0,1))
            //RITORNIAMO la LUNGHEZZA DEL SETTORE(+1 xk i è partito da zero, manoi vogliamo la lunghezza effettiva)
            //questo è il mio P(m) !!
        }
        return -1;  //x possible error
    }
}
