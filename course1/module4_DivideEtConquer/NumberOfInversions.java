public class NumberOfInversions {
    public static void main(String[] args) {
        //Input: A sequence of n integers a1, ..., an.
        //Output: The number of inversions in the sequence, i.e., the number of indices i < j such that ai > aj.
        //strategy: modifichiamo il  MergeSort per contare le inversioni mentre ordiniamo
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        System.out.println(mergeSortCount(arr, 0, n - 1));
        input.close();
    }
    private static long mergeSortCount(int[] arr, int left, int right) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        long inversions = 0;
        inversions += mergeSortCount(arr, left, mid);
        inversions += mergeSortCount(arr, mid + 1, right);
        inversions += merge(arr, left, mid, right);
        return inversions;
    }

    private static long merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        long invCount = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1); // tutti gli elementi rimanenti in sinistra > arr[j]
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
        return invCount;
    }
}
