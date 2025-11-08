import java.io.IOException;
import java.util.Scanner;

class Equation {
    Equation(double a[][], double b[]) {
        this.a = a;
        this.b = b;
    }

    double a[][];
    double b[];
}

class Position {
    Position(int column, int raw) {
        this.column = column;
        this.raw = raw;
    }

    int column;
    int raw;
}

public class EnergyValues {

    static Equation ReadEquation() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        if (size == 0) return new Equation(new double[0][0], new double[0]);

        double a[][] = new double[size][size];
        double b[] = new double[size];
        for (int raw = 0; raw < size; ++raw) {
            for (int column = 0; column < size; ++column)
                a[raw][column] = scanner.nextInt();
            b[raw] = scanner.nextInt();
        }
        return new Equation(a, b);
    }

    static Position SelectPivotElement(double a[][], boolean used_rows[], boolean used_columns[]) {
        int size = a.length;
        int pivotRow = -1, pivotCol = -1;
        for (int col = 0; col < size; col++) {
            if (!used_columns[col]) {
                for (int row = 0; row < size; row++) {
                    if (!used_rows[row] && Math.abs(a[row][col]) > 1e-9) {
                        pivotRow = row;
                        pivotCol = col;
                        return new Position(pivotCol, pivotRow);
                    }
                }
            }
        }
        return null; // should not happen if system is solvable
    }

    static void SwapLines(double a[][], double b[], Position pivot_element) {
        int size = a.length;
        int row = pivot_element.raw;
        int col = pivot_element.column;
        for (int j = 0; j < size; j++) {
            double tmp = a[row][j];
            a[row][j] = a[col][j];
            a[col][j] = tmp;
        }
        double tmpb = b[row];
        b[row] = b[col];
        b[col] = tmpb;

        pivot_element.raw = col;
    }

    static void ProcessPivotElement(double a[][], double b[], Position pivot_element) {
        int size = a.length;
        int row = pivot_element.raw;
        int col = pivot_element.column;

        // Normalize pivot row
        double pivotValue = a[row][col];
        for (int j = 0; j < size; j++) {
            a[row][j] /= pivotValue;
        }
        b[row] /= pivotValue;

        // Eliminate column for all other rows
        for (int i = 0; i < size; i++) {
            if (i != row) {
                double factor = a[i][col];
                for (int j = 0; j < size; j++) {
                    a[i][j] -= factor * a[row][j];
                }
                b[i] -= factor * b[row];
            }
        }
    }

    static void MarkPivotElementUsed(Position pivot_element, boolean used_rows[], boolean used_columns[]) {
        used_rows[pivot_element.raw] = true;
        used_columns[pivot_element.column] = true;
    }

    static double[] SolveEquation(Equation equation) {
        double a[][] = equation.a;
        double b[] = equation.b;
        int size = a.length;

        if (size == 0) return new double[0];

        boolean[] used_columns = new boolean[size];
        boolean[] used_rows = new boolean[size];

        for (int step = 0; step < size; ++step) {
            Position pivot_element = SelectPivotElement(a, used_rows, used_columns);
            SwapLines(a, b, pivot_element);
            ProcessPivotElement(a, b, pivot_element);
            MarkPivotElementUsed(pivot_element, used_rows, used_columns);
        }

        return b;
    }

    static void PrintColumn(double column[]) {
        int size = column.length;
        for (int raw = 0; raw < size; ++raw)
            System.out.printf("%.6f ", column[raw]);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Equation equation = ReadEquation();
        double[] solution = SolveEquation(equation);
        PrintColumn(solution);
    }
}
