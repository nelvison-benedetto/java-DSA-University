import java.util.*;

public class SpeedingUpRandomizedQuickSort {

    // public static void main(String[] args) {
    //     //classico esempio dove RandomizedQuickSort rallenta perche ci sono molti cloni
    //     //Input: An integer array with n elements that may contain duplicates.
    //     //Output: Sorted array (generated using a modification of RandomizedQuickSort) that works in O(nlogn) expected time.
    //     //strategy: uso di Dutch National Flag algorithm cioè invece di dividere in < pivot e >= pivot, usiamo <pivot =pivot >pivot
    //     Scanner input = new Scanner(System.in);
    //     int n = input.nextInt();
    //     int[] arr = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         arr[i] = input.nextInt();
    //     }
    //     randomizedQuickSort(arr, 0, n - 1);
    //     for (int x : arr) {
    //         System.out.print(x + " ");
    //     }
    //     input.close();
    // }
    // private static void randomizedQuickSort(int[] arr, int left, int right) {
    //     if (left >= right) return;
    //     int pivotIndex = left + new Random().nextInt(right - left + 1);
    //     swap(arr, left, pivotIndex);
    //     int[] m = partition3(arr, left, right);
    //     randomizedQuickSort(arr, left, m[0] - 1);
    //     randomizedQuickSort(arr, m[1] + 1, right);
    // }
    // // partition3 ritorna [m1, m2] -> m1 = inizio zona uguale, m2 = fine zona uguale
    // private static int[] partition3(int[] arr, int left, int right) {
    //     int pivot = arr[left];
    //     int lt = left;     // arr[left..lt-1] < pivot
    //     int gt = right;    // arr[gt+1..right] > pivot
    //     int i = left;      // elemento corrente
    //     while (i <= gt) {
    //         if (arr[i] < pivot) {
    //             swap(arr, lt, i);
    //             lt++;
    //             i++;
    //         } else if (arr[i] > pivot) {
    //             swap(arr, i, gt);
    //             gt--;
    //         } else {
    //             i++;
    //         }
    //     }
    //     return new int[]{lt, gt};
    // }
    // private static void swap(int[] arr, int i, int j) {
    //     int temp = arr[i];
    //     arr[i] = arr[j];
    //     arr[j] = temp;
    // }

    //PERFECT SOLUTION!!!
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for( int i=0; i<n; i++ )
            arr[i] = input.nextInt();
        input.close();
        randomizedQuickSort( arr, 0, n - 1 );
        for( int x : arr )
            System.out.print(x + " ");
    }
    static void randomizedQuickSort(int[] arr, int left, int right) {
        if( left>=right ) return;
        int pivotIndex = left + (int)(Math.random() * ( right - left + 1 ) );
        swap( arr, left, pivotIndex );
        int[] mid = partition3( arr, left, right );
        randomizedQuickSort( arr, left, mid[0] - 1 );
        randomizedQuickSort( arr, mid[1] + 1, right );
    }
    static int[] partition3( int[] arr, int left, int right ){
        int pivot = arr[left];
        int lt = left, gt = right, i = left;
        while( i<=gt ){
            if( arr[i]<pivot ) swap( arr, lt++, i++ );
            else if( arr[i]>pivot ) swap( arr, i, gt-- );
            else i++;
        }
        return new int[]{lt, gt};
    }
    static void swap( int[] arr, int i, int j ){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}


