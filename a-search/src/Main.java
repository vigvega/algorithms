import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception, FileNotFoundException, IOException{
        Board b = new Board("map2.txt");
        //System.out.println(b.toString());
        Board bs = b.solve(b, b.first(), b.last(), new HashSet<>());
        System.out.println("\n");
        System.out.println(bs.toString());
       

    }
}
