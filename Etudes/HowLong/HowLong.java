import java.util.*;
import java.math.BigInteger;

/**
 * A program that takes a letter followed by characters or integers, this input is then
 * used to work out the value of those letters and therefore calcualte a total for output.
 *
 * @author Jacob Gear - 1070739 - COSC326.
 *
 */
public class HowLong {

    /**
     * The main class creates two Maps. One for the rule (the starting integer) and the
     * other for the string of characters (to find the total of). The main purpose of
     * the main was two take input until there is a blank line then process that
     * information to get the totals then clear the maps for the next input.
     * LinkedHashMaps were used to save the ordering of the input.
     *
     * @param args.
     */
    public static void main(String[] args) {        
        Map<String, String[]> combos = new LinkedHashMap<>();
        Map<String, String> rules = new LinkedHashMap<>();

        Scanner stdin = new Scanner(System.in);
        while(stdin.hasNextLine()) {
            String[] colours = stdin.nextLine().split(" ");
            if(colours[0].equals("")){
                process(combos, rules);
                printOutput(combos);
                combos.clear();
                rules.clear();
                System.out.println();
            } else{
                String[] s = colours[1].split("");
                String[] u = colours[1].split(" ");
                if(!isInteger(colours[1])){
                    combos.put(colours[0], s);
                } else {
                    combos.put(colours[0], u);
                    rules.put(colours[0], colours[1]);
                }
            }
        }        
        if(combos.size() > 0){
            process(combos, rules);
            printOutput(combos);
            combos.clear();
            rules.clear();
        }

    }

    /**
     * Method that processes the Map. It does this by using a triple nested
     * for loop. The first for loop just makes sure the algorithm doesnt
     * loop forever by not allowing processing greater than the size of
     * the map. The second loop grabs the key and extracts its character
     * string in a array. Once the values are extracted (s[j]) it is compared to
     * the rules Map and if theres a match that value is swapped out for the rules
     * value and the map is updated. Then if all the values in the key value pair are
     * integers their totals are counted and put into the rules map as a new rule and
     * the combos map is updated.
     *
     * Visualised example:
     * b bwb / b [b][w][b]
     * b 15515 / b [15][5][15]    
     * b 35 / b [35] -> added to rules map, combos map updated.
     *
     * @param combos reference for the combos map (map of string characters with unknown
     * values).
     *
     * @param rules reference for the rules map (map of the known totals that can be used
     * as rules for the unknown items map).
     */
    public static void process(Map<String, String[]> combos, Map<String, String> rules) {                
        for(int v=0; v<combos.size(); v++){
            for(String i : combos.keySet()){
                String[] s = combos.get(i);
                for(int j=0; j<s.length; j++){
                    if(rules.containsKey(s[j])){                    
                        s[j] = rules.get(s[j]);                        
                    }                    
                    combos.put(i, s);
                }
                if(allInteger(s)){
                    BigInteger count = new BigInteger("0");
                    for(int l=0; l<s.length; l++){
                        BigInteger q = new BigInteger(s[l]);
                        count = count.add(q);
                    }
                    String c = String.valueOf(count);
                    String[] e = c.split(" ");
                    rules.put(i, c);
                    combos.put(i, e);
                }
            }                                 
        }

        calculateNaN(combos);
     }

    /**
     * Method that calculates if there are any elements that are not integers.
     * If there is this must mean there is an impossible loop and NaN should be
     * given as the value for that key instead.
     *
     * Visualised example:
     * a bb
     * b aa
     * a NaN
     * b NaN
     *
     * @param combos, reference to the combos map to find the NaNs.
     */
    public static void calculateNaN(Map<String, String[]> combos){               
        for(String i : combos.keySet()){
            String[] s = combos.get(i);
            int j = 1;
            if(!allInteger(combos.get(i)) || j <= 0){                        
                for(int k=0; k<s.length; k++){
                    String c = "NaN";
                    String[] h = c.split(" ");
                    combos.put(i, h);                        
                }                
            } 
        }                      

    }

    /**
     * Method that prints the output of a Map that has a string key
     * and an array of strings as the value.
     *
     * @param combos, Map to be printed out.
     */
    public static void printOutput(Map<String, String[]> combos){        
        for(String s : combos.keySet()){
            System.out.print(s + " ");
            String[] i = combos.get(s);
            for(int j=0; j<i.length; j++){
                System.out.print(i[j]+"");
            }
            System.out.println();
        }      
        
    }

    /**
     * Method that goes through an array and checks if all its elements are
     * integers.
     *
     * @param s, An array to be checked for ints.
     *
     * @return returns true if all the arrays elements are ints.
     */
    public static boolean allInteger(String[] s){
        for(int i=0; i<s.length; i++){
            if(!isInteger(s[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that gets given a string as input and checks if that
     * string is a number.
     *
     * @param strNumber, string to be checked if it is a number.
     *
     * @return returns true, if the string is a number.
     */
    public static boolean isInteger(String strNumber) {
        for (char c : strNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

}
