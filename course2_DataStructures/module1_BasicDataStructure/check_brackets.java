import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;

// class Bracket {  //naive solution by AI
//     Bracket(char type, int position) {
//         this.type = type;
//         this.position = position;
//     }
//     boolean Match(char c) {
//         if (this.type == '[' && c == ']')
//             return true;
//         if (this.type == '{' && c == '}')
//             return true;
//         if (this.type == '(' && c == ')')
//             return true;
//         return false;
//     }
//     char type;
//     int position;
// }
// class check_brackets {
//     public static void main(String[] args) throws IOException {
//         InputStreamReader input_stream = new InputStreamReader(System.in);
//         BufferedReader reader = new BufferedReader(input_stream);
//         String text = reader.readLine();
//         Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
//         for (int position = 0; position < text.length(); ++position) {
//             char next = text.charAt(position);
//             if (next == '(' || next == '[' || next == '{') {
//                 // Aggiungo la parentesi aperta allo stack
//                 opening_brackets_stack.push(new Bracket(next, position + 1));
//             }
//             if (next == ')' || next == ']' || next == '}') {
//                 // Controllo se lo stack è vuoto o se la parentesi non corrisponde
//                 if (opening_brackets_stack.isEmpty()) {
//                     System.out.println(position + 1);
//                     return;
//                 }
//                 Bracket top = opening_brackets_stack.pop();
//                 if (!top.Match(next)) {
//                     System.out.println(position + 1);
//                     return;
//                 }
//             }
//         }
//         // Se ci sono parentesi aperte rimaste nello stack
//         if (!opening_brackets_stack.isEmpty()) {
//             System.out.println(opening_brackets_stack.peek().position);
//         } else {
//             System.out.println("Success");
//         }
//     }
// }

public class check_brackets{
    public static void main(String[] args){}
    public static String checkBrackets(String text){
        //PERFECT SOLUTION BY ME 95%!!!
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Deque< Map.Entry<Character,Integer> > stack = new ArrayDeque<>();  
          //oppure al posto di una map dentro una deque, puoi usare un obj MyClassPair<K,V> in cui dentro hai 2 attributi mykey and myvalue
        int idx = 0;
        for(char c : text.toCharArray()){
            idx++;  //xk uso here 1-based
            if(c!=')' && c!='(' && c!=']' && c!='[' && c!='}' && c!='{') continue;
            if(map.containsKey(c)){
                if(stack.isEmpty() || map.get(c) != stack.pop().getKey() ){
                    return "error at " + idx;  //è stata rilevata una ),],}, ma non è stata trovata la sua corrispondente apertura, quindi errore!
                }
            }
            else{
                stack.push( new AbstractMap.SimpleEntry<>(c, idx) );  //add (,[,{ allo stack
            }
        }
        if(!stack.isEmpty()) return "error at " + stack.peekFirst().getValue();  //return idx prima (,[,{ nello stack rimasta aperta senza chiusura
        else return "Success";
    
        //solution by AI
        // Deque<Map.Entry<Character, Integer>> stack = new ArrayDeque<>();
        // Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');
        // for(int i=0; i<text.length(); i++) {
        //     char c = text.charAt(i);
        //     int idx = i+1; // 1-based index
        //     if( " ([{ ".indexOf(c) >= 0 ){   // parentesi aperta: metto nello stack
        //         stack.push( new AbstractMap.SimpleEntry<>(c, idx) );
        //     } else if (  " )]} ".indexOf(c) >= 0  ){  // parentesi chiusa
        //         if( stack.isEmpty() || stack.pop().getKey() != map.get(c) ){
        //             return String.valueOf(idx);  //errore alla posizione idx , .valueOf() per covertere in String (cmnq quando fai concatenazione lo fa auto)
        //         }
        //     }
        // }
        // // Se rimangono parentesi aperte nello stack
        // if (!stack.isEmpty()) {
        //     return String.valueOf(stack.peek().getValue());
        // }
        // return "Success";
    
    }
}

