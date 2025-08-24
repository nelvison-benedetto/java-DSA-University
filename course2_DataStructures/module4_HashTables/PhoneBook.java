import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    private PrintWriter out = new PrintWriter(System.out);

    // Mappa numero -> nome
    private Map<Integer, String> contacts = new HashMap<>();

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        out.println(response);
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                contacts.put(query.number, query.name); // aggiunge o sovrascrive
                break;
            case "del":
                contacts.remove(query.number);
                break;
            case "find":
                String name = contacts.get(query.number);
                if (name != null) {
                    writeResponse(name);
                } else {
                    writeResponse("not found");
                }
                break;
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; i++) {
            processQuery(readQuery());
        }
        out.flush();
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
