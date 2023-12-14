import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class HuffmanCompressor {
    public static void compress(String fileName) {
        try {

            int[] counts = new int[256];
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                for (char character : line.toCharArray()) {
                    counts[character]++;
                }
            }
            HuffmanTree ht = new HuffmanTree(counts);
            ht.write(fileName + ".code");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    

    public static void main(String[] args) {
        compress("./Text Files/happy hip hop.txt");

    }
}
