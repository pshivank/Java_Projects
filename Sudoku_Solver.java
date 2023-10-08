import java.util.*;
public class Sudoku_Solver {

    // Function for printing matrix 
    public static void print(int mat[][]){
        for( int s = 0 ; s < mat.length ; s++){
            for( int a = 0 ; a < mat.length ; a++){
                System.out.print(mat[s][a] + " ");
            }
            System.out.println();
        }
    }

    //Function for checking if the no. is already present at that row , column or grid
    public static boolean isSafe(int Sudoku[][] , int row , int col , int digit){
        // Checking in row 
        for(int c = 0 ; c < Sudoku.length ; c++){
            if(Sudoku[row][col] == digit){
                return false;
            }
        }

        // Checking in column 
        for( int r = 0 ; r < Sudoku.length ; r++ ){
            if(Sudoku[r][col] == digit){
                return false;
            }
        }

        // Checking in that particular 3x3 grid in which we are putting our no.
        int sr = (row/3) * 3 ;
        int sc = (col / 3) * 3;
        for(int r = sr ; r < sr+3 ; r++){
            for(int c = sc ; c < sc+3 ; c++){
                if(Sudoku[r][c] == digit){
                    return false;
                }
            }
        }
        return true;
    }


    // Function for Soving our Sudoku 
    public static boolean Solve_sudoku(int Sudoku[][] , int row , int col){
        if( row == 9 ){
            return true;
        }

        int nextRow = row , nextCol = col + 1 ;

        if( nextCol == 9){
            nextRow++;
            nextCol = 0 ;
        }


        // Checking if data is already present 
        if( Sudoku[row][col] != 0 ){
            return Solve_sudoku(Sudoku, nextRow, nextCol);
        }

        for(int digit = 1 ; digit <= 9 ; digit++){
            if(isSafe(Sudoku , row , col , digit)){
                Sudoku[row][col] = digit;
                if(Solve_sudoku(Sudoku, nextRow, nextCol)){
                    return true;
                }
                Sudoku[nextRow][nextCol] = 0 ;
            }
        }
        return false;

    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter dimensions of Your Sudoku  :--  ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println();

        // intitializing our 9x9 matrix for sudoku
        int Sudoku[][] = new int[n][m];
        
        // taking data for Sudoku
        for( int s = 0 ; s < n ; s++ ){
            for( int a = 0 ; a < m ; a++ ){
                Sudoku[s][a] = sc.nextInt();
            }
        }

        System.out.println(" ----------- Your Sudoku Initially --------------");
        print(Sudoku);

        System.out.println(" ------------- Your Sudoku After Solving ------------");
        Solve_sudoku(Sudoku , 0 , 0 );
        print(Sudoku);


    }
    
}
