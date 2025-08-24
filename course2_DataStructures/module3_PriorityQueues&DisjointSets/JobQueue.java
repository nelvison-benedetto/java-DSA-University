import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        // Min-heap: ordinamento per tempo di disponibilità, in caso di pareggio per ID del lavoratore
        PriorityQueue<Worker> pq = new PriorityQueue<>();
        for (int i = 0; i < numWorkers; i++) {
            pq.add(new Worker(i, 0));
        }

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Worker worker = pq.poll(); // il lavoratore disponibile prima
            assignedWorker[i] = worker.id;
            startTime[i] = worker.nextFreeTime;
            worker.nextFreeTime += duration;
            pq.add(worker);
        }
    }

    // Classe Worker per la priority queue
    private static class Worker implements Comparable<Worker> {
        int id;
        long nextFreeTime;

        Worker(int id, long nextFreeTime) {
            this.id = id;
            this.nextFreeTime = nextFreeTime;
        }

        @Override
        public int compareTo(Worker other) {
            if (this.nextFreeTime != other.nextFreeTime) {
                return Long.compare(this.nextFreeTime, other.nextFreeTime);
            }
            return Integer.compare(this.id, other.id);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
