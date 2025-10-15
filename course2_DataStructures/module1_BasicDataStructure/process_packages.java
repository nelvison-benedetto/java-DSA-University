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


class Request {

}
class Response{

}

class Buffer{

}

public class process_packages{
    
}


