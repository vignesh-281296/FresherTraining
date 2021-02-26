import java.util.Scanner;

/**
 * class for add two matrix using 2d array
 * 
 * @author  vignesh r
 * @version 1.0 2021-02-26
 */ 
public class MatrixAddition {
 
    /** 
     * Function to print Matrix
     * 
     * @param matrix 2d array to print the result matrix element
     * @param rowsize no of row
     * @param columnsize no of column 
     */
    static void printMatrix(int matrixC[][], int rowSize, int columnSize) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(matrixC[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
 
    /** 
     * Function to add the two matrices and store in matrix C
     *
     * @param matrixA 2d array for matrixA
     * @param matrixB 2d array for matrixB
     */
    static int[][] add(int matrixA[][], int matrixB[][],
                       int row, int column) {        
        int matrixC[][] = new int[row][column];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];             // Adding two Matrix A and B and store in Matrix C
                return matrixC;
    }
 
    /** 
     *Driver code 
     */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter no. of row");
        int row = userInput.nextInt();                             
        System.out.println("Enter no. of column");
        int column = userInput.nextInt();   
        if (row == 0 && column == 0) {
            System.out.print("Rows and column should be greater than zero");
        } else {
            int i,j;
            System.out.println("Enter the element of matrix A");
            int[][] matrixA = new int[row][column];                      
            for (i = 0; i < row; i++) {
                for (j = 0; j < column; j++) {
                    matrixA[i][j] = userInput.nextInt();                 
                }
            }
            System.out.println("Enter the element of matrix B");
            int[][] matrixB = new int[row][column];                    
            for (i = 0; i < row; i++) {
                for (j = 0; j < column; j++) {
                    matrixB[i][j] = userInput.nextInt();                 
                 }
            }
            System.out.println("\nMatrix A:");
            printMatrix(matrixA, row, column);                         
            System.out.println("\nMatrix B:");
            printMatrix(matrixB, row, column);                                               
            int matrixC[][] = add(matrixA, matrixB, row,column);                     
            System.out.println("\nResultant Matrix:");                  
            printMatrix(matrixC, row, column);
          }                           
    }
}