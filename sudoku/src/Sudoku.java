import java.util.*;

public class Sudoku {
    private int [][] sudoku;
    private final int F = 9;
    private final int C = 9;
    
    // Tablero de dimensión 9x9
    public Sudoku(int [][] s){
        sudoku = s;
    }

    // Devuelve si la celda está libre
	private boolean gap(int f, int c) {
		return sudoku[f][c] == 0;
	}
	
    // Devuelve si el valor está en la fila
	private boolean inRow(int fila, int valor) {
		boolean encontrado = false;
		for (int i = 0; i<F && !encontrado; i++) {
			if (sudoku[fila][i] == valor)
				encontrado = true;
		}
		return encontrado;
	}    

    // Devuelve si el valor está en la columna
	private boolean inColumn(int columna, int valor) {
		boolean encontrado = false;
		for (int i = 0; i<C && !encontrado; i++) {
			if (sudoku[i][columna] == valor)
				encontrado = true;
		}
		return encontrado;
	}

    // Devuelve si el valor está en el subtablero indicado
	private boolean inBoard(int fila, int columna, int valor) {
		boolean encontrado = false;
		for (int i = (3*(fila/3)); i<(3*( 1 + (fila/3))) && !encontrado; i++) {
			for (int j = (3*(columna/3)); j<(3*( 1 + (columna/3))) && !encontrado; j++) {
				if(sudoku[i][j] == valor)
					encontrado = true;
			}
		}
		return encontrado;		
	}    

    // Devuelve true si se puede añadir un valor en una posición concreta
	private boolean addIn(int fila, int columna, int valor) {
		return !inRow(fila, valor) && !inColumn(columna, valor) && !inBoard(fila, columna, valor);
	}
	
	// Funcion auxiliar que me devuelve la primera celda vacía del tablero
    private int[] firstGap(int f, int c) {
        int[] vacia = new int[2];
        while (f < F) {
            if (gap(f, c)) {
                vacia[0] = f;
                vacia[1] = c;
                return vacia;
            }
            c++;
                
            // esto es porque a partir de la segunda columna
            // hay que recorrer desde el principio
            if (c == C) {
                c = 0;
                f++;
            }
        }
        return null;
    }
	

    public void solve(List<Sudoku> soluciones, int fila, int columna) {
            
        // Busco la primera celda vacia
        int[] vacia = firstGap(fila,columna);

        // Si están todos los huecos ocupados, he encontrado una solución
        if (vacia==null) {
            System.out.println(new Sudoku(sudoku).toString()+"\n");
            //soluciones.add(new Sudoku(sudoku));
        }

        // De lo contrario, meto el primer valor que sea valido
        else {
            for (int j = 1; j<10; j++) {
    
                if (addIn(vacia[0], vacia[1], j)) {
                    sudoku[vacia[0]][vacia[1]] = j;
                    solve(soluciones, vacia[0], vacia[1]); // llamada recursiva
                }
            }
            // backtracking por si no era una opción válida
            sudoku[vacia[0]][vacia[1]] = 0;
        }
                
    }

    // Representación del tablero
    @Override
    public String toString(){
        String b = "";

        for (int i=0; i<F; i++){
            b += " ";

            for(int j=0; j<C; j++){
                if (j%3==0 && j!=0)
                    b += "| ";
                b += sudoku[i][j] + "  ";
                
            }

            if ((i+1)%3==0 && i!=F-1)
                b += "\n----------+----------+---------\n";
            else
                b += "\n\n";
        }
        return b;
    }
}
