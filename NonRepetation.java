import java.util.Scanner;

/**
 * class for non-repetation element using array
 * @author  vignesh r
 * @version 1.0 2021-02-26
 */
public class NonRepetation {
    
    /** 
     * Function for print non repetation element
     * 
     * @param arrayOfNumbers - find the non repetation element
     * @param length         - array length 
     */
    static void printNonRepetation(int arrayOfNumbers[], int length) {         
        for (int i = 0; i < length; i++) {                                    
            int count = 0;
            for (int j = 0; j < length; j++) {                                
                if (i != j && arrayOfNumbers[i] == arrayOfNumbers[j]) {                                 
                    count++;                                               
                }
            }
            if (count == 0) {
                System.out.print(arrayOfNumbers[i]);                                 
            }
        }        
    }

    /** 
     *Drive Code 
     */
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the length of the array:");
        int length = userInput.nextInt();
 
        if (length == 0) {
            System.out.print("Length of arrar should be greater than zero");
         } else {
             int arrayOfNumbers[] = new int[length];                                       
             System.out.println("Enter the elements of the array:");
             for (int i = 0; i < length; i++) {
                 arrayOfNumbers[i] = userInput.nextInt();                                  
             }     
             System.out.println("The non repetation element are");
             printNonRepetation(arrayOfNumbers, length);
        }                                                                         
    }
}