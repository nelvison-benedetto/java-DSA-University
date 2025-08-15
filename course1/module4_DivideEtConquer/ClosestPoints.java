public class ClosestPoints {
    static class Point {
        long x, y;
        Point(long x, long y) { this.x = x; this.y = y; }
    }
    public static void main(String[] args) {
        //dato un insieme di punti sul piano, trovare la distanza minima tra due punti qualsiasi.
        //Input: prima riga: un intero n (numero di punti), le successive n righe: due interi xi yi separati da spazio, che rappresentano le coordinate del punto i-esimo.
        //Output: un numero decimale che rappresenta la distanza minima tra due punti, con precisione di almeno 6 decimali.
        //Vincoli:  2 ≤ n ≤ 10^5  ,  -10^9 ≤ xi, yi ≤ 10^9

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            long x = input.nextLong();
            long y = input.nextLong();
            points[i] = new Point(x, y);
        }
        input.close();
        Arrays.sort(points, Comparator.comparingLong(p -> p.x));
        Point[] temp = new Point[n];
        double minDist = closest(points, temp, 0, n - 1);
        System.out.printf("%.6f\n", minDist);
    }
    static double closest(Point[] points, Point[] temp, int left, int right) {
        if (left >= right) return Double.MAX_VALUE;
        int mid = (left + right) / 2;
        double d1 = closest(points, temp, left, mid);
        double d2 = closest(points, temp, mid + 1, right);
        double d = Math.min(d1, d2);
        // Merge sort per ordinare per y
        mergeByY(points, temp, left, mid, right);
        // Array per strip
        ArrayList<Point> strip = new ArrayList<>();
        long midX = points[mid].x;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < d) strip.add(points[i]);
        }
        // Confronto solo punti nella strip
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }
    static void mergeByY(Point[] points, Point[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (points[i].y <= points[j].y) temp[k++] = points[i++];
            else temp[k++] = points[j++];
        }
        while (i <= mid) temp[k++] = points[i++];
        while (j <= right) temp[k++] = points[j++];
        for (int l = left; l <= right; l++) points[l] = temp[l];
    }

    static double distance(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
