
import java.util.Scanner;

public class MajorityElement {
    public static void main(String[] args) {
        //Input: A sequence of n integers.
        //Output: 1, if there is an element that is repeated more than n/2 times, and 0 otherwise. 
        //strategy: utilizzo di  Boyer–Moore Majority Vote Algorithm

        // Scanner input = new Scanner(System.in);  //solution naive by AI
        // int n = input.nextInt();
        // int[] arr = new int[n];
        // for (int i = 0; i < n; i++) {
        //     arr[i] = input.nextInt();
        // }
        // int candidate = findCandidate(arr);
        // if (isMajority(arr, candidate)) {
        //     System.out.println(1);
        // } else {
        //     System.out.println(0);
        // } 
        // input.close();
    }
    // private static int findCandidate(int[] arr) {  //solution naive by AI
    //     int count = 0, candidate = -1;
    //     for (int num : arr) {
    //         if (count == 0) {
    //             candidate = num;
    //             count = 1;
    //         } else if (num == candidate) {
    //             count++;
    //         } else {
    //             count--;
    //         }
    //     }
    //     return candidate;
    // }
    // private static boolean isMajority(int[] arr, int candidate) {  //solution naive by AI
    //     int count = 0;
    //     for (int num : arr) {
    //         if (num == candidate) count++;
    //     }
    //     return count > arr.length / 2;
    // }

    //PERFECT SOLUTION!!!semplicemente sort e poi fai un For e count quanti sono ==sequence[n/2], infine se count>n/2 allora return 1
    public static int getCheckMajorityItemExists(int[] sequence){ //O(n log n)
        //return 1 se c'è 1 item che ha cloni >sequence/2, altrimenti 0.
        Arrays.sort(sequence);  //O(n log n)
        int n = sequence.length;
        int count =0;
        //int candidate = sequence[n/2];  //possibile num most common
        for(int item : sequence){  //O(n)
            if(item==sequence[n/2]) count++;
        }
        return count > n/2? 1 : 0;
    }

    // public static int getCheckMajorityItemExists(int[] sequence){  //WRONG(not perfect direction), solution not correct xk e.g.[2222333888] pensa di si perche 3==3==3 ma in realta 2 è better, e problemi sui bordi
    //     //return 1 se c'è 1 item che ha cloni >sequence/2, altrimenti 0.
    //     int n = sequence.length;
    //     Arrays.sort(sequence);
    //     int mid = n/2;
    //     // if(n%2==0){
    //     //     if(sequence[mid]==sequence[mid-1]) return 1;   //sequence[mid] sarebbe proprio il box che esce dalla meta esatta sx e va in sezione dx
    //     //     else return 0;
    //     // }
    //     // else{
    //     //     if(sequence[mid]==sequence[mid-1]) return 1;  //sequence[mid] qua è 
    //     //     else return 0;
    //     // }
    //     if(sequence[mid]==sequence[mid-1] || sequence[mid]==sequence[mid+1]) return 1;  //se sequence è pari o dispari non cambia logica
    //     else return 0;
    // }

}
