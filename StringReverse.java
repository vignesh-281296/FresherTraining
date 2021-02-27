import java.util.Scanner;

/**
 * class for string reverse
 *
 * @author  vignesh r
 * @version 1.0 2021-02-26 
 */   
public class StringReverse {
    
    /* 
     * Function to reverse string
     *
     * @param str    - for reverse the string
     * @param length - string length   
     */ 
    static void printReverseString(String str, int length) {     
        for(int i = length; i > 0; i--) {
            System.out.print(str.charAt(i-1));                     
        } 
    }
  
    /**
     * Driver Code
     */ 
    public static void main(String args[]) {                                                
        Scanner userInput = new Scanner(System.in);
        String str;                                               
        System.out.println("Enter your string: ");
        str = userInput.nextLine();                               
        int length = str.length();
        if (!str.isEmpty()) {
            printReverseString(str, length);
        }                                                           
    }
}