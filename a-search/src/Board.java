import java.io.*;
import java.util.*;

/*
 * A: punto inicial
 * B: punto final
 * *: obstáculo
 * -: visitado
 */

public class Board {
    private char [][] matrix ;
    private int n, m;

    // Tablero vacío de dimensión nxm
    public Board(int n, int m){
        matrix = new char[n][m];
        n = this.n;
        m = this.m;
    }

    // Tablero leído de fichero
    public Board(String file) throws FileNotFoundException, IOException{

        String line;
        File f = new File(file);
        Scanner sc = new Scanner(f);

        int j = 0;
        n = 10; // longitud primera linea
        m = 10; // numero de lineas
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

    // getters
    protected char[][] getMatrix(){
        return this.matrix;
    }

    protected int getN(){
        return this.n;
    }

    protected int getM(){
        return this.m;
    }

    // Devuelve el elemento (i,j)
    protected char element(int i, int j){
        return this.matrix[i][j];
    }
    
    // Añade una casilla a la ruta
    protected void select(int i, int j){
        matrix[i][j] = '-';
    }

    // Devuelve la posición del punto inicial
    protected int[] first(){
        for (int i=0; i<this.getN(); i++){
            for (int j=0; j<this.getM(); j++){
                if (element(i, j)=='A'){
                    int[] sol = {i, j};
                    return sol;
                }
            }
        }
        return null;
    }

    // Devuelve la posición del punto final
    protected int[] last(){
        for (int i=0; i<this.getN(); i++){
            for (int j=0; j<this.getM(); j++){
                if (element(i, j)=='B'){
                    int[] sol = {i, j};
                    return sol;
                }
            }
        }
        return null;
    }

    protected boolean isFirst(int i, int j){
        return matrix[i][j]=='A';
    }

    protected boolean isLast(int i, int j){
        return matrix[i][j]=='B';
    }
    
    // Indica si una casilla es un obstáculo
    protected boolean isObstacle(int i, int j){
        return matrix[i][j]=='*';
    }

    // Indica si una casilla ha sido visitada
    protected boolean isVisited(int i, int j){
        return matrix[i][j]=='-';
    }

    // Implementación del algoritmo
    public Board solve(Board b, int[] f, int[] l, Set<int[]> frontier){

        int i = f[0];
        int j = f[1];
        boolean ult = false;
        
        // Añado a frontier todas las casillas que puedo visitar
        // (i+1, j)
        if (i+1>=0 && i+1<n && j>=0 && j<m && !b.isFirst(i+1, j) && !b.isObstacle(i+1, j) && !b.isVisited(i+1, j)){
            int[] c = {i+1, j};
            frontier.add(c);

            if(b.isLast(i+1, j)) ult = true;
        }

        // (i-1, j)
        if (i-1>=0 && i-1<n && j>=0 && j<m && !b.isFirst(i-1, j)  && !b.isObstacle(i-1, j) && !b.isVisited(i-1, j)){
            int[] c = {i-1, j};
            frontier.add(c);

            if(b.isLast(i-1, j)) ult = true;
        }

        // (i, j+1)
        if (i>=0 && i<n && j+1>=0 && j+1<m && !b.isFirst(i, j+1) && !b.isObstacle(i, j+1) && !b.isVisited(i, j+1)){
            int[] c = {i, j+1};
            frontier.add(c);

            if(b.isLast(i, j+1)) ult = true;
        }

        // (i, j-1)
        if (i>=0 && i<n && j-1>=0 && j-1<m && !b.isFirst(i, j-1) && !b.isObstacle(i, j-1) && !b.isVisited(i, j-1)){
            int[] c = {i, j-1};
            frontier.add(c);

            if(b.isLast(i, j-1)) ult = true;
        }

        // Si frontier is empty o está B ---> he terminado
        // Sino, exploro el más prometedor
        if(frontier.isEmpty() || ult){
            return b;
        }
        else{ // llamada recursiva
            int[] sig = explore(b, frontier);
            b.select(sig[0], sig[1]);
            return solve(b, sig, l, frontier);
        }
    }
    
    private void printSet(Set<int[]> s){
        Iterator<int[]> it = s.iterator();
        System.out.println("\nSet:");
        while(it.hasNext()){
            int[] c = it.next();
            System.out.println(c[0] + " " + c[1]);
        }
       
    }

    // Función auxiliar que devuelve la casilla más prometedora
    // a la que puedo transitar
    private int[] explore(Board b, Set<int[]> frontier){
        Iterator<int[]> it = frontier.iterator();

        int[] pos = it.next();

        while(it.hasNext()){
            int[] c = it.next();
            int i = c[0];
            int j = c[1];
            if(cost(b, i, j)<cost(b, pos[0], pos[1])){
                pos = c;
            }
        }
        frontier.remove(pos); // Para evitar explorarlo más de una vez, elimino el elemento seleccionado del set
        return pos;
    }

    // Función auxiliar que devuelve el coste estimado de una casilla
    private int cost(Board b, int i, int j){
        int[] p0 = b.first();
        int[] p1 = b.last();

        // Distancia recorrida
        int raiz0 = (int) Math.abs(Math.pow(p0[0]-i, 2)-Math.pow(p0[1]-j, 2));
        int dist0 = (int) Math.sqrt(raiz0);

        // Distancia por recorrer
        int raiz1 = (int) Math.abs(Math.pow(p1[0]-i, 2)-Math.pow(p1[1]-j, 2));
        int dist1 = (int) Math.sqrt(raiz1);

        return dist0+dist1;
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
                else if (isVisited(i, j)){
                    b += YELLOW + "■ " + RESET;
                }
                else{
                    b += " " + matrix[i][j];
                }
            }

            b += "\n";
        }
        return b;
    }
}
