
import java.util.Scanner;
public class Sum {
    public static void main(String[] args) {
        //dati 2 n, sommali e trova il result.
        Scanner input = new Scanner(System.in);  // crea uno scanner per leggere input
        //System.out.print("Inserisci il primo numero: ");
        int num1 = input.nextInt();  // legge il primo numero intero
        //System.out.print("Inserisci il secondo numero: ");
        int num2 = input.nextInt();  // legge il secondo numero intero
        int somma = num1 + num2;
        System.out.println(somma);
        input.close();  // chiude lo scanner
    }
}
