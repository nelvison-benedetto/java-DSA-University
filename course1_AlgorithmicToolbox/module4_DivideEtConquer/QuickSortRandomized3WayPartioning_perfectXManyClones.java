import java.util.*;

public class QuickSortRandomized3WayPartioning_perfectXManyClones {

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
    //advanced quick_sort: Randomized QuickSort con 3-way partitioning (Dutch National Flag)
    //non esplode con molti clones
    //semplicmente usiamo 3 arr: 1 x <mid, 1==mid, 1 >mid. use tech problem Dutch National Flag x 3way partitioning + pivot random.
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for( int i=0; i<n; i++ )
            nums[i] = input.nextInt();  //build the staticarr nums
        input.close();
        randomizedQuickSort( nums, 0, n - 1 );
        for( int x : nums ) System.out.print(x + " ");
    }
    static void randomizedQuickSort( int[] arr, int left, int right ){
        if( left>=right ) return;
        int pivotIndex = left + (int)( Math.random() * ( right - left + 1 ) );  //genera idx random left->right(inclusi entrambi)
        swap( arr, left, pivotIndex );  //now pivot è in testa (in idx left)!
        int[] mid = partition3( arr, left, right );  //3way partitioning, return arr con 2 idx: lt e gt che delimitano(inclusi) la zona item==pivot cioe ==arr[left]
        randomizedQuickSort( arr, left, mid[0]-1 );  //ricorsione su zona <pivot
        randomizedQuickSort( arr, mid[1]+1, right );  //ricorsione su zona >pivot
    }
    static int[] partition3( int[] arr, int left, int right ){
        int pivot = arr[left];  //xk ricorda che dopo swap value pivot è andato in testa!!
        int lt = left,  //lessthan, prima posizione dove finiranno gli elementi <pivot. zona arr[left .. lt-1]
            gt = right,  //greatherthan, ultima posizione dove finiranno gli elementi >pivot. zona arr[gt+1 .. right]
            i = left;  //x scandire the items
        while( i<=gt ){  //at the start le regioni <pivot e >pivot sono vuote. tutto è nell'area non processata arr[i..gt]
            if( arr[i]<pivot ) swap( arr, lt++, i++ );   //swap values arr[lt]<->arr[i], after +1 su entrambi i pointers: lt++ perche ora l'item (ex arr[i]) è in zona <pivot quindi devo update zona arr[left .. lt-1]!
            else if( arr[i]>pivot ) swap( arr, i, gt-- );  //non fare i++, perche dopo swap ora in in arr[i] c'è un item sconosciuto(da processare in next cycle)!
            else i++;  //caso ==pivot: espandiamo la zona ==pivot lasciando semplicemnte l'elemento dove sta, senza spostarlo in zona a DX dove c'è zona arr[gt+1 .. right], o in zona SX 
        }
        return new int[]{lt, gt};  //🔥🔥in zona tra puntatori lt->gt(inclusi entrambi) ci sono tutti gli item ==pivot!!
    }
    static void swap( int[] arr, int i, int j ){  //swap items in idx i<->j
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
