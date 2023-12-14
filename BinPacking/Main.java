import java.util.*;
import java.io.*;

class Disk implements Comparable<Disk> {
    ArrayList<Integer> files = new ArrayList<>();
    int id = 0;
    int spaceLeft = 1000000;

    public int compareTo(Disk other) {
        if (this.id == other.id) {
          return 0;
        }
        return this.spaceLeft < other.spaceLeft ? 1 : -1;
    }

    public Disk(int i) { 
        this.id = i; 
    }
}

class Heuristics {
  public void worst(ArrayList<Integer> files) {
    PriorityQueue<Disk> pq = new PriorityQueue<>((x, y) -> x.compareTo(y));
    int curr = 0;
    int sum = 0;

    for (int i = 0; i < files.size(); i++) {
        sum += files.get(i);
        Disk d = (!pq.isEmpty() && pq.peek().spaceLeft >= files.get(i)) ? pq.poll() : new Disk(curr++);
        d.spaceLeft -= files.get(i);  
        d.files.add(files.get(i));
        pq.add(d);
    }
    if (pq.size() < 100) {
        System.out.print("Total size = " + (double)sum / 1000000 + " GB" + "\nDisks req'd = " +  pq.size());

        while (!pq.isEmpty()) {
            Disk d = pq.poll();
            System.out.print("\n  " + d.id + " - " + d.spaceLeft + ": ");
            for(int v : d.files) {
              System.out.print(v + " "); 
            }
        }
    }
  }

  public void worstDecreasing(ArrayList<Integer> files) {
    Collections.sort(files);
    Collections.reverse(files);
    worst(files);
  }

  public void best(ArrayList<Integer> files) {
    Collections.sort(files);  
    Collections.reverse(files);
    TreeSet<Disk> ts = new TreeSet<>((x, y) -> x.compareTo(y));
    int id = 0;
    int sum = 0;
    Disk temp = new Disk(-1);

    for (int i = 0; i < files.size(); ++i) {
        temp.spaceLeft = files.get(i);
        Disk v = ts.floor(temp);
        Disk d;

        if (v == null) { 
            d = new Disk(id++); 
        } else {
            ts.remove(v); 
            d = v;
        }
        
        d.files.add(files.get(i));  
        d.spaceLeft -= files.get(i);
        ts.add(d);
        sum += files.get(i);
    }
    if (ts.size() < 100) {
        System.out.print("Total size = " + (double)sum / 1000000 + " GB" + "\nDisks req'd = " +  ts.size());

        while(!ts.isEmpty()) {
            Disk d = ts.pollFirst();
            System.out.print("\n   " + d.id + " " + d.spaceLeft + ": ");

        }
    }
  }
}

public class Main {
  public static void main(String[] args) {
      try {
          Scanner scan = new Scanner(new File("input20.txt"));
          ArrayList<Integer> files = new ArrayList<>();

          while(scan.hasNext()) {
              files.add(scan.nextInt());
          }

          Heuristics h = new Heuristics();
          h.best(files);
      } catch(Exception ex) {

      }
  }
}