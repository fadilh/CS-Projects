import java.util.PriorityQueue;

public class Dijkstra {
    Graph graph;

    Dijkstra(Graph graph) {
        this.graph = graph;
    }

    void dijkstra(int src, int dest) {
        graph.vertices[src].distance = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        pq.add(graph.vertices[src]);

        while (!pq.isEmpty()) {
            Vertex curr = pq.poll();
            if (curr.visited) {
                continue;
            }
            curr.visited = true;

            for (int edge : curr.edges) {
                Vertex next = graph.vertices[edge];
                double dist = curr.distance + graph.distance(curr.ID, next.ID);

                if (dist < next.distance) {
                    next.distance = dist;
                    pq.add(next);
                }
            }
        }

    }
}
