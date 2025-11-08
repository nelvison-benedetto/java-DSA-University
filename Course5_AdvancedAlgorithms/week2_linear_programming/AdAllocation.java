import java.io.*;
import java.util.*;

public class AdAllocation {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    int allocateAds(int n, int m, double A[][], double[] b, double[] c, double[] x) {
        // Simplex algorithm
        int p = n, q = m;
        double[][] mat = new double[p + 1][q + p + 1]; // tableau

        // fill constraints: Ax <= b
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < q; j++)
                mat[i][j] = A[i][j];
            mat[i][q + i] = 1; // slack variable
            mat[i][q + p] = b[i];
        }

        // fill objective: maximize c^T x
        for (int j = 0; j < q; j++)
            mat[p][j] = -c[j]; // maximize

        // basis
        int[] basis = new int[p];
        for (int i = 0; i < p; i++) basis[i] = q + i;

        while (true) {
            int col = -1;
            for (int j = 0; j < q + p; j++)
                if (mat[p][j] < -1e-9) { col = j; break; }
            if (col == -1) break; // optimal

            int row = -1;
            double minRatio = Double.POSITIVE_INFINITY;
            for (int i = 0; i < p; i++) {
                if (mat[i][col] > 1e-9) {
                    double ratio = mat[i][q + p] / mat[i][col];
                    if (ratio < minRatio) { minRatio = ratio; row = i; }
                }
            }
            if (row == -1) return 1; // unbounded

            pivot(mat, row, col, p, q + p);
            basis[row] = col;
        }

        // fill solution
        Arrays.fill(x, 0);
        for (int i = 0; i < p; i++)
            if (basis[i] < q)
                x[basis[i]] = mat[i][q + p];

        return 0;
    }

    void pivot(double[][] mat, int row, int col, int p, int q) {
        double pivotVal = mat[row][col];
        for (int j = 0; j <= q; j++) mat[row][j] /= pivotVal;
        for (int i = 0; i <= p; i++) {
            if (i == row) continue;
            double factor = mat[i][col];
            for (int j = 0; j <= q; j++) mat[i][j] -= factor * mat[row][j];
        }
    }

    void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        double[][] A = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                A[i][j] = nextInt();
        double[] b = new double[n];
        for (int i = 0; i < n; i++) b[i] = nextInt();
        double[] c = new double[m];
        for (int i = 0; i < m; i++) c[i] = nextInt();
        double[] x = new double[m];
        int t = allocateAds(n, m, A, b, c, x);
        if (t == -1) out.println("No solution");
        else if (t == 1) out.println("Infinity");
        else {
            out.println("Bounded solution");
            for (int i = 0; i < m; i++)
                out.printf("%.15f%c", x[i], i + 1 == m ? '\n' : ' ');
        }
    }

    AdAllocation() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new AdAllocation();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try { st = new StringTokenizer(br.readLine()); } 
            catch (Exception e) { eof = true; return null; }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException { return Integer.parseInt(nextToken()); }
}
