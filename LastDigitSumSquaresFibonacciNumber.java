import java.util.Scanner;

public class LastDigitSumSquaresFibonacciNumber {
    public static void main(String[] args){
        //dato n, calc la somma dei quadrati di F_0->F_n. return solo l'ultimo char del result.
        
        //formula x quadrati di fibonacci:  (F_0)²+(F_1)²+...+(F_n)² == F_n * F_(n+1)  ->che in formula math is ∑(n,k=0)(F_k)² notare che n starebbe 'sopra' ∑ e k starebbe 'sotto'
            //pero il (F_0)² si puo ignorare perche ==0
        //formula x quadrati di fibonacci in intervallo m->n:  (F_m)^2 + (F(m+1))^2 + ... + (F_n)^2 == F_n * F_(n+1) - F_(m-1) * F_m
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();

        int period = getPisanoPeriod(10);  //la variabile period per modulo 10 è sempre 60! cmnq per leggibilita la calcolo cmnq
        //riduci n e n+1 al pisanoperiod
        int n_mod = (int)(n % period);
        int n1_mod = (int)((n + 1) % period);

        int Fn = getFibonacciModule(n_mod, 10);
        int Fn1 = getFibonacciModule(n1_mod, 10);
        int result = (Fn * Fn1) % 10;

        System.out.println(result);
        input.close();
    }

    public static int getPisanoPeriod(int m){
        long slow=0L, fast=1L, x;
        for(int i=0; i<m*m; i++){
            x = (slow+fast) % m;
            slow = fast;
            fast = x;
            if(slow == 0 && fast==1) return i+1;
        }
        return -1;
    }

    public static int getFibonacciModule(int n, int mod){
        if (n <= 1)
            return n;
        int slow = 0, fast = 1, x;
        for (int i = 2; i <= n; i++) {
            x = (slow + fast) % mod;
            slow = fast;
            fast = x;
        }
        return fast;
    }

}
