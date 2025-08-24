import java.util.Scanner;
import java.util.Arrays;

public class MaximumSalary {
    public static void main(String[] args) {
        //hai una lista di numeri positivi e vuoi comporre il numero più grande possibile concatenandoli
        //strategy: definisci un ordinamento personalizzato per due numeri x e y->confronta le due concatenazioni xy vs yx
           //->se xy > yx allora x dovrebbe precedere y nell’ordine finale->quando li hai ordinati tutti allora basta concatenarli all

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] numbers = new String[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = input.next();
        }
        //ordinamento personalizzato
        Arrays.sort(numbers, (a, b) -> (b + a).compareTo(a + b));
        //concatenazione finale
        StringBuilder result = new StringBuilder();
        for (String num : numbers) {
            result.append(num);
        }
        //gestione caso in cui tutti siano zeri
        if (result.charAt(0) == '0') {
            System.out.println("0");
        } else {
            System.out.println(result.toString());
        }
        input.close();
    }
}
