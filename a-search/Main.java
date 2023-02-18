import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception, FileNotFoundException, IOException{

        Board b = new Board("maps/map3.txt");
        System.out.println("\n");
        System.out.println(b.toString());
        b.solve(b, b.first(), b.last(), new HashSet<>(), new HashMap<>());
        System.out.println("\n");
        System.out.println(b.toString());
       
    }
}
