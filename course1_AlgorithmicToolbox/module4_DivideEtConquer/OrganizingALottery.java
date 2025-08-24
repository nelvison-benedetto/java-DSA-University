import java.util.Scanner;
import java.util.Arrays;

public class OrganizingALottery {
    public static void main(String[] args) {
        //Input: A list of segments and a list of points.
        //Output: The number of segments containing each point.
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = input.nextInt();
            ends[i] = input.nextInt();
        }
        int[] points = new int[m];
        for (int i = 0; i < m; i++) {
            points[i] = input.nextInt();
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        StringBuilder sb = new StringBuilder();
        for (int p : points) {
            int countStarts = upperBound(starts, p);
            int countEnds = lowerBound(ends, p);
            sb.append(countStarts - countEnds).append(" ");
        }
        System.out.println(sb.toString().trim());
        input.close();
    }
    // primo indice con valore > key
    private static int upperBound(int[] arr, int key) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= key) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    // primo indice con valore >= key
    private static int lowerBound(int[] arr, int key) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < key) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
