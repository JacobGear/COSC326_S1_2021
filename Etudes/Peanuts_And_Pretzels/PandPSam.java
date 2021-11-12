import java.util.*;

public class PandPSam {

    private int peanuts;
    private int pretzels;
    private ArrayList<String> rules;

    public PandPSam(ArrayList<String[]> rules, int peanuts, int pretzels) {
        this.peanuts = peanuts;
        this.pretzels = pretzels;
        ArrayList<String> r = new ArrayList<String>();
        for (String[] s : rules) {
            try {
                String[] splitv1 = s[0]
                    .split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                for (String val : splitv1) {
                    r.add(val.replaceAll("\\s+",""));
                }
                String[] splitv2 = s[1]
                    .split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                for (String val : splitv2) {
                    r.add(val.replaceAll("\\s+",""));
                }
            } catch (Exception e) {
                System.out
                    .println("You probably forgot a second variable in rules!");
            }
        }
        this.rules = r;
    }

    public ArrayList<String[]> act() {
        // Scanner sc = new Scanner(rules);
        /*
         * while (sc.hasNextLine()) {
         * String[] s = sc.nextLine().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
         * for (String val : s) {
         * rules.add(val.strip());
         * }
         * }
         */
        List<Integer> c1;
        List<Integer> c2;
        ArrayList<String[]> generatedValues = null;
        ArrayList<String[]> values = new ArrayList<String[]>();
        for (int i = 0; i < rules.size(); i += 4) {
            // If the first variable(Leftmost) says less than
            if (rules.get(i).contains("<")) {
                // Generate c1 starting from 0, based upon operator.
                c1 = op.get(rules.get(i)).range(0,
                    Integer.parseInt(rules.get(i + 1)));
            } else {
                // else generate it from the maximum value, downwards.
                c1 = op.get(rules.get(i))
                    .range(Integer.parseInt(rules.get(i + 1)), peanuts);
            }
            // Do the same for the second variable.
            if (rules.get(i + 2).contains("<")) {
                c2 = op.get(rules.get(i + 2)).range(0,
                    Integer.parseInt(rules.get(i + 3)));
            } else {
                c2 = op.get(rules.get(i + 2))
                    .range(Integer.parseInt(rules.get(i + 3)), pretzels);
            }
            generatedValues = permutations(c1, c2);
            for(String[] s : generatedValues){
                values.add(s);
            }

        }
        return values;
    }

    /**
     * Code to generate all permutations of the array, could probably be
     * optimized.
     */
    private ArrayList<String[]> permutations(List<Integer> i, List<Integer> j) {
        ArrayList<String[]> perms = new ArrayList<String[]>();
        for (Integer a : i) {
            for (Integer b : j) {
                String[] str = {
                    "=" + a,
                    "=" + b 
                };
                perms.add(str);
            }
        }
        return perms;
    }

    /**
     * Custom Range interface to generate Lists based upon the range we know
     * 
     * @author Samuel
     *
     */
    interface Range {
        List<Integer> range(int start, int end);
    }

    /**
     * Map to generate the lists based upon the operator.
     */
    final Map<String, Range> op = new HashMap<String, Range>();
    {
        op.put("<", new Range() {
            public List<Integer> range(int start, int end) {
                List<Integer> l = new ArrayList<Integer>();
                for (int i = 0; i < end; i++) {
                    l.add(i);
                }
                return l;
            }

        });
        op.put(">", new Range() {
            public List<Integer> range(int start, int end) {
                List<Integer> l = new ArrayList<Integer>();
                for (int i = end; i > start; i--) {
                    l.add(i);
                }
                return l;
            }

        });
        op.put("<=", new Range() {
            public List<Integer> range(int start, int end) {
                List<Integer> l = new ArrayList<Integer>();
                for (int i = 0; i <= end; i++) {
                    l.add(i);
                }
                return l;
            }

        });
        op.put(">=", new Range() {
            public List<Integer> range(int start, int end) {
                List<Integer> l = new ArrayList<Integer>();
                for (int i = end; i >= start; i--) {
                    l.add(i);
                }
                return l;
            }

        });
        op.put("=", new Range() {
            public List<Integer> range(int start, int end) {
                List<Integer> l = new ArrayList<Integer>();
                l.add(start);
                return l;
            }
        });
    }
}
