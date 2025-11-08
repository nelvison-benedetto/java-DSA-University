import java.io.*;
import java.util.*;

public class Diet {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Solve LP by enumerating all vertices
    int solveDietProblem(int n, int m, double A[][], double[] b, double[] c, double[] x) {
        int totalConstraints = n + m; // add x_i >= 0 constraints
        double[][] A2 = new double[totalConstraints][m];
        double[] b2 = new double[totalConstraints];

        // Copy original inequalities
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, A2[i], 0, m);
            b2[i] = b[i];
        }

        // Add non-negativity constraints x_i >= 0 => -x_i <= 0
        for (int i = 0; i < m; i++) {
            A2[n + i][i] = -1;
            b2[n + i] = 0;
        }

        double bestValue = Double.NEGATIVE_INFINITY;
        double[] bestX = new double[m];

        int[] indices = new int[m];
        // Generate all combinations of m constraints out of totalConstraints
        if (!enumerateVertices(A2, b2, m, totalConstraints, 0, 0, indices, c, bestX, bestValue)) {
            return -1; // No solution
        }

        System.arraycopy(bestX, 0, x, 0, m);
        if (bestValue > 1e18) return 1; // Infinity
        return 0; // bounded
    }

    boolean enumerateVertices(double[][] A, double[] b, int m, int totalConstraints,
                              int start, int depth, int[] indices,
                              double[] c, double[] bestX, double bestValueRef) {
        if (depth == m) {
            double[] candidate = solveSystem(A, b, indices);
            if (candidate == null) return true;
            // Check if all constraints are satisfied
            for (int i = 0; i < totalConstraints; i++) {
                double sum = 0;
                for (int j = 0; j < m; j++) sum += A[i][j] * candidate[j];
                if (sum > b[i] + 1e-8) return true; // violates constraint
            }
            double value = 0;
            for (int j = 0; j < m; j++) value += c[j] * candidate[j];
            if (value > bestValueRef) {
                bestValueRef = value;
                System.arraycopy(candidate, 0, bestX, 0, m);
            }
            return true;
        }
        for (int i = start; i <= totalConstraints - (m - depth); i++) {
            indices[depth] = i;
            if (!enumerateVertices(A, b, m, totalConstraints, i + 1, depth + 1, indices, c, bestX, bestValueRef)) return false;
        }
        return true;
    }

    double[] solveSystem(double[][] A, double[] b, int[] indices) {
        int m = indices.length;
        double[][] mat = new double[m][m];
        double[] rhs = new double[m];
        for (int i = 0; i < m; i++) {
            int idx = indices[i];
            System.arraycopy(A[idx], 0, mat[i], 0, m);
            rhs[i] = b[idx];
        }
        return gaussian(mat, rhs);
    }

    double[] gaussian(double[][] a, double[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // Find pivot
            int pivot = i;
            for (int j = i + 1; j < n; j++) if (Math.abs(a[j][i]) > Math.abs(a[pivot][i])) pivot = j;
            if (Math.abs(a[pivot][i]) < 1e-9) return null; // singular
            double[] tempRow = a[i]; a[i] = a[pivot]; a[pivot] = tempRow;
            double tempB = b[i]; b[i] = b[pivot]; b[pivot] = tempB;

            // Eliminate
            for (int j = i + 1; j < n; j++) {
                double factor = a[j][i] / a[i][i];
                for (int k = i; k < n; k++) a[j][k] -= factor * a[i][k];
                b[j] -= factor * b[i];
            }
        }
        // Back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = b[i];
            for (int j = i + 1; j < n; j++) sum -= a[i][j] * x[j];
            x[i] = sum / a[i][i];
        }
        return x;
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
        int t = solveDietProblem(n, m, A, b, c, x);
        if (t == -1) {
            out.println("No solution");
        } else if (t == 1) {
            out.println("Infinity");
        } else {
            out.println("Bounded solution");
            for (int i = 0; i < m; i++)
                out.printf("%.15f%c", x[i], i + 1 == m ? '\n' : ' ');
        }
    }

    Diet() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Diet();
    }

    String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
