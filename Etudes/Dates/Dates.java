/**
 * A program that takes a date as import, checks to see if it is valid and prints
 * the output according to dd<space><first three chars of month name><space>yyyy
 *
 * @author Jacob Gear - 1070739 - COSC326.
 */

import java.util.*;
import java.io.*;

public class Dates{
    private static String stdout; // Save the input to be printed if there was an error

    /**
     * The main class takes input puts it into a scanner and saves the input into
     * a string and an array. Then it processes the array.
     *
     * @param args.
     */
    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);        
        while(stdin.hasNextLine()){
            String str = stdin.nextLine();
            stdout = str;
            String[] line = new String[3];
            if(str.contains(" ")){
                line = str.split(" ");
            } else if(str.contains("/")){
                line = str.split("/");
            } else if(str.contains("-")){
                line = str.split("-");
            } else{
                line = stdout.split("");;
            }
            
            if(!str.equals("")){
                process(line);                    
            } else{
                System.out.println();
            }
            stdout = "";                
        }
    }

    /**
     * process method takes a string as input and checks if it follows all the date
     * rules. Then it will print the array. If the array is shorter than three it
     * is not a valid date.
     *
     * @params line, the date in form of an array.
     */
    public static void process(String[] line){        
        if(valid(stdout, line)){
            if(yearRules(line[2]) && monthRules(line[1]) && dayRules(line[0], line[1], line[2])){
                printArr(line);
            }               
            
        } 
    }

    /**
     * dayRules method takes in a date and checks to see if it meets the required date rules.
     * if it does, the method will return true, if not an error message will be sent to stdout.
     *
     * @param day, a String day to be entered.
     * @param month, a String month to be entered.
     * @param year, A String year to be entered.
     *
     * @return true if the date meets the required date rules. 
     */
    public static boolean dayRules(String day, String month, String year){

        // If day is not a number print an error
        if(!isInteger(day)){
            System.out.println(stdout + " - INVALID: day is not a number.");
            return false;            
        }                

        //No try catch needed as isInteger above checks if day is an int
        int i = Integer.parseInt(day);
        //No try catch needed as year is already checked in the year method
        int y = Integer.parseInt(year);
        // Leap year check. If there is a leap year feb has 29 days
        if(month.equalsIgnoreCase("feb") || month.equals("2") || month.equals("02")){            
            if(isLeapYear(y)){
                if(i > 29){
                    System.out.println(stdout + " - INVALID: feb leap years have a max of 29 days.");
                    return false;
                }
            } else{
                if(i > 28){
                    System.out.println(stdout + " - INVALID: feb non leap years have a max of 28 days.");
                    return false;
                }
            }
        }

        //Check for the 30 day months
        String m = month;
        m = monthConverter(m);
        if(m.equals("Apr") || m.equals("Jun") || m.equals("Sep") || m.equals("Nov")){
            if(i > 30){
                System.out.println(stdout + " - INVALID: April, June, July & November only have 30 days.");
                return false;
            }
        }

        // A day cannot be smaller than one or greater then 31
        if(i < 1 || i > 31){
            System.out.println(stdout + " - INVALID: day outside of the range of 1-31.");
            return false;
        }

        // The day format must be a length of 1 or a length of 2                
        if(day.length() < 0 || day.length() > 2){
            System.out.print(stdout + " - INVALID: day in the wrong format ");
            System.out.println("Try dd or d or 0d.");
            return false;
        }                   
        
        return true;
    }

    /**
     * monthRules method takes in a month and checks to see if it meets the required month rules.
     * if it does, the method will return true, if not an error message will be sent to stdout.
     *
     * @param month, a String month to be entered.
     *
     * @return true if the date meets the required date rules. 
     */
    public static boolean monthRules(String month){

        // If month is a String it must have a length of three
        if(!isInteger(month) && month.length() != 3){
            System.out.print(stdout + " - INVALID: month is in the wrong format ");
            System.out.println("Try mm or m or 0m or the first three letters of the month name.");
            return false;
        }       

        // If month is a string it must be a valid month (first three letters)
        if(!isInteger(month) && !validMonth(month)){
            System.out.print(stdout + " - INVALID: month is in the wrong format ");
            System.out.println("Try mm or m or 0m or the first three letters of the month name.");
            return false;            
        }

        // If month is a digit it must be in the range of 1 and 12
        try{
            int i = Integer.parseInt(month);            
            if(i < 1 || i > 12){
                System.out.println(stdout + " - INVALID: month outside of the rannge of 1-12.");
                return false;
            }
        } catch(RuntimeException e){
            //Do nothing
        }

        if(isInteger(month) && month.length() > 2){
            System.out.println(stdout + " - INVALID: month is in the wrong format Try mm or m or 0m or the first three letters of the month name.");
            return false;
        }
        
        return true;
    }

    /**
     * yearRules method takes in a year and checks to see if it meets the required year rules.
     * if it does, the method will return true, if not an error message will be sent to stdout.
     *
     * @param year, a String year to be entered.
     *
     * @return true if the date meets the required date rules. 
     */
    public static boolean yearRules(String year){

        // Year must be an integer
        if(!isInteger(year)){
            System.out.println(stdout + " - INVALID: year is not a number.");
            return false;            
        }        
        
        // No try catch as year has to be a number at this point
        int i = Integer.parseInt(year);
        // If yyyy year must be in the range of 1753 & 3000
        if(year.length() == 4 && i < 1753 || i > 3000){
            System.out.println(stdout + " - INVALID: year outside range of 1753 and 3000.");
            return false;
        }                

        // Year must be in the format of yy or yyyy
        if(year.length() != 2 && year.length() != 4){
            System.out.print(stdout + " - INVALID: year in the wrong format ");
            System.out.println("Try yy or yyyy.");
            return false;
        }        
        
        return true;
    }

    /**
     * printArr method takes in a date in the form of an array. The date is then converted into
     * the correct format and then printed.
     *
     * @param arr, an array date to be printed.
     * 
     */
    public static void printArr(String[] arr){
        arr[2] = yearConverter(arr[2]);
        arr[1] = monthConverter(arr[1]);
        arr[0] = dayConverter(arr[0]);
            
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Method isInteger takes in a String and returns true if
     * it is a number.
     *
     * @param month, a String to be checked if it is a number.
     *
     * @return true if the string is a integer. 
     */
    private static boolean isInteger(String strNumber) {
        int count = 0;
        for (char c : strNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        if(count == strNumber.length()){
            return true;
        }
        
        return false;
    }

    /**
     * Method monthConverter takes in a month in form of a String and converts it into
     * to correct month format.
     *
     * @param month, a String month to be converted into the correct format.
     *
     * @return a month in form of a String in the correct date format. 
     */
    private static String monthConverter(String month){
        if(isInteger(month)){
            if(month.equals("1") || month.equals("01")){
                month = "Jan";            
            } else if(month.equals("2") || month.equals("02")){
                month = "Feb";            
            } else if(month.equals("3") || month.equals("03")){
                month = "Mar";            
            } else if(month.equals("4") || month.equals("04")){
                month = "Apr";            
            } else if(month.equals("5") || month.equals("05")){
                month = "May";            
            } else if(month.equals("6") || month.equals("06")){
                month = "Jun";            
            } else if(month.equals("7") || month.equals("07")){
                month = "Jul";            
            } else if(month.equals("8") || month.equals("08")){
                month = "Aug";            
            } else if(month.equals("9") || month.equals("09")){
                month = "Sep";            
            } else if(month.equals("10")){
                month = "Oct";            
            } else if(month.equals("11")){
                month = "Nov";            
            } else if(month.equals("12")){
                month = "Dec";            
            }
        } else{
            StringBuilder capMonth = new StringBuilder(month);
            char c = month.charAt(0);
            char ch = month.charAt(1);
            char cha = month.charAt(2);
            c = Character.toUpperCase(c);            
            ch = Character.toLowerCase(ch);
            cha = Character.toLowerCase(cha);
            capMonth.setCharAt(0, c);   
            capMonth.setCharAt(1, ch);
            capMonth.setCharAt(2, cha);
            month = capMonth.toString();
        }
        return month;
    }

    /**
     * validMonth method takes in a month in the form of a String and returns
     * true if it is a valid month according to the date format.
     *
     * @param month, a String month to be entered.
     *
     * @return true if the month is a valid month. 
     */
    private static boolean validMonth(String month){        

        if(month.equals("jan") || month.equals("JAN") || month.equals("Jan")){
            return true;
        } else if(month.equals("feb") || month.equals("FEB") || month.equals("Feb")){
            return true;
        } else if(month.equals("mar") || month.equals("MAR") || month.equals("Mar")){
            return true;
        } else if(month.equals("apr") || month.equals("APR") || month.equals("Apr")){
            return true;
        } else if(month.equals("may") || month.equals("MAY") || month.equals("May")){
            return true;
        } else if(month.equals("jun") || month.equals("JUN") || month.equals("Jun")){
            return true;
        } else if(month.equals("jul") || month.equals("JUL") || month.equals("Jul")){
            return true;
        } else if(month.equals("aug") || month.equals("AUG") || month.equals("Aug")){
            return true;
        } else if(month.equals("sep") || month.equals("SEP") || month.equals("Sep")){
            return true;
        } else if(month.equals("oct") || month.equals("OCT") || month.equals("Oct")){
            return true;
        } else if(month.equals("nov") || month.equals("NOV") || month.equals("Nov")){
            return true;
        } else if(month.equals("dec") || month.equals("DEC") || month.equals("Dec")){
            return true;
        }
        
        return false;            
    }

    /**
     * seperatorChecker method takes in a string and returns true if there is no more than
     * two separators in the date and the separators are the same.
     *
     * @param month, a String date to be entered.
     *
     * @return true if there isnt more than two separators in the date. 
     */
    private static boolean separatorChecker(String str){
        if(str.contains(" ") && str.contains("/")){
            return false;
        } else if(str.contains(" ") && str.contains("-")){
            return false;
        } else if(str.contains("/") && str.contains("-")){
            return false;
        }

        int count = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '/' || str.charAt(i) == '-' ){
                count++;
            }
        }
        if(count > 2){
            return false;
        }

        return true;
    }

    /**
     * dayConverter method takes in a day in the form of a String and returns the day
     * in the correct day format.
     *
     * @param day, a String day to be entered.
     *
     * @return a string in the correct day format.
     */
    private static String dayConverter(String day){
        if(day.length() !=2){
            StringBuilder addZero = new StringBuilder(day);
            char c = day.charAt(0);
            addZero.append(c);
            addZero.setCharAt(0, '0');
            day = addZero.toString();
        }
        return day;
    }

    /**
     * yearConverter method takes in a year in the form of a String and returns the year
     * in the correct day format.
     *
     * @param year, a String year to be entered.
     *
     * @return a string in the correct year format.
     */
    private static String yearConverter(String year){
        // Year has already been checked if it is an int
        int i = Integer.parseInt(year);
                
        if(year.length() == 2){
            if(i > 49){
                i = i + 1900;
            } else{
                i = i + 2000;
            }            
        }
        year = Integer.toString(i);
        return year;
    }

    /**
     * valid method takes in a date in the form of a String and returns true
     * if the date does not have a negative number and there isnt too many separators.
     *
     * @param str, date in the form of a String.
     * @param line, date in the form of a String array. 
     *
     * @return true if the date is in the correct date format.
     */
    private static boolean valid(String str, String[] line){        

        // Checks if the input has too many separators
        if(!separatorChecker(str)){
            System.out.print(stdout + " - INVALID: Only one separator type to be used in per date ");
            System.out.println("(/ & -) and make sure there is not a seperator at the end (12/12/12/).");
            return false;
        }
               
        // Checks to see if the date has a day month and year         
        if(line.length > 3){
            System.out.println(stdout + " - INVALID: reason unknown");
            return false;
        } else if(line.length < 3){
            System.out.println(stdout + " - INVALID: day, month and year is required only as input.");
            return false;
        }
        
        // Checks to see if there are too many spaces in the date.
        int count = 0;
        for(int i=0; i<str.length()-1; i++){
            if(str.charAt(i) == ' '){
                count++;
            }
        }
        if(count > 2){
            System.out.println(stdout + " - INVALID: too many spaces");
            return false;
        }        

        // Checks to see if there is numbers in the date
        count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        if(count < 3){
            System.out.println(stdout + " - INVALID: reason unknown.");
            return false;
        }

        if(str.charAt(0) == '-'){
            System.out.println(stdout + " - INVALID: Cannot have negative numbers in dates");
            return false;
        }
        
        return true;
        
    }

    private static boolean isLeapYear(int y){
        if(y % 100 == 0){
            if(y % 400 == 0){
                return true;
            } else{
                return false;
            }
        } else if(y % 4 == 0){
            return true;
        }

        return false;
    }
    
}
