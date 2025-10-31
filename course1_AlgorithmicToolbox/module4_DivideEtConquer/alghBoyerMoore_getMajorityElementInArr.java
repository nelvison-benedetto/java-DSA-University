
import java.util.Scanner;

public class alghBoyerMoore_getMajorityElementInArr {
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

    //BAD semplicemente sort e poi fai un For e count quanti sono ==sequence[n/2], infine se count>n/2 allora return 1
    // public static int getCheckMajorityItemExists(int[] sequence){ //O(n log n)
    //     //return 1 se c'è 1 item che ha cloni >sequence/2, altrimenti 0.
    //     Arrays.sort(sequence);  //O(n log n)
    //     int n = sequence.length;
    //     int count =0;
    //     //int candidate = sequence[n/2];  //possibile num most common
    //     for(int item : sequence){  //O(n)
    //         if(item==sequence[n/2]) count++;
    //     }
    //     return count > n/2? 1 : 0;
    // }


    //PERFECT SOLUTION!!! O(n)
    //output return 1 if there is an element that is repeated more than n/2 times, and 0 otherwise. 
    //🔥🔥use Boyer–Moore Majority Vote Algorithm
    public static int getCheckMajorityItemExistsOK(int[] sequence){ 
        Integer candidate = null;  //potenziale item maggioritario trovato (tramite il processo di eliminazione)
        int count = 0;  //misura la 'forza' del canditato corrente, quando scende a 0 perdiamo il candidato e possiamo sceglierne un'altro
        //rappresenta il numero relativo di occorrenze del candidato rispetto agli elementi che lo 'annullano'. Se esiste un vero maggioritario (> n/2) questo sarà il candidato dopo this for.
        for( int num : sequence ){
            if( count==0 )
                candidate = num;  //change candidato
            count += ( num==candidate ) ? 1 : -1;
        }
        //check xk se non esiste un vero cadidato maggioritario (>n/2), here candidate puo essere qualsiasi valore.
        count = 0;
        for( int num : sequence )
            if( num==candidate ) count++;
        return count > sequence.length/2 ?  1 : 0;
    }

}
