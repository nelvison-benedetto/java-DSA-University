import java.util.ArrayList;
import java.util.Scanner;

public class MaximumPairwiseProduct {
    public static void main(String[] args){
        //data un n che rappresenta il tot dei singoli n che verranno passati successivamente,
        //trova i 2 n che moltiplicati creano il massimo result possibile.
        Scanner input = new Scanner(System.in);
        Long n = input.nextLong();
        ArrayList<Long> arrlist = new ArrayList<>();
        for (int i=0; i<n; i++) {
            Long numero = input.nextLong();
            arrlist.add(numero);
        }
        Long maxx1 = -1L;
        Long max2 = -1L;
        for(int i=0; i<n; i++){
            Long current = arrlist.get(i);
            if(current >maxx1){
                max2 = maxx1;
                maxx1=current;
            }else{
                if(current>max2){
                    max2= current;
                }
            }
        }
        System.out.println(maxx1*max2);
    }
}
