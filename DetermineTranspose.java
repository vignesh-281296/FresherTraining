import java.util.Scanner;

/*
 * class for transpose matrix
 * using 2d array
 */
public class DetermineTranspose {
    
    /* 
     * Function for print 
     * determine transpose 
     */
    public static void printDetermineTranspose(int array[][], int row, int col) {
        
        for(int i=0; i < row; i++) {                              
            for(int j=0; j < col; j++) {                            
               System.out.print(array[j][i] + " ");               
            }        
            System.out.println("\n");                             
        }     
    }

   /* 
    * Driver Code 
    */
   public static void main(String args[]) {

       Scanner userInput = new Scanner(System.in);
       System.out.println("Enter no. of rows");
       int rows = userInput.nextInt();                               
       System.out.println("Enter no. of column");
       int columns = userInput.nextInt();  
       if(rows == 0 && columns == 0) {
          System.out.print("Row and column value must be greater than zero");
       } else {
           System.out.println("Enter the element of matrix");
           int[][] matrix = new int[rows][columns];                       
           for(int i = 0; i < rows; i++) {
              for(int j = 0; j < columns; j++) {
                  matrix[i][j] = userInput.nextInt();                 
              }
           }   
           System.out.println("Determine Transpose Matrix is ");
           printDetermineTranspose(matrix, rows, columns);            
         }
   }                              
}