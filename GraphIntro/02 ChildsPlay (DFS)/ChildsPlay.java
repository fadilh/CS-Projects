import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChildsPlay {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("02 ChildsPlay (DFS)/01 Data files/play.dat"));
        int testCases = scanner.nextInt();

        while (testCases > 0) {
            int numDominos = scanner.nextInt();
            int numEdges = scanner.nextInt();
            int numDominosFallen = scanner.nextInt();

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= numDominos; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < numEdges; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                graph.get(x).add(y);
            }

            boolean[] knockedByHand = new boolean[numDominos + 1];
            for (int i = 0; i < numDominosFallen; i++) {
                int z = scanner.nextInt();
                knockedByHand[z] = true;
            }

            int count = 0;
            boolean[] knockedDown = new boolean[numDominos + 1];
            for (int i = 1; i <= numDominos; i++) {
                if (knockedByHand[i]) {
                    count++;
                    knockedDown[i] = true;
                    knockDown(i, graph, knockedDown);
                }
            }
            System.out.println(count);

            testCases--;
        }
    }

    private static void knockDown(int domino, List<List<Integer>> graph, boolean[] knockedDown) {
        for (int neighbor : graph.get(domino)) {
            if (!knockedDown[neighbor]) {
                knockedDown[neighbor] = true;
                knockDown(neighbor, graph, knockedDown);
            }
        }
    }
}
