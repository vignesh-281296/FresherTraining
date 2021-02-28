import java.util.Scanner;

/**
 * class to compare two strings
 *
 * @author  vignesh r
 * @version 1.0 2021-02-26  
 */   
public class CompareString {
    
    /** 
     *Function to print string compare
     *
     * @param firstString        - to compare with second string
     * @param secondString       - delete the matching string with firststring
     * @param firstStringLength  - length of first string
     * @param secondStringLength - length of second string
     */  
    static void comparingTwoString(String firstString, String secondString, 
                                   int firstStringLength, int secondStringLength) {           
        for (int i = 0; i < secondStringLength; i++) {                                                
            int count = 0;
            for (int j = 0; j < firstStringLength; j++) {                                              
                if (secondString.charAt(i) == firstString.charAt(j)) {                              
                    count = 1;
                }
            }
            if (count == 0) {
                System.out.print(secondString.charAt(i));                                                                                       
            }
        }
    }        
    
    /** 
     *Drive code
     */
    public static void main(String args[]) {
      String firstString;                                                                          
      String secondString;                                                                         
      Scanner userInput = new Scanner(System.in);
      System.out.println("Enter the first string");
      firstString = userInput.nextLine();
 
      if (firstString.isEmpty()) {
          System.out.print("First string should not be empty");
      } else {
          System.out.println("Enter the second string");
          secondString = userInput.nextLine();                                                         
          if (secondString.isEmpty()) {
              System.out.print("Second string should not be empty");
          } else{
              int firstStringLength = firstString.length();                                                
              int secondStringLength = secondString.length();                                              
              System.out.print("Unique String: ");
              comparingTwoString(firstString, secondString, firstStringLength, secondStringLength);
            }      
        }                                                                               
    }
}