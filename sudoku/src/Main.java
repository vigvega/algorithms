import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        int [][] s1 = {{0, 1, 6, 0, 0, 0, 2, 0, 7},
        {0, 2, 0, 6, 0, 0, 0, 8, 4},
        {0, 9, 0, 0, 8, 0, 6, 0, 3},
        {0, 0, 0, 0, 6, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 9, 4, 0},
        {0, 7, 1, 0, 4, 0, 3, 2, 0},
        {3, 0, 0, 5, 0, 0, 0, 0, 0},
        {0, 4, 8, 0, 0, 7, 0, 0, 9},
        {1, 5, 7, 4, 9, 6, 8, 3, 0}};

        
        int [][] s2 = {{0,4,0,0,0,0,3,6,2},
        {6, 3, 0, 9, 4, 1, 0, 0, 0},
        {5, 0, 7, 0, 3, 0, 0, 0, 0},
        {0, 9, 0, 3, 7, 5, 1, 0, 0},
        {3, 0, 4, 8, 0, 0, 0, 0, 0},
        {1, 7, 0, 0, 6, 2, 0, 0, 0},
        {7, 1, 6, 0, 9, 0, 0, 2, 0},
        {0, 0, 9, 6, 0, 0, 0, 0, 0},
        {0, 0, 3, 1, 2, 0, 0, 9, 0}};

        Sudoku s = new Sudoku(s1);
        //System.out.println(s.toString());
        List<Sudoku> l = s.solveAll();
        for (Sudoku sudoku : l) {
            System.out.println(sudoku.toString());
        }
        //System.out.println(s.toString());
    }
}
