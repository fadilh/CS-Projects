import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath {
    public static void main(String[] args) {
        try {
            Graph g = new Graph(new Scanner(new File("01 ShortestPath data files/input6.txt")));
            Dijkstra d = new Dijkstra(g);
            d.dijkstra(0, 4);
            for (int i = 0; i < g.vertices.length; i++) {
                System.out.println(g.vertices[i].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}