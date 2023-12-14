import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{
	private class Node {
		String text;
        Node left, right;
        public Node(String text) {
            this.text = text;
            left = right = null;
        }

        @Override
        public String toString() {
            return this.text;
        }
	}


	Node root, current;
	String fileName;
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	public GameTree(String fileName)
	{
		try {
			Scanner file = new Scanner(new File(fileName));
			root = setTree(file);
			current = root;
			this.fileName = fileName;
		} catch (Exception e) {
			System.out.println("file not found");
		}
	}

	private Node setTree(Scanner scanner) {
		if (scanner.hasNext()) {
			Node temp = new Node(scanner.nextLine());
			if (temp.text.endsWith("?")) {
				temp.left = setTree(scanner);
				temp.right = setTree(scanner);
			}
			return temp;
		}
		else {return null;}
	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA)
	{
		boolean isLeftAns = !current.left.text.endsWith("?");
		String oldAns = current.left.text != null && isLeftAns ? current.left.text : isLeftAns && current.left == null ? "" : current.right.text;
		Node temp = new Node(newQ); 
		temp.left = new Node(newA);
		temp.right = new Node(oldAns);
		if (isLeftAns) { current.left = temp; } 
		else {  current.right = temp; }

		
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		// return !getCurrent().endsWith("?");
		return current.left == null && current.right == null;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
		return current.text;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if (yesOrNo == Choice.No) {
			current = current.right;
		} else {
			current = current.left;
		}
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		current = root;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		print(builder, root, 0);
		return builder.toString();
	}

	private void print(StringBuilder builder, Node node, int level) {
		if (node != null) {
			print(builder, node.right, level + 1);
			String space = "";
			for (int i = 0; i < level; i++) {
				space += "- ";
			}
			builder.append(space + node.text);
			builder.append("\n");
			print(builder, node.left, level + 1);
		}
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame()
	{
		try {
			PrintWriter file = new PrintWriter(fileName);
			Stack<Node> nodes = new Stack<>();
			nodes.add(root);
			while(!nodes.isEmpty()) {
				Node r = nodes.pop();
				file.write(r.text + "\n");
				if(r.right != null) { nodes.add(r.right); }
				if(r.left != null) { nodes.add(r.left); }
			}
			file.close();

		} catch (Exception e) {
			System.out.println("Could not create file");
		}
	}
}
