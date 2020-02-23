//package hw15;
import java.util.Scanner;

public class Matrix_oop {
    public static void main(String[] args){

        // Define variables for rows and columns selection
        int rows = 0;
        int cols = 0;

        // Create Scanner object
        Scanner input = new Scanner(System.in);

        // Get rows and columns and validate the inputs
        boolean badInputs = false;

        do {
            badInputs = false;
            System.out.println("\nHow many rows in your matrix? (Between 1 and 5 rows).\n");
            rows = input.nextInt();
            
            System.out.println("How many columns in your matrix? (Between 1 and 5 columns).");
            cols = input.nextInt();
            
            if (rows < 1 || rows > 5) {
                badInputs = true;
                System.out.println("\nThe number of rows selected is < 1 or > 5. Please try again.\n");
            }
            if (cols < 1 || cols > 5) {
                badInputs = true;
                System.out.println("\nThe number of columns is < 1 or > 5. Please try again.\n");
            }
        } while (badInputs);
        // Create a matrix object using the Matrix class 
        Matrix m1 = new Matrix(rows, cols);

        // Print the newly created matrix, m1
        System.out.printf("\nOriginal %1d x %1d Matrix: \n", rows, cols);
        m1.printMatrix();

        // Create matrix objects for the transposed, column summed, 
        // and reversed rows results:
        Matrix tmat, cmat, rmat, arMat, anMat;

        // Display menu, get user's menu selection, and call the 
        // appropriate instance method in the Matrix class
        String userInput = " ";
        do {
            printMenu();
            userInput = input.next().toUpperCase();
            System.out.println();

            switch(userInput) {
            case("T"): 
                tmat = m1.transpose();
                tmat.printMatrix();
                break; 
            case("C"): 
                cmat = m1.columnSum();
                cmat.printMatrix();
                break; 
            case("R"): 
                rmat = m1.reverseRows();
                rmat.printMatrix();
                break; 
            case("P"):
            	m1.printMatrix();
            	break;
            case("AR"):
            	arMat = m1.add(m1.reverseRows());
            	arMat.printMatrix();
            	break;
            case("AN"):
                System.out.println("Enter a number to add to each element in the matrix: ");
                int numToAdd = input.nextInt();
                m1.setNum(numToAdd);
                anMat = m1.add();
                anMat.printMatrix();
                break;
            case("Q"): 
                System.out.println("\n  Exiting.\n");
                input.close();
                break;
            default:
                System.out.println("\n  Error, invalid input.");
                break;
            }
        } while (!userInput.equals("Q"));
    }
    
    public static void printMenu() {
        System.out.println("\n");
        System.out.println("______________________________________________________________________\n");
        System.out.println("  T transpose   - Rows become colums (and vice versa)");
        System.out.println("  C columnSum   - Caclulate the sum of the values in each column");
        System.out.println("  R reverseRows - Reverse all elements in every row of the matrix");
        System.out.println("  P printMatrix - Print the original matrix");
       	System.out.println("  AR addReverse - Add reverse of matrix to original matrix");
 		System.out.println("  AN addNum     - Add a number to each element of the original matrix");
        System.out.println("  Q quit        - Exit the program");
        System.out.println("______________________________________________________________________\n");

        System.out.print("\n  Enter: T, C, R, P, AR, AN, or Q =>  ");
    }    
}


class Matrix {
    int[][] mat;  // mat is declared as an instance variable (note no 'static' keyword)
    
    // Declare a private instance variable in your Matrix class
    private int numToAdd = 0; 
    
    //User Input

    // Setter to set the input number to be added to original matrix
    public void setNum(int numToAdd) {
    this.numToAdd = numToAdd;
    } 
    
    // Constructor that initializes the matrix to 0 1 2, then 10 11 12, then 20 21 22, ...
    Matrix(int row, int column) {
        mat = new int [row][column];

        //Initialize mat
        for (int i = 0, rowInitializer = 0; i < row; i++, rowInitializer += 10) {
            for (int j = 0, columnInitializer = rowInitializer; j < column; j++, columnInitializer ++) {
                mat[i][j] = columnInitializer;
            }
        }        
    }
    
    // Constructor that initializes the entire matrix to the value passed in.
    Matrix(int row, int column, int value) {
        mat = new int [row][column];

        // Initialize mat with value
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                mat[i][j] = value;
            }
        }        
    }

    void printMatrix() {
        // Print out matrix mat
        for (int i = 0; i < mat.length; i++) {
            System.out.println();
            for (int j = 0; j < mat[i].length ; j++) {
                System.out.printf("%5d", mat[i][j]);
            }
        }        
    }

    Matrix transpose() {
        // Create the matrix, mtran, that will hold the results
        Matrix mtran = new Matrix(mat[0].length, mat.length);

        // Transpose matrix mat into matrix mtran and return mtran
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length ; j++) {
                mtran.mat[i][j] = mat[j][i];
            }
        }
        return mtran;        
    }

    Matrix columnSum() {
        // Create the matrix, mcolsum, that will hold the results-this matrix has 1 row
        // Initialize each element of mcolsum to 0 using the appropriate constructor
        Matrix mcolsum = new Matrix(1, mat[0].length, 0);

        // Sum the columns of matrix mat into matrix mcolsum and return mcolsum
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mcolsum.mat[0][i] = mcolsum.mat[0][i] + mat[j][i];
            }
        }
        return mcolsum;        
    }

    Matrix reverseRows() {
        // Create the matrix, mrev, that will hold the results
        Matrix mrev = new Matrix(mat.length, mat[0].length);

        // Reverse the rows of matrix mat into matrix mrev and return mrev
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length ; j++) {
                mrev.mat[i][j] = mat[i][mat[0].length - 1 - j];
            }
        }
        return mrev;      
    }

    Matrix add(Matrix mat) {
    	 Matrix sum = new Matrix(mat.mat.length, mat.mat[0].length);
 		// TODO: New code to add two matrices goes here
        for (int i = 0; i < mat.mat.length; i++) {
            for (int j = 0; j < mat.mat[i].length ; j++) {
            sum.mat[i][j] = this.mat[i][j] + mat.mat[i][j];
        }
    }
 		// Use instance method printMatrix() to print the result

 		return sum;	

    }

   Matrix add() {
    Matrix inc = new Matrix(mat.length, mat[0].length);
    // TODO: New code to add numToAdd to each element in the matrix goes here
    for (int i = 0; i < mat.length; i++) {
        for (int j = 0; j < mat[i].length ; j++) {
            inc.mat[i][j] = mat[i][j] + numToAdd;
        }
    }

    // Use instance method printMatrix() to print the result
    return inc;


    }


}
