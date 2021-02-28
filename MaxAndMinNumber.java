import java.util.Scanner;

/**
 * class to find largest and smallest Element using array
 * 
 * @author  vignesh r
 * @version 1.0 2021-02-26
 */
public class MaxAndMinNumber {   
  
    /* 
     * Method to find maximum in arrayOfNumbers[] 
     * 
     * @param  arrayOfNumbers -  find max element
     * @return maximum element in arrayOfNumbers
     */
    static int largest(int arrayOfNumbers[]) {
        int i;    
        int max = arrayOfNumbers[0];                                 // Considering first element has maximum
       
        /*
         * Traverse array elements from second and
         * compare every element with current max  
         */
         for (i = 1; i < arrayOfNumbers.length; i++)
             if (arrayOfNumbers[i] > max)
                 max = arrayOfNumbers[i];
                 return max;
    }

    /* 
     * Method to find maximum in arrayOfNumbers[] 
     *
     * @param  arrayOfNumbers - find min element
     * @return return maximum element in arrayOfNumbers 
     */
    static int smallest(int arrayOfNumbers[]) {
        int i;  
        int min = arrayOfNumbers[0];                                        // Considering first element has minimum
       
        /*
         * Traverse array elements from second and
         * compare every element with current max  
         */
        for (i = 1; i < arrayOfNumbers.length; i++)
            if (arrayOfNumbers[i] < min)
                min = arrayOfNumbers[i];
                return min;
        }
      
    /* 
     * Driver method 
     */
    public static void main(String[] args) {       
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the length of the array:");
        int length = userInput.nextInt();                                                            
        int arrayOfNumbers[] = new int[length];                                      
        int i; 

        if (length == 0) {
            System.out.print("Array length should be greater than zero");
        } else {
            System.out.println("Enter the elements of the array:");
            for (i = 0; i < length; i++) {                                                                        
                arrayOfNumbers[i] = userInput.nextInt();
            }
            System.out.println("Largest in given array is " + largest(arrayOfNumbers));   
            System.out.print("Smallest in given array is " + smallest(arrayOfNumbers));     
          }     
     }
 }
