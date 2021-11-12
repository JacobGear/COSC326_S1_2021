import java.util.*;

public class PandP {
    public static ArrayList<String[]> rules = new ArrayList<>();
    public static int peanuts;
    public static int pretzels;
    public static int uno = 0;
    public static int bad = -1;
    public static int goo = 1;
    public static int[][] table;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (!line.trim().isEmpty()) {
                if (Character.isDigit(line.charAt(0))) {
                    String[] numFood = line.split(" ");
                    peanuts = Integer.parseInt(numFood[0]);
                    pretzels = Integer.parseInt(numFood[1]);
                } else {
                    String[] rule = line.split(" ");
                    rules.add(rule);
                }
            } else{
                PandPSam s = new PandPSam(rules, peanuts, pretzels);
                rules = s.act();
                defaultRulesAdd();
                process();
                rules.clear();
            }
        }
        if(peanuts > 0 || pretzels > 0){
            PandPSam s = new PandPSam(rules, peanuts, pretzels);
            rules = s.act();
            defaultRulesAdd();
            process();
            rules.clear();
        }
        scan.close();
    }

    public static void process() {
        table = new int[peanuts + 1][pretzels + 1];
        table[0][0] = -1;
        if (pretzels > 1) {
            table[0][1] = 1;
        }
        if (peanuts > 1) {
            table[1][0] = 1;
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == uno) {
                    for (String[] rule : rules) {
                        int pea = Integer.parseInt(rule[0].replaceAll("[^0-9]", ""));
                        int pret = Integer.parseInt(rule[1].replaceAll("[^0-9]", ""));
                        if (i >= pea && j >= pret) {
                            int x = 0;
                            int y = 0;
                            
                            x = i - pea;                    
                            y = j - pret;                            
                            
                            if (table[x][y] == bad) {
                                table[i][j] = goo;
                                break;
                            }
                        }
                    }
                }
                if (table[i][j] == uno) {
                    table[i][j] = bad;
                }
            }
        }
        game();
    }

    public static void game() {
        //print2Darr(table);
        //inputTest();
        boolean win = false;

        for (String[] rule : rules) {
            int pea = Integer.parseInt(rule[0].replaceAll("[^0-9]", ""));
            int pret = Integer.parseInt(rule[1].replaceAll("[^0-9]", ""));
            if( ( (peanuts - pea) > -1 && (pretzels - pret) > -1 ) && table[peanuts - pea][pretzels - pret] == bad ) {
                System.out.println(pea + " " + pret);
                win = true;
                break;
            }
        }

        if (win == false) System.out.println("0 0");

    }

    public static void print2Darr(int[][] arr2D) {
        for (int i = 0; i < arr2D.length; i++) {
            for (int j = 0; j < arr2D[i].length; j++) {
                System.out.print(arr2D[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void inputTest() {
        System.out.print(peanuts + " ");
        System.out.println(pretzels);
        for (String[] rule : rules) {
            for (int i = 0; i < rule.length; i++) {
                System.out.print(rule[i] + " ");
            }
            System.out.println();
        }
    }

    public static void defaultRulesAdd() {
        String[] r1 = new String[] { "=0", "=1" };
        String[] r2 = new String[] { "=1", "=0" };
        rules.add(r1); rules.add(r2);
    }
}