import java.util.Scanner;

public class LeastCommonMultiple {
    public static void main(String[] args){
        //minimo comune multiplo (LCM), find the smallest int that is divisible by both a and b
        Scanner input = new Scanner(System.in);
        long a = input.nextLong();
        long b = input.nextLong();

        //🔥apply formula: LCM(a,b) = (a*b)/GCD(a,b)
        System.out.println(calcLCM(a, b));
        input.close();
    }
    public static long calcLCM(long a, long b){
        //long x = a*b;  //puo fare overflow se i longs sono molto grandi!!
        //return x/calcGCD(a, b);
        return (a / calcGCD(a, b)) * b;  //eviti overflow di x
        
    }
    public static long calcGCD(long a, long b){  
        if(a==0) return b;
        if(b==0) return a;
        return calcGCD(b, a%b);
    }
}
