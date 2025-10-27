import java.util.*;

public class QuickSort3WayPartioning_perfectXManyClones {

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

    //PERFECT SOLUTION!!!  O(n log n)
    //quick_sort normale esplode con molti cloness
    //ordina arr in asc, ma usando version di randomize_quick_sort che rimane in O(n log n) anche con clones.
    //semplicmeente usiamo 3 arr: 1 x <mid, 1==mid, 1 >mid. use tech problem Dutch National Flag x 3way partitioning + pivot random.
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for( int i=0; i<n; i++ )
            arr[i] = input.nextInt();  //build the staticarr
        input.close();
        randomizedQuickSort( arr, 0, n - 1 );
        for( int x : arr )
            System.out.print(x + " ");
    }
    static void randomizedQuickSort(int[] arr, int left, int right) {
        if( left>=right ) return;
        int pivotIndex = left + (int)(Math.random() * ( right - left + 1 ) );  //pivot posizione random
        swap( arr, left, pivotIndex );  //now pivot è in testa
        int[] mid = partition3( arr, left, right );  //3way partitioning
        randomizedQuickSort( arr, left, mid[0] - 1 );  //ricorsione su parte <pivot
        randomizedQuickSort( arr, mid[1] + 1, right );  //ricorsione su parte >pivot
    }
    static int[] partition3( int[] arr, int left, int right ){
        int pivot = arr[left];  //xk ricorda che dopo swap value pivot è andato in testa!!
        int lt=left,  //lessthan, prima posizione dove finiranno gli elementi < pivot. l’area < pivot è arr[left .. lt-1]
            gt=right,  //greatherthan, ultima posizione dove finiranno gli elementi > pivot. l’area > pivot è arr[gt+1 .. right]
            i=left;  //x scandire the items
        while( i<=gt ){
            if( arr[i]<pivot ) swap( arr, lt++, i++ );   //swap values arr[lt] <-> arr[i], after +1 su entrambi i pointers: aumentiamo di +1 la zona <pivot e i+1 xk ex-item arr[lt] è gia processato
            else if( arr[i]>pivot ) swap( arr, i, gt-- );  ///swap values arr[i] <-> arr[gt],, after -1 su gt : diminuiamo di -1 la zona >pivot e non aumentiamo i xk ex-itm arr[gt] è nuovo e non è stato ancora esaminato quindi dobbiamo riesaminarlo al prossimo ciclo
            else i++;  //caso == pivot: espandiamo la zona == pivot semplicemente avanzando i
        }
        return new int[]{lt, gt};  //xk ora lt è la prima posizione di == pivot e gt l’ultima, quindi l’intervallo arr[lt..gt] contiene tutti i valori uguali al pivot!!
    }
    static void swap( int[] arr, int i, int j ){  //swap values dentro slots arr[i] <-> arr[j]
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}


