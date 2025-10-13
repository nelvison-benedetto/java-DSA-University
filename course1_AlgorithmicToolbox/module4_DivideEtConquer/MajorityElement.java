
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

    public static int getMajorityElement(int[] sequence){

        

    }


}
