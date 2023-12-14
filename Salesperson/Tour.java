public class Tour 
{
	private class Node {
		Point p;
		Node next;
		
		Node(Point p, Node next) {
			this.p = p;
			this.next = next;
		}
	}

	Node head;
	int size;
	/** create an empty tour */
	public Tour()
	{
		head = null;
	}
	
	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d)
	{
		size = 4;
		Node tail = new Node(d, null);
		head = new Node(a, new Node(b, new Node(c, tail)));
		tail.next = head;
	}
	
	/** print tour (one point per line) to std output */
	public void show()
	{
		if (size == 0) {return;}
		Node current = head;
		for (int i = 0; i < size; i++) {
			System.out.println(current.p);
			current = current.next;
		}
	}
	
	/** draw the tour using StdDraw */
	public void draw()
	{
		if (size == 0) {return;}
		Node current = head;
		for (int i = 0; i < size; i++) {
			current.p.draw();
			current.p.drawTo(current.next.p);
			current = current.next;
		}
	}
	
	/** return number of nodes in the tour */
	public int size() { return size; }
	
	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance()
	{
		if (size() == 0) {return 0.0;}
		double distance = 0.0;
		Node current = head;
		for (int i = 0; i < size; i++) {
			distance += current.p.distanceTo(current.next.p);
			current = current.next;
		}
		return distance;
	}
	
	/** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p) 
    {
		if (size == 0) {
			head = new Node(p, null);
			head.next = head;
			size++;
			return;
		}
		size++;
		Node current = head;
		Node best = head;
		double distance = Double.MAX_VALUE;
		for (int i = 0; i < size; i++) {
			if (current.p.distanceTo(p) < distance) {
				distance = current.p.distanceTo(p);
				best = current;
			}
			current = current.next;
		}
		Node newNode = new Node(p, best.next);
		best.next = newNode;
	}

	/** insert p using smallest increase heuristic */
    public void insertSmallest(Point p) 
    {
		if (size == 0) {
			head = new Node(p, null);
			head.next = head;
			size++;
			return;
		}
		size++;
		Node current = head;
		Node best = head;
		double smallestDist = Double.MAX_VALUE;
		for (int i = 0; i < size; i++) {
			double newDist = distance() - (current.p.distanceTo(current.next.p)) + (current.p.distanceTo(p)) + (p.distanceTo(current.next.p));
			if (newDist < smallestDist) {
				smallestDist = newDist;
				best = current;
			}
			current = current.next;
		}
		Node newNode = new Node(p, best.next);
		best.next = newNode;
    }
	public static void main(String[] args) {
        // StdDraw.setCanvasSize(600, 600);
		// StdDraw.setXscale(0, 600);
        // StdDraw.setYscale(0, 600);
		Tour t = new Tour(new Point(0, 300), new Point(600, 300), new Point(300, 600), new Point(300, 0));
		// Tour t = new Tour();
		t.show();
		System.out.println(t.size());
		t.insertNearest(new Point(300, 1));
		t.insertSmallest(new Point(300, 599));
		t.show();
		System.out.println(t.size());

	}
}

