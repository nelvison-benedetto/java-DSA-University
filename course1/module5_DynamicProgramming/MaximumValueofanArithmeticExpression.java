import java.util.Scanner;

public class MaximumValueofanArithmeticExpression {
    public static void main(String[] args) {
        //Input: An arithmetic expression consisting of digits as well as plus, minus, and multiplication signs.
        //Output: Add parentheses to the expression in order to maximize its value.

        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();
        System.out.println(getMaxValue(s));
    }
    private static long getMaxValue(String exp) {
        int n = (exp.length() + 1) / 2;
        long[][] minValues = new long[n][n];
        long[][] maxValues = new long[n][n];
        // Inizializza la diagonale con i numeri stessi
        for (int i = 0; i < n; i++) {
            long num = exp.charAt(2 * i) - '0';
            minValues[i][i] = num;
            maxValues[i][i] = num;
        }
        // Lunghezza del sottointervallo
        for (int l = 1; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                long minVal = Long.MAX_VALUE;
                long maxVal = Long.MIN_VALUE;
                // Prova ogni punto di divisione
                for (int k = i; k < j; k++) {
                    char op = exp.charAt(2 * k + 1);

                    long a = eval(maxValues[i][k], maxValues[k + 1][j], op);
                    long b = eval(maxValues[i][k], minValues[k + 1][j], op);
                    long c = eval(minValues[i][k], maxValues[k + 1][j], op);
                    long d = eval(minValues[i][k], minValues[k + 1][j], op);

                    minVal = Math.min(minVal, Math.min(Math.min(a, b), Math.min(c, d)));
                    maxVal = Math.max(maxVal, Math.max(Math.max(a, b), Math.max(c, d)));
                }
                minValues[i][j] = minVal;
                maxValues[i][j] = maxVal;
            }
        }
        return maxValues[0][n - 1];
    }
    private static long eval(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: throw new IllegalArgumentException("Operatore sconosciuto: " + op);
        }
    }
}
