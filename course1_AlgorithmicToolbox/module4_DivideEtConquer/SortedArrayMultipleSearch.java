import java.util.Scanner;

public class SortedArrayMultipleSearch {
    public static void main(String[] args) {
        //hai due array: K = array ordinato di n interi distinti e Q = array di m query
        //per ogni elemento q in Q, devi verificare se esiste in K. Se sì, restituisci l’indice in K, altrimenti -1.
        //l’array K è ordinato
        //strategy: usiamo la Binary Search per trovare ogni q in O(log n)

    //     Scanner input = new Scanner(System.in);  //solution naive by AI
    //     int n = input.nextInt();
    //     int[] K = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         K[i] = input.nextInt();
    //     }
    //     int m = input.nextInt();
    //     int[] Q = new int[m];
    //     for (int i = 0; i < m; i++) {
    //         Q[i] = input.nextInt();
    //     }
    //     for (int i = 0; i < m; i++) {
    //         System.out.print(binarySearch(K, Q[i]) + " ");
    //     }
    //     input.close();
    // }
    // private static int binarySearch(int[] arr, int key) {
    //     int left = 0, right = arr.length - 1;
    //     while (left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if (arr[mid] == key) {
    //             return mid;
    //         } else if (arr[mid] < key) {
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }
    //     return -1; //not found
    }

    //PERFECT SOLUTION!! 99% by me!!!
    public static int[] findIndicesInSortedArray( int[] numstofind, int[] sortedarr ){
        //return arr con in slot index dell num trovato altrimenti -1
        int[] res = new int[numstofind.length];
        for(int i=0; i<res.length; i++){
            res[i] = binary_search_returnIdx(numstofind[i], sortedarr);
        }
        return res;
    }
    public static int binary_search_returnIdx(int target, int[] arr){  //O(log n)
        int left = 0;
        int right = arr.length-1;
        boolean isFound = false;
        while(left<=right && !isFound){
            int mid = (left+right)/2;  //remember to sum!!
            if(arr[mid] == target) {
                isFound = true;
                return mid;
            }
            else if(arr[mid]<target) left= mid+1;
            else right=mid-1;
        }
        return -1;
    }

}
