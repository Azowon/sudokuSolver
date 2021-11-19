import java.util.Arrays;

public class SudokuSolver {

    static int counter = 0;
    public static void main(String[] args) {


        int[][] matrix = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int u = 0; u < 9; u++) {
                matrix[u][i] = 0;
            }
        }

        int[][] matrix2 = new int[][] {
                {0,0,5,7,8,6,9,2,4},
                {0,0,2,4,5,9,0,0,8},
                {0,0,9,0,2,3,0,7,0},
                {0,0,0,0,0,0,0,0,0},
                {7,2,0,5,4,0,1,0,3},
                {0,0,0,0,0,2,0,0,5},
                {0,0,4,6,0,5,8,0,1},
                {1,6,0,0,0,0,0,5,9},
                {0,5,0,0,0,1,4,6,7},
        };

        new SudokuSolver().solve(matrix2,0,0);

    }

    boolean solve(int[][] sudoku, int x, int y) {


        if (y == 9) {
            if(x ==8) {
                System.out.println("Solved!");
                System.out.println("number of tries: "+counter);
                print(sudoku);
                return true;
            }
            y = 0;
            x++;
        }

        if(sudoku[x][y] != 0) {
            return solve(sudoku,x,y+1);
        }

        for (int i = 1; i <= 9; i++) {
            if (isValid(sudoku, x, y, i)) {
                counter++;
                int[][] copy = deepCopy(sudoku);
                copy[x][y] = i;
                if (solve(copy, x,y+1)) {
                    return true;
                }
            }
        }
        return false;

    }

    void print(int[][] sudoku) {
        System.out.println(Arrays.deepToString(sudoku));

    }

    boolean isValid(int[][] sudoku, int x, int y, int number) {

        for(int[] i : sudoku) {
            if(i[y] == number) return false;
        }
        for(int i : sudoku[x]) {
            if(i == number) return false;
        }
        x = (x/3)*3;
        y = (y/3)*3;
        for(int i = x; i<=x+2; i++) {
            for(int u = y; u<=y+2; u++) {
                if(sudoku[i][u] == number) return false;
            }
        }
        return true;
    }

    public int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

}