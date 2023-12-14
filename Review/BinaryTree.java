import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Node<T> { //not an inner class for ease of testing
	T       data;
	Node<T> left;
	Node<T> right;

	public Node(T data) {
		this(data, null, null);
	}

	public Node(T data, Node<T> left, Node<T> right) {
		this.data  = data;
		this.left  = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "" + this.data;
	}
}

public class BinaryTree<T>
{
	private Node<T> overallRoot;
	
	/** Construct an empty tree */
	public BinaryTree() { }

	/** Construct a binary tree given a pre-built tree */
	public BinaryTree(Node<T> overallRoot) {
		this.overallRoot = overallRoot;
	}
	
	public Node<T> getRoot() { return this.overallRoot; }
	
	//*********************************************
	//*********** STUDENT METHODS BELOW ***********
	//*********************************************
	public void reflect() {
		reflect(overallRoot);
	}

	private void reflect(Node node) {
		if (node.left != null) {
			reflect(node.left);
		}
		if (node.right != null) {
			reflect(node.right);
		}

		Node temp = node.left == null ? null : node.left;
		node.left = node.right == null ? null : node.right;
		node.right = temp;
	}


	public Node build(int levels, String s) {
		if (levels < 1) {
			return null;
		} else {
			Node node  = new Node(s);

			node.left = build(levels-1, s);
			node.right = build(levels-1, s);

			return node;
		}
	}

	public void save(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName));
			out.println(save(overallRoot));
			out.close();
		}
		catch (IOException io) {}
	}
	private String save(Node node) {
		if (node == null) {
			return "$\n";
		} else {
			return node.data + "\n" + save(node.left) + save(node.right);
		}
	}

	public Node load(String file) {
		try {
			Scanner scan = new Scanner(new File(file));
			overallRoot = load(scan);
		} catch (Exception e) {
			System.out.println("file not found");
		}
		return overallRoot;
	}
	private Node load(Scanner scan){
		if (scan.hasNextLine()) {
			String currLine = scan.nextLine();
			if (currLine.equals("$")) {
				return null;
			} else {
				Node newNode = new Node(currLine);
				newNode.left = load(scan);
				newNode.right = load(scan);
				return newNode;
			}
		} else {
			return null;
		}
	} 
}
