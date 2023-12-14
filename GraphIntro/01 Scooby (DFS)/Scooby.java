import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Scooby
 */
public class Scooby {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("01 Scooby (DFS)/01 Data files/scooby.dat"));
            int count = scanner.nextInt();
            scanner.nextLine();
            while (scanner.hasNext()) {
                boolean found = false;
                String edgesLine = scanner.nextLine();
                List<String> edgesList = Arrays.asList(edgesLine.split(" "));
                Map<Character, List<Character>> list = new HashMap<Character, List<Character>>();
                String wantedEdge = scanner.nextLine();

                if (edgesList.contains(wantedEdge)) {
                    System.out.println("yes");
                    found = true;
                }

                if (!found) {
                    for (String edge : edgesList) {
                        char[] cList = edge.toCharArray();
                        for (int i = 0; i < 2; i++) {
                            List<Character> adj = list.get(cList[i]) != null ? list.get(cList[i]) : new ArrayList<>();
                            adj.add(cList[i == 0 ? 1 : 0]);
                            list.put(cList[i], adj);
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}