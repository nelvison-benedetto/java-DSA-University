import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HashTable_PhoneBook_nonecessaryhashingfunct {

    // private FastScanner in = new FastScanner();
    // private PrintWriter out = new PrintWriter(System.out);
    // // Mappa numero -> nome
    // private Map<Integer, String> contacts = new HashMap<>();
    // public static void main(String[] args) {
    //     new PhoneBook().processQueries();
    // }
    // private Query readQuery() {
    //     String type = in.next();
    //     int number = in.nextInt();
    //     if (type.equals("add")) {
    //         String name = in.next();
    //         return new Query(type, name, number);
    //     } else {
    //         return new Query(type, number);
    //     }
    // }
    // private void writeResponse(String response) {
    //     out.println(response);
    // }
    // private void processQuery(Query query) {
    //     switch (query.type) {
    //         case "add":
    //             contacts.put(query.number, query.name); // aggiunge o sovrascrive
    //             break;
    //         case "del":
    //             contacts.remove(query.number);
    //             break;
    //         case "find":
    //             String name = contacts.get(query.number);
    //             if (name != null) {
    //                 writeResponse(name);
    //             } else {
    //                 writeResponse("not found");
    //             }
    //             break;
    //     }
    // }
    // public void processQueries() {
    //     int queryCount = in.nextInt();
    //     for (int i = 0; i < queryCount; i++) {
    //         processQuery(readQuery());
    //     }
    //     out.flush();
    // }
    // static class Query {
    //     String type;
    //     String name;
    //     int number;
    //     public Query(String type, String name, int number) {
    //         this.type = type;
    //         this.name = name;
    //         this.number = number;
    //     }
    //     public Query(String type, int number) {
    //         this.type = type;
    //         this.number = number;
    //     }
    // }
    // class FastScanner {
    //     BufferedReader br;
    //     StringTokenizer st;
    //     FastScanner() {
    //         br = new BufferedReader(new InputStreamReader(System.in));
    //     }
    //     String next() {
    //         while (st == null || !st.hasMoreTokens()) {
    //             try {
    //                 st = new StringTokenizer(br.readLine());
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //         return st.nextToken();
    //     }
    //     int nextInt() {
    //         return Integer.parseInt(next());
    //     }
    // }

    //PERFECT SOLUTION!!! O(n)
    //hashtable associa chiave->valore quickly: here key is idx e valore is value.
    //immagina armadio con 10M cassetti, per ogni cassetto l'idx rappresenta il numero di telefono mentre il value rappresenta nome contatto rubrica e.g.'AAA_MySelf'
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //tot ops
        String[] phoneBook = new String[10_000_000];  
        //crei staticarr con 0->9_999_999 slots, quindi l'idx target rappresentera il numero di telefono mentre il value è la str
        StringBuilder sb = new StringBuilder();
        for( int i=0; i<n; i++ ){
            String command = sc.next();  //save "add"/"del"/"find" in var
            int number = sc.nextInt();  //save numero di telefono in input in var
            switch(command){
                case "add":  //aggiungi o aggiorna una voce: se number già esiste, aggiorni il nome
                    String name = sc.next();  //save name input wanted in var
                    phoneBook[number] = name;  //update the value of the slot
                    break;
                case "del":  //cancella la voce col numero indicato (se non esiste → ignora)
                    phoneBook[number] = null;  //clean slot
                    break;
                case "find":  //cerca il nome associato a number (stampa "not found" se non c’è)
                    String res = phoneBook[number];
                    sb.append(res == null ? "not found" : res).append("\n");  //append in string sb
                    break;
            }
        }
        System.out.print(sb.toString());  //return sb as solution
    }

}
