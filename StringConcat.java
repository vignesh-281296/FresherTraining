import java.util.Scanner;

/**
 * class for string concat
 *
 * @author  vignesh r
 * @version 1.0 2021-02-26
 */ 
public class StringConcat {

    /**
     * Drive Code
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
                System.out.print("Second String should not be Empty");
            } else {
                System.out.println("Concatenated String is ");
                System.out.print(firstString+secondString);     
              }  
          }                                
    }
}