import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

// class Request {
//     public Request(int arrival_time, int process_time) {
//         this.arrival_time = arrival_time;
//         this.process_time = process_time;
//     }
//     public int arrival_time;
//     public int process_time;
// }

// class Response {
//     public Response(boolean dropped, int start_time) {
//         this.dropped = dropped;
//         this.start_time = start_time;
//     }
//     public boolean dropped;
//     public int start_time;
// }

// class Buffer {
//     public Buffer(int size) {
//         this.size_ = size;
//         this.finish_time_ = new LinkedList<>();
//     }
//     public Response Process(Request request) {
//         int arrival_time = request.arrival_time;
//         int process_time = request.process_time;
//         // Rimuovo pacchetti già processati
//         while (!finish_time_.isEmpty() && finish_time_.peek() <= arrival_time) {
//             finish_time_.poll();
//         }
//         // Se il buffer è pieno → scarto il pacchetto
//         if (finish_time_.size() == size_) {
//             return new Response(true, -1);
//         }
//         // Calcolo l’orario di inizio del pacchetto
//         int start_time = (finish_time_.isEmpty()) ? arrival_time : finish_time_.peekLast();
//         finish_time_.add(start_time + process_time);
//         return new Response(false, start_time);
//     }
//     private int size_;
//     private LinkedList<Integer> finish_time_;
// }
//
// public class process_packages {
//     private static LinkedList<Request> ReadQueries(Scanner scanner) throws IOException {
//         int requests_count = scanner.nextInt();
//         LinkedList<Request> requests = new LinkedList<>();
//         for (int i = 0; i < requests_count; ++i) {
//             int arrival_time = scanner.nextInt();
//             int process_time = scanner.nextInt();
//             requests.add(new Request(arrival_time, process_time));
//         }
//         return requests;
//     }
//     private static LinkedList<Response> ProcessRequests(LinkedList<Request> requests, Buffer buffer) {
//         LinkedList<Response> responses = new LinkedList<>();
//         for (Request request : requests) {
//             responses.add(buffer.Process(request));
//         }
//         return responses;
//     }
//     private static void PrintResponses(LinkedList<Response> responses) {
//         for (Response response : responses) {
//             if (response.dropped) {
//                 System.out.println(-1);
//             } else {
//                 System.out.println(response.start_time);
//             }
//         }
//     }
//     public static void main(String[] args) throws IOException {
//         Scanner scanner = new Scanner(System.in);
//         int buffer_max_size = scanner.nextInt();
//         Buffer buffer = new Buffer(buffer_max_size);
//         LinkedList<Request> requests = ReadQueries(scanner);
//         LinkedList<Response> responses = ProcessRequests(requests, buffer);
//         PrintResponses(responses);
//     }
// }


public class NetworkPacketProcessing{

    //PERFECT SOLUTION!!! O(n) memory O(S) (al massimo S elementi nella coda finishTimes)
    //A[i]s→tempo di arrivo, P[i]→tempo di elaborazione, hai buffer di dimensione fissa S, se è pieno quando arriva un nuovo pacchetto questo pacchetto viene scartato (drop → output -1).
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();   //buffer size
        int n = sc.nextInt();   //tot number of packets
        List<Packet> packets = new ArrayList<>();
        for( int i=0; i<n; i++ ){
            long arrival = sc.nextLong();
            long process = sc.nextLong();
            packets.add( new Packet( arrival, process ) );  //crei obj e aggiungilo al dynamicarr
        }
        List<Long> result = processPackets(S, packets);  //passi buffers_size e dynamicarrPackets alla funct
        for(Long r : result) {
            System.out.println(r);
        }
    }
    static class Packet{  //class Obj
        long arrival;
        long process;
        Packet(long arrival, long process) {
            this.arrival = arrival;
            this.process = process;
        }
    }
    static List<Long> processPackets(int bufferSize, List<Packet> packets){  //ricevi buffers size capacity e dynamicarr packets
        List<Long> result = new ArrayList<>();  //per ogni packet il tempo in cui INIZIA la sua elaborazione, oppure -1 se il pacchetto è stato scartato
        Deque<Long> finishTimes = new ArrayDeque<>();  //doublequeue, rappresenta quanto è pieno il buffer
          //contiene solo i finish_time dei packs attualmente nel buffer in ordine di arrivo.
        for( Packet packet : packets ){
            //spacchetto this packet
            long arrival = packet.arrival;  //tempo di arrivo di this packet
            long process = packet.process;  //tempo quanto tempo ci mette as elaborated di this packet
            //rimuovi tutti i pacchetti già finiti
            while( !finishTimes.isEmpty() && finishTimes.peekFirst() <= arrival ){  //.peekFirst() check oldest, <= ok xke se è allo stesso istante in quell'istante 1seneva e 1entra ok
                //rimuovo tutti i packs con finish>currentArrival xk sono già stati completati prima o esattamente al momento di arrivo del nuovo pacchetto
                finishTimes.pollFirst();  //elimino oldest
            }
            //se buffer pieno quindi non c'è spazio → pacchetto scartato
            if( finishTimes.size() == bufferSize ){  //se il numero di finish times è uguale alla capacità S, non c'è piu spazio
                result.add(-1L);
                continue;  //next cycle
            }
            //calcola tempo di inzio elaborazione di this pack
            long start = finishTimes.isEmpty() ? arrival : finishTimes.peekLast();  
                //se finisfinishTimes is empty (cioe CPU libera) allora il nostro pack inizia immediatamente adesso!, altrimenti check the newest in finishTimes
            long finish = start + process;
            //aggiorna buffer e risultato
            finishTimes.addLast( finish );
            result.add( start );
        }
        return result;
    }

}
