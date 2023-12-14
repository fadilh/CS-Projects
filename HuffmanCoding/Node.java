public class Node implements Comparable<Node> {
    char value;
    int frequency;
    Node right, left;

    public Node(char value) {
        this.value = value;
    }

    public Node(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
        this.left = this.right = null;
    }

    public Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.frequency - o.frequency;
    }

    public String toString() {
        return "" + value;
    }
}