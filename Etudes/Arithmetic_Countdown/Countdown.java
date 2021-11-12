/**
 * A program that takes input of numbers and a character. The program uses these numbers to see if
 * a output is possible or not according to the rules which the character desribes.
 *
 * @author Jacob Gear - 1070739 - COSC326.
 */

import java.util.*;

public class Countdown{

    static String method;
    static boolean found;

    /**
     * The main class takes input puts it into a scanner and saves the input into
     * two arrays. Then it processes the arrays.
     *
     * @param args.
     */
    public static void main(String[] args){
        
        Scanner stdin = new Scanner(System.in);
        while(stdin.hasNextLine()){            
            String[] strNumbers = stdin.nextLine().split(" ");
            if(!strNumbers[0].equals("")){
                int[] numbers = stringArrToInt(strNumbers);
                String[] target = stdin.nextLine().split(" ");            
                method = "";
                found = false;
                process(numbers, target);
                if(found == false){
                    System.out.println(method + " " + target[0] + " impossible");
                }
            } else{
                System.out.println();
            }            
        }
        
    }

    /**
     * process method takes a string as input and checks if it follows all the date
     * rules. Then it will print the array. If the array is shorter than three it
     * is not a valid date.
     *
     * @params numbers, numbers to see if an output is possible.
     * @params target, the target number.
     */
    public static void process(int[] numbers, String[] target){
        int goal = Integer.parseInt(target[0]);
        method = target[1];

        if(method.equals("L")){
            String addOrMulti = "";
            leftToRight(numbers, goal, 1, numbers[0], addOrMulti);
        } else{
            String addOrMulti = "";
            normal(numbers, goal, 1, numbers[0], 0, addOrMulti);
        }
    }

    /**
     * This algorithm works by adding the first digit in numbers to be the sum
     * then recursivly go down the tree finding all possible outcomes smaller than
     * the goal. If we find a sum that equals the goal it will be outputted.
     *
     * @param numbers, numbers to see if an output is possible.
     * @param goal, the goal we are trying to reach.
     * @param index, the index of numbers we are currently working on.
     * @param sum, the sum of the numbers we are currently working on.
     * @param addOrMulti, saving the path of whether we multiplied or added the sum.
     */
    public static void leftToRight(int[] numbers, int goal, int index, int sum, String addOrMulti){        

        //So we only get one output
        if(found){
            return;
        }
        
        if(sum == goal && index == numbers.length){            
            found = true;
            printArr(numbers, sum, addOrMulti);
            return;
        }
        else if(sum > goal){
            return;
        }
        else{
            if(index == numbers.length){
                return;
            }
        }

        leftToRight(numbers, goal, index+1, sum*numbers[index], addOrMulti+"* ");
        leftToRight(numbers, goal, index+1, sum+numbers[index], addOrMulti+"+ ");
        
    }

    /**
     * This algorithm works by recursivly creating a tree making sure to multiply the numbers
     * first before adding them (using a next variable that holds the next item in the array).
     * If we get a number that matches the goal we output it. if a number
     * is bigger than the goal or there are no numbers in our array left we exit.
     *
     * @param numbers, numbers to see if an output is possible.
     * @param goal, the goal we are trying to reach.
     * @param index, the index of numbers we are currently working on.
     * @param curr, the current number we are working on.
     * @param sum, the sum of the numbers we are currently working on.
     * @param addOrMulti, saving the path of whether we multiplied or added the sum.
     */
    public static void normal(int[] numbers, int goal, int index, int curr, int sum, String addOrMulti){
        //So we only get one output
        if(found){
            return;
        }
        
        if(sum + curr == goal && index == numbers.length){            
            found = true;
            printArr(numbers, sum+curr, addOrMulti);
            return;            
        }
        else if(sum + curr > goal){
            return;
        }
        else{
            if(index == numbers.length){
                return;
            }
        }

        int next = numbers[index];        
        
        normal(numbers, goal, index+1, next, sum+curr, addOrMulti+"+ ");
        normal(numbers, goal, index+1, curr*next, sum, addOrMulti+"* ");
                
    }

    /**
     * This method converts an array of strings to an array of ints
     *
     * @params strNumbers, a string of numbers.
     */
    private static int[] stringArrToInt(String[] strNumbers){
        int[] numbers = new int[strNumbers.length];

        for(int i=0; i<strNumbers.length; i++){
            numbers[i] = Integer.parseInt(strNumbers[i]);
        }

        return numbers;
    }

    /**
     * This method prints an array.
     *
     * @params n, an int array of numbers.
     * @params sum, the sum or acheived goal/target.
     * @params am, the path of * or +.
     */
    private static void printArr(int[] n, int sum, String am){
        
        String numbers = "";
        String[] addOrMulti = arrAddOne(am);
        for(int i=0; i<n.length; i++){
            numbers = numbers + n[i] + " " + addOrMulti[i] + " ";
        }
        
        System.out.print(method+" " + sum + " ");
        System.out.println(numbers);
                    
    }

    /**
     * This method is needed to add a " " character at the end of an array so
     * we do not get end of line exceptions as the path of + and * has one less
     * character then the numbers.
     *
     * @params am, the path of + and *.
     */
    private static String[] arrAddOne(String am){
        String[] addOrMulti = am.split(" ");
        String[] temp = new String[addOrMulti.length + 1];

        for (int i=0; i < addOrMulti.length; i++){
            temp[i] = addOrMulti[i];
        }
        addOrMulti = temp;
        addOrMulti[addOrMulti.length-1] = " ";

        return addOrMulti;        
    }

}
