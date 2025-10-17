import java.util.*;

public class SlidingWindowMaximum {

    // Versione naive: O(n * w)
    // public static void maxSlidingWindowNaive(int[] A, int w) {
    //     for (int i = 0; i <= A.length - w; i++) {
    //         int windowMax = A[i];
    //         for (int j = i + 1; j < i + w; j++) {
    //             windowMax = Math.max(windowMax, A[j]);
    //         }
    //         System.out.print(windowMax + " ");
    //     }
    //     System.out.println();
    // }
    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     int n = scanner.nextInt();
    //     int[] A = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         A[i] = scanner.nextInt();
    //     }
    //     int w = scanner.nextInt();
    //     maxSlidingWindowNaive(A, w);
    // }

    public static int[] slidingWindowMaximum(int n, int[] array, int m){  //array is ordered descending, n is array.length, m is windows length

        if (array==null || n==0 || m==0 || m>n) return new int[0];
        int[] result = new int[n-m+1];  //ricorda che la window scorre di 1,  e 'n-m+1' == 'm<=n'
        Deque<Integer> queue = new ArrayDeque<>(); //per gli idxs
          //quindi in pratica la queue lavora come windows, immagina come testa di un serpente rivolta verso il basso
        for(int i=0; i<n; i++){
            //rimuovi indici fuori dalla finestra (troppo a sinistra)
            while(!queue.isEmpty() && queue.peekFirst() <= i-m){  //quindi 'i-m' rappresenta lo space a fuori a sx della window/il bordo della window
                queue.pollFirst();
            }
            //rimuovi indici dalla coda del serpente, in base al loro value se sono <= al nuovo elemento
            while(!queue.isEmpty() && array[queue.peekLast()] <= array[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);  //aggiungi l’indice corrente
            //registra il massimo quando la finestra è piena
            if( i >= m-1 ){  //la prima window COMPLETA arriva quando i==m-1 (0-based), quindi non puoi salvare in result[] quando sei ai 'i' inferiori della lenght della window
                result[i-m+1] = array[queue.peekFirst()];  //il box target in result è i-m+1
            }
        }
        return result;

    }

}


