import java.util.Scanner;

/**
 * class for transpose matrix using 2d array
 *
 * @version 1.0 26-12-2021
 * @author  vignesh r
 */
public class DetermineTranspose {
   
    /** 
     * Function for print determine transpose
     *
     * @param matrix  - 2d array to transpose the element
     * @param row     - no of rows
     * @param column  - no of column    
     */
    public static void printDetermineTranspose(int matrix[][], int row, int column) {        
        for (int i = 0; i < row; i++) {                              
            for (int j = 0; j < column; j++) {                            
                System.out.print(matrix[j][i] + " ");               
            }        
            System.out.println("\n");                             
        }     
    }

    /** 
     * Driver Code 
     */
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter no. of row");
        int row = userInput.nextInt();                               
        System.out.println("Enter no. of column");
        int column = userInput.nextInt();  

        if (row == 0 && column == 0) {
            System.out.print("Row and column value must be greater than zero");
        } else {
            System.out.println("Enter the element of matrix");
            int[][] matrix = new int[row][column];                       
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = userInput.nextInt();                 
                }
            } 
            System.out.println("Determine Transpose Matrix is ");
            printDetermineTranspose(matrix, row, column);            
         }
   }                              
}