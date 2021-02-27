import java.util.Scanner;

/**
 * class for find diagonal element using 2d array
 *
 * @author vignesh r
 * @version 1.0 2021-02-26
 */ 
public class DiagonalElement {
   
    /** 
     * Function for print diagonal Element
     *
     * @param matrix -  find diagonal elements 
     * @param row    - no of row
     * @param column - no of column 
     */
    public static void printDiagonalElement (int matrix[][], int row, int column) {
         for (int i = 0; i < row; i++) {                             
             for (int j = 0; j < column; j++) {                                       
                if (i == j) {                                                          
                    System.out.println(matrix[i][j] + " ");                           
                }
            }             
        }    
    }

    /** 
     * Driver Code 
     */
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter no. of row");
        int row = userInput.nextInt();                            
        System.out.println("Enter no. of Column");
        int column = userInput.nextInt(); 
 
        if (row == 0 && column == 0) {
            System.out.print("Row and Column value should be greater than zero");
        } else {
            System.out.println("Enter the element of matrix");
            int[][] matrix = new int[row][column];                       
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = userInput.nextInt();                 
                }
             }   
             System.out.println("Diagonal Element in Matrix are ");
             printDiagonalElement(matrix, row, column);
         }                                      
    }
}