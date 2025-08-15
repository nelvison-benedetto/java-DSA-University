import java.util.Scanner;

public class LastDigitPartialSumFibonacciNumber {
    public static void main(String[] args){
        //dati m ed n, fai la somma della sezione degli F da m a n (entrambi inclusi), 
        //del result prendi solo l'ultima cifra decimale. 
        Scanner input = new Scanner(System.in);
        long m = input.nextLong();
        long n = input.nextLong();

        int period = getPisanoPeriod(10);

        //riduzione di m and n con il pisanoperiod
        m = m%period;
        n = n%period;
        if(n<m){n+= period;} //in caso si verifichi, fai fare 'un giro' su periodo

        long slow=0L, fast=1L, x; long sum=0L;
        //for(int i=m; i<n+1; i++){  //err in questo modo calcoli solo l'ULTIMO result della seq di fibonacci
        //    x = (slow+fast) % 10;
        //    slow = fast;
        //    fast = x;
        //}
        for (int i = 0; i <= n; i++) {
            if (i >= m) {
                sum = (sum + slow) % 10;  //somma il valore corrente F(i), usando slow(not fast che senon salti il primo result in assoluto)
            }
            x = (slow+fast) %10;
            slow = fast;
            fast = x;
        }
        //now sum è la somma di tutti gli F_x in range
        System.out.println(sum%10);
        input.close();
    }

    public static int getPisanoPeriod(long m){
        long slow=0L, fast=1L, x;
        for(int i=0; i<m*m; i++){
            x = (slow+fast) %m;
            slow = fast;
            fast = x;
            if(slow==0 && fast==1) return i+1;
        }
        return -1;
    }
}
