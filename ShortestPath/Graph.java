import java.util.Scanner;

public class Graph {
    int v, e;
    Vertex[] vertices;

    Graph(Scanner inputFile) {
        v = inputFile.nextInt();
        e = inputFile.nextInt();
        vertices = new Vertex[v];
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertex(inputFile.nextInt(), inputFile.nextInt(), inputFile.nextInt());
            inputFile.nextLine();
        }
        inputFile.nextLine();
        for (int i = 0; i < e; i++) {
            vertices[inputFile.nextInt()].edges.add(inputFile.nextInt());
            inputFile.nextLine();
        }
    }

    double distance(int from, int to) {
        return Math.abs(vertices[from].eucDistance(vertices[to]));
    }

}
