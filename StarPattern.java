import java.util.Scanner;

/** 
 * class to print the number pattern
 *
 * @author  vignesh r
 * @version 1.0 2021-02-26
 */ 
public class StarPattern {

    /* 
     * Function to print the number pattern
     * 
     * @param row no of row 
     */
    static void printStarPattern(int row) {
        for (int i = 1; i <= row; i++) {                        
            for (int j = 1; j <= i; j++) {                                    
                System.out.print("*");                        
            }
            System.out.println();                            
        }
    }

    /** 
     * Driver code 
     */ 
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the no of rows: ");
        int row = userInput.nextInt();  
        if (row == 0) {
            System.out.print("Row should be greater than zero");
        } else {                     
            printStarPattern(row);
          }                             
    }
}