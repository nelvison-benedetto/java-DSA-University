import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;

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

        // Scanner input = new Scanner(System.in);  //solution naive AI
        // int n = input.nextInt();
        // double W = input.nextDouble();
        // Item[] items = new Item[n];
        // for (int i = 0; i < n; i++) {
        //     double value = input.nextDouble();
        //     double weight = input.nextDouble();
        //     items[i] = new Item(value, weight);
        // }
        // // Ordina gli oggetti per valore/peso decrescente
        // Arrays.sort(items, (a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));
        // double totalValue = 0.0;
        // double remaining = W;
        // for (Item item : items) {
        //     if (remaining == 0) break;
        //     double amt = Math.min(item.weight, remaining);
        //     totalValue += amt * (item.value / item.weight);
        //     remaining -= amt;
        // }
        // System.out.printf("%.4f\n", totalValue);
        // input.close();
    
    }

    //PERFECT SOLUTION!!!
    static class Item {
        int value;
        int weight;
        Item(int value, int weight){
            this.value = value;
            this.weight = weight;
        }
        // valore per unità di peso
        double valuePerWeight(){
            return (double) value / weight;
        }
    }
    public static double getMaxLootValue(int ncouples, int capacity, int[] weights, int[] values){
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items.add( new Item(values[i], weights[i]) ); // Costruisci la lista di oggetti
        }  
        //ora hai una list di Items, in cui per ognuno puoi call valuePerWeight per ottenere il suo rapporto value/weight
        //Ordina per valore/peso decrescente
        items.sort((a, b) -> Double.compare(b.valuePerWeight(), a.valuePerWeight()));  //ordina gli items desc per il loro rapporto value/weight
        double totalValue = 0.0;
        double currentWeight = 0.0;
        for(Item item : items){
            if (currentWeight + item.weight <= capacity) {
                //prendiamo tutto l'obj
                totalValue += item.value;
                currentWeight += item.weight;
            } else {
                //prendiamo solo una % dell'obj
                double remaining = capacity - currentWeight;
                double fraction = remaining / item.weight;
                totalValue += item.value * fraction;
                break; // lo zaino ora è full ok!!
            }
        }
        return totalValue;
    }


    // public static double getMaxLootValue(int ncouples, int capacity, int[] weights, int[] values){  //WRONG(not perfect direction) non ultimato
    //     //gli items sono limitati, e puoi prender anche e.g. 1/3 di un item.
    //     List<Integer> valuesOrdered = new ArrayList<>( Arrays.stream(values).boxed().collect(Collectors.toList()) );
    //     List<Integer> weightsOrderedbyV = new ArrayList<>( Arrays.stream(values).boxed().collect(Collectors.toList()) );
    //     List<List<Integer>> result = quick_sort(valuesOrdered, weightsOrderedbyV);
    //     List<Integer> valuesOk = result.get(0);
    //     List<Integer> weightsOk = result.get(1);
    //     double totw = 0.0d;  //non obbligatatoria here la d, ma in float f è obbligatoria
    //     double totv = 0.0d;
    //     for(int i=0; i<valuesOk.size(); i++){
    //         if(weightsOk.get(i)<capacity){
    //             totw += weightsOk.get(i);
    //             totv += valuesOk.get(i);
    //         }
    //         else{
    //             // double x = weightsOk[i]-capacity;
    //             // double avaible = weightsOk[i]-x;
    //             // double percentual = (weightsOk[i]/100)* avaible;
    //             // double thevalcorresp = (valuesOk[i]/100)* percentual;
    //             // totw += avaible;
    //             // totv += thevalcorresp;
    //             double remaining = capacity - totw;
    //             double fraction = remaining / weightsOk.get(i);
    //             totw += remaining;
    //             totv += valuesOk.get(i) * fraction;
    //             break;
    //         }
    //     }
    //     return totv;
    // }
    // public static List<Integer> quick_sort(List<Integer> values, List<Integer> weights){  //WRONG ancora errors di logic
    //     int idx0= 0; int idxN=values.size();
    //     int n = values.size();
    //     int mid = n/2;
    //     List<Integer> left = new ArrayList<>();
    //     List<Integer> middle = new ArrayList<>();
    //     List<Integer> right = new ArrayList<>();
    //     List<Integer> leftW = new ArrayList<>();
    //     List<Integer> middleW = new ArrayList<>();
    //     List<Integer> rightW = new ArrayList<>();
    //     for(int i=0; i<n; i++){
    //         if(values.get(i) == mid) {middle.add(values.get(i)); middleW.add(weights.get(i));}
    //         else if(values.get(i) <mid) {left.add(values.get(i)); leftW.add(weights.get(i));}
    //         else {right.add(values.get(i)); rightW.add(weights.get(i));}
    //     }
    //     //return quick_sort(left, leftW)+middle+quick_sort(right, rightW);  queta va bene in python
    //     List<Integer> sortedValues = new ArrayList<>();
    //     List<Integer> sortedWeights = new ArrayList<>();
    //     List<List<Integer>> leftResult = quick_sort(left, leftW);
    //     List<List<Integer>> rightResult = quick_sort(right, rightW);
    //     sortedValues.addAll(leftResult.get(0));
    //     sortedValues.addAll(middle);
    //     sortedValues.addAll(rightResult.get(0));
    //     sortedWeights.addAll(leftResult.get(1));
    //     sortedWeights.addAll(middleW);
    //     sortedWeights.addAll(rightResult.get(1));
    //     List<List<Integer>> result = new ArrayList<>();
    //     result.add(sortedValues);
    //     result.add(sortedWeights);
    //     return result;
    // }

}
