import java.io.*;
import java.util.Scanner;

/*
 * A: punto inicial
 * B: punto final
 * *: obstáculo
 * -: visitado
 */


public class Board {
    private char [][] matrix ;

    // Tablero vacío de dimensión nxm
    public Board(int n, int m){
        matrix = new char[n][m];
    }

    // Tablero leído de fichero
    public Board(String file) throws FileNotFoundException, IOException{

        String line; 
        File f = new File(file);
        Scanner sc = new Scanner(f);

        int j = 0;
        int n = 10; // longitud primera linea
        int m = 10; // numero de lineas
        matrix = new char[n][m];

        while(sc.hasNext()) { 
            line = sc.nextLine();

            for (int i=0; i<n; i++){
                matrix[j][i] = line.charAt(i);
            }
            j++;
        } 
        sc.close();     
    }

    // Devuelve el elemento (i,j)
    public char elemento(int i, int j){
        return matrix[i][j];
    }
    
    // Devuelve su dimension
    public int sizeBoard(){
        return matrix.length;
    }

    // Añade punto de partida
    public void start(int i, int j){
        matrix[i][j] = 'A';
    }

    // Añade meta
    public void end(int i, int j){
        matrix[i][j] = 'B';
    }

    // Añade una casilla a la ruta
    public void select(int i, int j){
        matrix[i][j] = '-';
    }

    // Añade obstáculo
    public void obstacle(int i, int j){
        matrix[i][j] = '*';
    }

    // Indica si una casilla es un obstáculo
    public boolean isObstacle(int i, int j){
        return matrix[i][j]=='*';
    }

    // Indica si una casilla ha sido visitada
    public boolean isVisited(int i, int j){
        return matrix[i][j]=='-';
    }

    // Copia de mi tablero
    private Board copyOf(Board b){
        Board copy = b;
        return copy;
    }

    // Implementación de mi algoritmo
    public Board solve(){
        Board b = copyOf(this);
        
        return b;
    }

    // Representación del tablero
    @Override
    public String toString(){
        // Colores
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String RESET = "\u001B[0m";
        String b = "";

        for (int i=0; i<10; i++){
            b += " ";
            for(int j=0; j<10; j++){

                if (matrix[i][j]=='A' || matrix[i][j]=='B'){
                    b += GREEN + "◉ " + RESET;
                }
                else if (isObstacle(i, j)){
                    b += "■ ";
                }
                else{
                    b += " " + matrix[i][j];
                }
            }

            System.out.println("\n");
            b += "\n";
        }
        return b;
    }
}
