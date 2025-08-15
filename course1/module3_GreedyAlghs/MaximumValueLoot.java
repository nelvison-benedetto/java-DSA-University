class Item {
    double value, weight;
    Item(double v, double w) {
        value = v;
        weight = w;
    }
}

public class MaximumValueLoot {
    public static void main(String[] args) {
        //esempio classico Fractional Knapsack Problem (zaino frazionario) risolvibile con greedy
        //hai un zaino con capacità W(peso massimo che può contenere), hai n oggetti(o "composti"):ogni obj i ha valore c_i e valore w_i
        //puoi prendere frazioni di obj 0<=f_i<=1, non solo interi
        //vuoi massimizzare il valore dello zaino

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        double W = input.nextDouble();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            double value = input.nextDouble();
            double weight = input.nextDouble();
            items[i] = new Item(value, weight);
        }

        // Ordina gli oggetti per valore/peso decrescente
        Arrays.sort(items, (a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));

        double totalValue = 0.0;
        double remaining = W;

        for (Item item : items) {
            if (remaining == 0) break;

            double amt = Math.min(item.weight, remaining);
            totalValue += amt * (item.value / item.weight);
            remaining -= amt;
        }

        System.out.printf("%.4f\n", totalValue);
        input.close();

    }
}
