import java.util.Scanner;

public class BinarySearch {
    // public static void main(String[] args) {
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
    // }

    //by me
    // public static int[] findIndicesInSortedArray( int[] numstofind, int[] sortedarr ){
    //     //return arr con in slot index dell num trovato altrimenti -1
    //     int[] res = new int[numstofind.length];
    //     for(int i=0; i<res.length; i++){
    //         res[i] = binary_search_returnIdx(numstofind[i], sortedarr);
    //     }
    //     return res;
    // }
    // public static int binary_search_returnIdx(int target, int[] arr){  //O(log n)
    //     int left = 0;
    //     int right = arr.length-1;
    //     boolean isFound = false;
    //     while(left<=right && !isFound){
    //         int mid = (left+right)/2;  //remember to sum!!
    //         if(arr[mid] == target) {
    //             isFound = true;
    //             return mid;
    //         }
    //         else if(arr[mid]<target) left= mid+1;
    //         else right=mid-1;
    //     }
    //     return -1;
    // }

    //PERFECT SOLUTION!!!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        StringTokenizer st;
        int n = Integer.parseInt( br.readLine() );
        st = new StringTokenizer( br.readLine() );
        int[] K = new int[n];
        for( int i=0; i<n; i++ ){
            K[i] = Integer.parseInt( st.nextToken() );  //build staticarr K
        }
        //leggi m e array Q
        int m = Integer.parseInt( br.readLine() );
        st = new StringTokenizer( br.readLine() );
        int[] Q = new int[m];
        for( int i=0; i<m; i++ ){
            Q[i] = Integer.parseInt(st.nextToken());  //build staticarr Q
        }
        StringBuilder sb = new StringBuilder();
        for( int q : Q ){  //per ogni query cerca in K
            int res = binarySearch(K, q);  //get idx item target se esiste altrimenti get -1. applica binary search.
            sb.append(res).append(" ");
        }
        System.out.println( sb.toString().trim() );
    }
    static int binarySearch( int[] K, int q ){
        int left = 0, right = K.length - 1;
        while( left<=right ){
            int mid = (left + right) / 2;
            if( K[mid]==q )
                return mid;
            else if( K[mid]<q )
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }


}
