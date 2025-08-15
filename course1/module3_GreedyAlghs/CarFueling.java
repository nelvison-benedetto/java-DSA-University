public class CarFueling {
    public static void main(String[] args){
        //un' auto deve percorrere una distanza totale d da città A a città B.
        //l' auto parte con il pieno e può percorrere al massimo m miglia con un pieno di carburante.
        //lungo la strada ci sono n distributori posizionati a distanze stop1 < stop2 < ... < stopn.
        //calcola il numero minimo di rifornimenti necessari per arrivare a destinazione.
        //se non è possibile arrivare return -1.
        //strategy: 

        Scanner input = new Scanner(System.in);
        int d = input.nextInt();        //distanza totale
        int m = input.nextInt();        //distanza massima con pieno
        int n = input.nextInt();        //numero distributori
        int[] stops = new int[n + 2];   //aggiungiamo start e destinazione
        stops[0] = 0;                   //partenza
        for (int i = 1; i <= n; i++) {
            stops[i] = input.nextInt();
        }
        stops[n + 1] = d;           //destinazione

        int numRefills = 0;
        int current = 0;
        while (current < n + 1) {
            int last = current;
            //vai al distributore più lontano possibile senza esaurire carburante
            while (current + 1 <= n + 1 && stops[current + 1] - stops[last] <= m) {
                current++;
            }
            if (current == last) {
                //non possiamo avanzare
                numRefills = -1;
                break;
            }
            if (current <= n) {
                numRefills++;
            }
        }
        System.out.println(numRefills);
        input.close();
    }
}
