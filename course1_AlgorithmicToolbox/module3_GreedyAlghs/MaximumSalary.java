import java.util.Scanner;
import java.util.Arrays;

public class MaximumSalary {
    public static void main(String[] args) {
        //hai una lista di numeri positivi e vuoi comporre il numero più grande possibile concatenandoli
        //strategy: definisci un ordinamento personalizzato per due numeri x e y->confronta le due concatenazioni xy vs yx
           //->se xy > yx allora x dovrebbe precedere y nell’ordine finale->quando li hai ordinati tutti allora basta concatenarli all

        //PERFECT SOLUTION!!!  O(n L log n) (L=lunghezza media delle strs)
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] nums = new String[n];
        for( int i=0; i<n; i++ ) nums[i] = in.next();
        //ordinamento personalizzato
        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));  //x desc order, se (b+a) è maggiore il sort metterà b prima di a
        //concatenazione finale
        StringBuilder sb = new StringBuilder();
        for (String num : nums) sb.append(num);
        String res = sb.toString();
        //se tutti i numeri sono "0"
        if (res.charAt(0) == '0') res = "0";
        System.out.println(res);
        in.close();
    }
    
}
