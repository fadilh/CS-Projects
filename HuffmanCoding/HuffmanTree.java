import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class HuffmanTree {
    HashMap<Integer, String> map = new HashMap<Integer, String>();
    Node root;

    public HuffmanTree(int[] counts) {
        buildTree(counts);
    }

    private void buildTree(int[] counts) {
        Queue<Node> priorityQueue = new PriorityQueue<>(Comparable::compareTo);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                priorityQueue.add(new Node((char) i, counts[i]));
            }
        }
        priorityQueue.add(new Node((char) 256, 0));
        int i = 0;
        while (priorityQueue.size() > 1) {
            Node leftNode = priorityQueue.poll();
            Node rightNode = priorityQueue.poll();
            Node newNode = new Node((char) i, leftNode.frequency + rightNode.frequency);

            newNode.right = rightNode;
            newNode.left = leftNode;

            priorityQueue.add(newNode);
            i++;
        }
        root = priorityQueue.poll();

        TreePrinter.printTree(root);
    }

    public HuffmanTree(String codeFile) throws IOException {
        try (Scanner scanner = new Scanner(new File(codeFile))) {
            while (scanner.hasNext()) {
                String treeString = scanner.nextLine();
                Stack<Node> stack = new Stack<>();
                for (int i = 0; i < treeString.length(); i++) {
                    char c = treeString.charAt(i);
                    if (c == '1') {
                        Node right = stack.pop();
                        Node left = stack.pop();
                        stack.push(new Node('\0', left, right));
                    } else if (c != '0') {
                        stack.push(new Node(c));
                    }
                }
                root = stack.pop();
            }
        }
    }

    public void write(String fileName) {
        writeFile("", root);
        System.out.println(map.toString());
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            map.forEach((key, value) -> {
                pw.println(key);
                pw.println(value);
            });
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String s, Node n) {
        if (n.left != null) {
            writeFile(s + "0", n.left);
            writeFile(s + "1", n.right);
        } else {
            map.put((int) n.value, s);
        }
    }

    public void encode(BitOutputStream out, String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useDelimiter("");
            while (scanner.hasNext()) {
                String s = map.get(scanner.next());
                for (char c : s.toCharArray()) {
                    out.writeBit(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void decode(BitInputStream in, String outFile) {
        try (PrintWriter pw = new PrintWriter(new File(outFile))) {
            Node curNode = root;
            while (curNode.value != (char) 256) {
                int curBit = in.readBit();
                curNode = curBit == 1 ? curNode.right : curNode.left;
                pw.write(curNode.value);
                curNode = root;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void expand(String codeFile, String fileName) {
        BitInputStream in = new BitInputStream(codeFile);
        decode(in, fileName);
    }
}