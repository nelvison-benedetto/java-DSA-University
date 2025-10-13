import java.util.Scanner;

public class CarFueling {
    public static void main(String[] args){
        //un' auto deve percorrere una distanza totale d da città A a città B.
        //l' auto parte con il pieno e può percorrere al massimo m miglia con un pieno di carburante.
        //lungo la strada ci sono n distributori posizionati a distanze stop1 < stop2 < ... < stopn.
        //calcola il numero minimo di rifornimenti necessari per arrivare a destinazione.
        //se non è possibile arrivare return -1.
        //strategy: 

        // Scanner input = new Scanner(System.in);  //solution naive AI
        // int d = input.nextInt();        //distanza totale
        // int m = input.nextInt();        //distanza massima con pieno
        // int n = input.nextInt();        //numero distributori
        // int[] stops = new int[n + 2];   //aggiungiamo start e destinazione
        // stops[0] = 0;                   //partenza
        // for (int i = 1; i <= n; i++) {
        //     stops[i] = input.nextInt();
        // }
        // stops[n + 1] = d;           //destinazione
        // int numRefills = 0;
        // int current = 0;
        // while (current < n + 1) {
        //     int last = current;
        //     //vai al distributore più lontano possibile senza esaurire carburante
        //     while (current + 1 <= n + 1 && stops[current + 1] - stops[last] <= m) {
        //         current++;
        //     }
        //     if (current == last) {
        //         //non possiamo avanzare
        //         numRefills = -1;
        //         break;
        //     }
        //     if (current <= n) {
        //         numRefills++;
        //     }
        // }
        // System.out.println(numRefills);
        // input.close();
    }

    //PERFECT SOLUTION!!
    public static int getMinGasStations(int distance, int distancemaxfulltank, int nstations, int[] stops) {
        int[] allStops = new int[nstations + 2]; //ci serve 1 slot a sx per 'start' cioe 0, e uno a dx per traguardo cioe distance
        allStops[0] = 0;  //partenza
        for (int i=0; i<nstations; i++){
            allStops[i + 1] = stops[i];
        }
        allStops[nstations + 1] = distance;
          //e.g. nstations=4, allStops lenght=6; [0][1][2][3][4][5], settiamo allStops[0] a 0, copy&paste the 4 items, set rimanente allStops[nstations+1]
        int numRefills = 0;      //quante volte ti fermi con l'auto per fare il pieno
        int currentRefill = 0;   //posizione attuale nella lista di stazioni
        //loop principale: finché non raggiungiamo la destinazione
        while (currentRefill <= nstations) {  //quindi max arriva fino al penultimo index di allStops, pero giusto cosi xk con allStops[currentRefill + 1] - allStops[lastRefill] check se possiamo arrivare/superare il traguardo
            int lastRefill = currentRefill;  //setta base da cui parti con l'auto, immagina che leghi qui la 'corda'
            //salta da gasstaz a gasstaz, ovviamente prima di saltare pero calcoli se riesci ad arrivarci con il pieno
            while (currentRefill <= nstations &&
                   allStops[currentRefill + 1] - allStops[lastRefill] <= distancemaxfulltank) {
                        currentRefill++;  //currentRefill viene editata, poi una volta uscito dal big cycle risetti la base = currentRefill
            }
            if(currentRefill == lastRefill) {  //non hai fatto neanche un salto da 0->gasstaz1, quindi impossibile arrivare.
                return -1;
            }
            if(currentRefill <= nstations) {
                numRefills++;  //update che ti fermi here per fare benzina, e poi riparti new cycle
            }
        }
        return numRefills;
    }


    // public static int getMinGasStations(int distance, int distancemaxfulltank, int nstations, int[] stops ){  //WRONG(not perfect direction) non ultimato
    //     //allo start hai fuel al 100%, gli stops sono distance start-thisgasstat ordinate ascendente, return -1 se non riesci a raggiungere distance
    //     int runned =0;
    //     int nstarts = 1;
    //     while(runned<distance){
    //         int idxmaxStation = binary_search(distancemaxfulltank*nstarts, stops);
    //         if(idxmaxStation == -1) return -1;
    //         runned = stops[idxmaxStation];
    //         nstarts++;
    //     }
    // } 
    // public static int binary_search(int distancemaxfulltank, int[] stops){
    //     int idx0 = 0; 
    //     int idxN = stops.length-1;
    //     boolean isFound = false;
    //     if(distancemaxfulltank<stops[0]) return -1;  //significa che il mio tank a full non basta enanche per arrivare a gasstaz1
    //     while(idx0<=idxN && !isFound){
    //         int mid = (idx0+idxN)/2;
    //         if(distancemaxfulltank == stops[mid]) return stops[mid];
    //         else if(distancemaxfulltank > stops[mid]) idx0= mid+1;
    //         else idxN= mid-1;
    //     }
    //     return idxN;  //se non trovi una gasstaz == distancemaxfull, return quella appena prima
    // }

}
