import java.util.*;

/**
 * An implementation of a of a program that takes words from stdin
 * counts the syllables of that word and puts the counted output
 * to stdout.
 *
 * @authors Jacob Gear, Sayyed Latif, Timothy Varsanyi, Samuel Ng
 */
public class SyllableSlam{

    /**
     * Main method that calls the method which holds the program
     * @param args command line arguments are not used.
     */
    public static void main(String[]args){
        syllableCounter();
    }

 /**
     * syllableCounter method takes inputted words from stdin and
     * puts them into a scanner, then counts the syllables of each
     * words and displays the count to stdout.
     */
    public static void syllableCounter(){

        int count = 0;
        Scanner line = new Scanner(System.in);
        String wrd= "";
        // While there are words being inputted
        while(line.hasNextLine()){
            Scanner word = new Scanner(line.nextLine());
            while(word.hasNext()){
                wrd = word.next();
                for(int i=0; i<wrd.length(); i++){
                    // first if is so we dont go past the end of the word
                    if(isVowel(wrd.charAt(wrd.length()-1)) && i == wrd.length()-1){
                        count++;
                    // if character i is a vowel and the one after is a constant up the count
                    } else if(isVowel(wrd.charAt(i)) && !isVowel(wrd.charAt(i+1)) ){
                        count++;
                    }

                }
                // e's are silent at the end of words so decrement the count
                if(wrd.length() > 1 && wrd.charAt(wrd.length()-1) == 'e'){
                    count--;
                }
                // if a word ends in "ed" and has a t or d the character before decrement the count
                if( wrd.length() > 1 && wrd.charAt(wrd.length()-2) == 'e' && wrd.charAt(wrd.length()-1) == 'd'){
                    if(wrd.charAt(wrd.length()-3) == 'e')
                        continue;
                    else  if(wrd.charAt(wrd.length()-3) != 'd' || wrd.charAt(wrd.length()-3) != 't' )
                        count--;
                }
                // if a word ends in e and has an 'l' infront of it increment the count
                if(wrd.length() > 1 && wrd.charAt(wrd.length()-2) == 'l' && wrd.charAt(wrd.length()-1) == 'e'){
                    count++;
                }
                // if a word ends in "ing" and has a vowel the character before increment the count
		if(wrd.length() >= 4 && wrd.substring(wrd.length()-4, wrd.length()).equals("ing")){
                    count++;
                }

            }
            System.out.println(count);
            count = 0;
        }
        if(count == 0){
            count = 1;
        }

    }

	/**
     * Method that checks if a letter is a vowel.
     *
     * @param c takes a character as a parameter.
     * @returns a boolean to see if that character is
     * a vowel or not.
     */
    public static boolean isVowel(char c){
        if(c == 'A' || c == 'a'){
            return true;
        } else if(c == 'E' || c == 'e'){
            return true;
        } else if(c == 'I' || c == 'i'){
            return true;
        } else if(c == 'O' || c == 'o'){
            return true;
        } else if(c == 'U' || c == 'u'){
            return true;
        } else if(c == 'Y' || c == 'y'){
            return true;
        }
        return false;
    }


}


