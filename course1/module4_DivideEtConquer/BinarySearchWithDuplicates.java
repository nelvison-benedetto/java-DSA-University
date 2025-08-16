import java.util.Scanner;

public class BinarySearchWithDuplicates {
    public static void main(String[] args) {
        //in input una sequenza di numeri(anche cloni) anche integer q.
        //trova l'index dello slot nella sequenza dove si trova il PRIMO q esistente, return -1 se non esiste.s

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] K = new int[n];
        for (int i = 0; i < n; i++) {
            K[i] = input.nextInt();
        }
        int m = input.nextInt();
        int[] Q = new int[m];
        for (int i = 0; i < m; i++) {
            Q[i] = input.nextInt();
        }
        for (int i = 0; i < m; i++) {
            System.out.print(firstOccurrence(K, Q[i]));
            if (i < m - 1) System.out.print(" ");
        }
        input.close();
    }
    private static int firstOccurrence(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == key) {
                result = mid;   //trovata un'occorrenza
                right = mid - 1;  //cerchiamo più a sinistra
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
