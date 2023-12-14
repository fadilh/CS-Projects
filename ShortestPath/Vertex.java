import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable {
    int ID, x, y;
    List<Integer> edges;
    boolean visited;
    double distance;

    Vertex(int ID, int x, int y) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<Integer>();
        this.visited = false;
        this.distance = Double.POSITIVE_INFINITY;
    }

    double eucDistance(Vertex v) {
        return Math.sqrt(Math.pow((v.x = this.x), 2) + Math.pow((v.y = this.y), 2));
    }

    @Override
    public String toString() {
        return "Vertex " + ID + ", at x: " + x + ", y: " + y + ", Edges: " + edges.toString() + ", Distance: "
                + distance;
    }

    @Override
    public int compareTo(Object o) {
        Vertex v = (Vertex) o;
        return Double.compare(distance, v.distance);
    }
}
