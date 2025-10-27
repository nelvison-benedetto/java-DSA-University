import java.util.Scanner;

public class BinarySearchwClones_getFirstOrdered {
    public static void main(String[] args) {
        //in input una sequenza di numeri(anche cloni) anche integer q.
        //trova l'index dello slot nella sequenza dove si trova il PRIMO q esistente, return -1 se non esiste.s

        // Scanner input = new Scanner(System.in);  //solution naive by AI
        // int n = input.nextInt();
        // int[] K = new int[n];
        // for (int i = 0; i < n; i++) {
        //     K[i] = input.nextInt();
        // }
        // int m = input.nextInt();
        // int[] Q = new int[m];
        // for (int i = 0; i < m; i++) {
        //     Q[i] = input.nextInt();
        // }
        // for (int i = 0; i < m; i++) {
        //     System.out.print(firstOccurrence(K, Q[i]));
        //     if (i < m - 1) System.out.print(" ");
        // }
        // input.close();
    }
    // private static int firstOccurrence(int[] arr, int key) {  //solution naive by AI
    //     int left = 0, right = arr.length - 1;
    //     int result = -1;
    //     while (left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if (arr[mid] == key) {
    //             result = mid;   //trovata un'occorrenza
    //             right = mid - 1;  //cerchiamo più a sinistra
    //         } else if (arr[mid] < key) {
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }
    //     return result;
    // }


    //PERFECT SOLUTION!!!
    public static int[] findFirstOccurrences( int[] numsToFind, int[] sortedArr ){
         //sortedArr è order ascendente, se target num ha piu cloni in sortedArr return solo idx primo
         int[] res = new int[numsToFind.length];
        for(int i=0; i<res.length; i++){
            res[i] = binary_search_returnIdxfirstclone(numsToFind[i], sortedArr);
        }
        return res;
    }
    public static int binary_search_returnIdxfirstclone(int target, int[] arr){  //O(log n)
        int left = 0;
        int right = arr.length-1;
        int res = -1;
        boolean isFound = false;
        while(left<=right && !isFound){
            int mid = (left+right)/2;
            if(arr[mid] == target){
                // while(arr[mid-1]!=target){  !!error xk magari ci sono 1000 clones non puoi scorrere, meglio continuare ad usare divideetimper
                //     mid--;
                // }
                // return mid;
                res = mid;
                right= mid-1; //🔥🔥continua ad usare divideetimpera per trovare target item piu a sx possibile e ovveride res!!!
            }
            else if(arr[mid]<target) left=mid+1;
            else right=mid-1;
        }
        return res;
    }
}
