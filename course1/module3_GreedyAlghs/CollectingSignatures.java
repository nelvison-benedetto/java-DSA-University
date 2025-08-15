class Segment {
    int start, end;
    Segment(int s, int e) {
        start = s;
        end = e;
    }
}
public class CollectingSignatures {
    public static void main(String[] args){
        //hai una serie di segmenti sulla linea dei numeri: [l1, r1], [l2, r2], ..., [ln, rn].
        //devi scegliere un insieme minimo di punti tali che ogni segmento contenga almeno un punto scelto.
        //strategy: ordina i segmenti in base al punto finale destro ri crescente->Inizia dal primo segmento e scegli come punto il suo punto finale destro
           //->rimuovi tutti i segmenti coperti da questo punto->itera finche non ci sono piu segmenti.

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int l = input.nextInt();
            int r = input.nextInt();
            segments[i] = new Segment(l, r);
        }

        //ordina per punto finale destro crescente
        Arrays.sort(segments, Comparator.comparingInt(s -> s.end));
        List<Integer> points = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int point = segments[i].end;  // scegli il punto finale del segmento corrente
            points.add(point);

            //salta tutti i segmenti coperti da questo punto
            i++;
            while (i < n && segments[i].start <= point) {
                i++;
            }
        }
        System.out.println(points.size());
        for (int p : points) {
            System.out.print(p + " ");
        }
        input.close();
    }
}
