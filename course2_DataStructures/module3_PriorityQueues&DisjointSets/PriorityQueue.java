import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PriorityQueue {

    // private int numWorkers;
    // private int[] jobs;
    // private int[] assignedWorker;
    // private long[] startTime;
    // private FastScanner in;
    // private PrintWriter out;
    // public static void main(String[] args) throws IOException {
    //     new JobQueue().solve();
    // }
    // private void readData() throws IOException {
    //     numWorkers = in.nextInt();
    //     int m = in.nextInt();
    //     jobs = new int[m];
    //     for (int i = 0; i < m; ++i) {
    //         jobs[i] = in.nextInt();
    //     }
    // }
    // private void writeResponse() {
    //     for (int i = 0; i < jobs.length; ++i) {
    //         out.println(assignedWorker[i] + " " + startTime[i]);
    //     }
    // }
    // private void assignJobs() {
    //     assignedWorker = new int[jobs.length];
    //     startTime = new long[jobs.length];
    //     // Min-heap: ordinamento per tempo di disponibilità, in caso di pareggio per ID del lavoratore
    //     PriorityQueue<Worker> pq = new PriorityQueue<>();
    //     for (int i = 0; i < numWorkers; i++) {
    //         pq.add(new Worker(i, 0));
    //     }
    //     for (int i = 0; i < jobs.length; i++) {
    //         int duration = jobs[i];
    //         Worker worker = pq.poll(); // il lavoratore disponibile prima
    //         assignedWorker[i] = worker.id;
    //         startTime[i] = worker.nextFreeTime;
    //         worker.nextFreeTime += duration;
    //         pq.add(worker);
    //     }
    // }    // Classe Worker per la priority queue
    // private static class Worker implements Comparable<Worker> {
    //     int id;
    //     long nextFreeTime;
    //     Worker(int id, long nextFreeTime) {
    //         this.id = id;
    //         this.nextFreeTime = nextFreeTime;
    //     }
    //     @Override
    //     public int compareTo(Worker other) {
    //         if (this.nextFreeTime != other.nextFreeTime) {
    //             return Long.compare(this.nextFreeTime, other.nextFreeTime);
    //         }
    //         return Integer.compare(this.id, other.id);
    //     }
    // }
    // public void solve() throws IOException {
    //     in = new FastScanner();
    //     out = new PrintWriter(new BufferedOutputStream(System.out));
    //     readData();
    //     assignJobs();
    //     writeResponse();
    //     out.close();
    // }
    // static class FastScanner {
    //     private BufferedReader reader;
    //     private StringTokenizer tokenizer;
    //     public FastScanner() {
    //         reader = new BufferedReader(new InputStreamReader(System.in));
    //         tokenizer = null;
    //     }
    //     public String next() throws IOException {
    //         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
    //             tokenizer = new StringTokenizer(reader.readLine());
    //         }
    //         return tokenizer.nextToken();
    //     }
    //     public int nextInt() throws IOException {
    //         return Integer.parseInt(next());
    //     }
    // }

    //PEFECT SOLUTION!!! O(m log n) (priorityqueue è o(log n) ma poi la fai per ogni job m quindi total o(m log n))
    //assegnazione di m job a n thread (worker) paralleli. Per ogni job stampa id_thread(idx del thread che segue this job), start_time(sec quando il job incomincia)
    //i thread prendono i job nell'ordine dato, se più thread sono liberi nello stesso istante vince quello con id più piccolo, un thread non interrompe un job iniziato.
    public static void main( String[] args ){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //number threads(workers) paralleli
        int m = sc.nextInt();  //number jobs (ognuno con durata t[i]s)
        long[] jobs = new long[m];
        for( int i=0; i<m; i++) jobs[i] = sc.nextLong();  //completo staticarr jobs catturando gli input jobs
        PriorityQueue<ThreadJob> pq = new PriorityQueue<>();  //priorityqueue (min-heap), ordina (metod compareTo()) auto secondo (finishTime,threadId)
        for( int i=0; i<n; i++) pq.add( new ThreadJob(0, i) ); //iniziallizzo tutti gli Obj thread disponibili, saranno tutti ordinati per id nel priorityqueue
        StringBuilder sb = new StringBuilder();
        for( int i=0; i<m; i++ ){  //!ora lavoro sui jobs (ognuno come valore ha solo il tempo che ci mette ad essere elaborato)
            ThreadJob th = pq.poll();  //estrai smallest dalla priorityqueue, !!cioe il thread disponibile per primo a lavorare sul nostro job!
            sb.append(th.threadId).append(" ").append(th.finishTime).append("\n");  //crea row str
            long newFinish = th.finishTime + jobs[i];  //update the finish time  
            pq.add(new ThreadJob(newFinish, th.threadId));  //add nella priorityqueu threadid con il suo nuovo finishtime update, pq manterra i threads ordinati
        }
        System.out.print(sb.toString());
    }
    static class ThreadJob implements Comparable<ThreadJob>{
        long finishTime;  //tempo in sec 
        int threadId;  //idx thread che esegue questo job
        ThreadJob( long finishTime, int threadId ){  //
            this.finishTime = finishTime;
            this.threadId = threadId;
        }
        @Override  //🔥prirityqueue call this AUTO quando fai add()/poll() per mantenere min-heap/max-heap ordinato!!
        public int compareTo( ThreadJob other ){  //order per finishTime asc, in caso di pareggio usa threadId asc
            //remember Long.compare(x, y) return -1 se x<y, 0 se x==y, 1 se x>y. quindi compareTo() riceve result e quindi sa come ordinare!
            if( this.finishTime != other.finishTime )
                return Long.compare( this.finishTime, other.finishTime ); //return ordinato x finishTime piu piccolo
            return Integer.compare( this.threadId, other.threadId );  //qua attiva quando finishTime==other.finishTime, return x thread_id piu piccolo
        }
    }
}
