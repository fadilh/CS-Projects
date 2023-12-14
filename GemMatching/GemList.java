import java.util.Queue;
import java.util.Stack;

public class GemList 
{	
	private class Node{
		private Gem gem;
		private Node next;
		public Node(Gem gem) { 
			this.gem = gem; 
		}
	}
	private Node head;

	public int size() {
		Node current = head;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	public void draw(double y){
		Node current = head;
		int x = 0;
		while (current != null) {
			current.gem.draw(GemGame.indexToX(x), y);
			x++;
			current = current.next;
		}
	}

	public String toString() {
		Node current = head;
		String output = "[";
		while (current != null) {
			output += current.gem.getType() + ": " + current.gem.getPoints() + (current.next == null ? "" : ", ");
			current = current.next;
		}
		output += "]";
		return output;
	}

	public void insertBefore(Gem gem, int index) {
		if (index > size()) {
			if (head == null) {
				head = new Node(gem);
			} else {
				Node current = this.head;
				while (current.next != null) {
					current = current.next;
				}
				current.next = new Node(gem);
			}
		} else if (index == 0) {
			Node newNode = new Node(gem);
			newNode.next = this.head;
			this.head = newNode;
		} else {
			Node current = this.head;
            int i = 0;
            while (i != index - 1) {
                i++;
                current = current.next;
            }
            Node newNode = new Node(gem);
            newNode.next = current.next;
            current.next = newNode;
		}
	}

	public int score(){
		if (head == null) {return 0;}
		Node current = this.head;
		GemType streakType = null;
		int score = 0, streakCount = 0, streakPoints = 0;
		while (current != null) {
			if (streakType == current.gem.getType()) {
				streakPoints += current.gem.getPoints();
				streakCount++;
			} else{
				score += streakCount * streakPoints;
				streakPoints = current.gem.getPoints(); 
				streakCount = 1;
				streakType = current.gem.getType();
			}
			current = current.next;
		}
		score += streakCount * streakPoints;
		return score;
	}

	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}	
}
