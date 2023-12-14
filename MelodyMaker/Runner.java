import java.util.*;

public class Runner {
    public static void main(String[] args) {
        QueueProbs queueProbs = new QueueProbs();
        System.out.println(queueProbs.evenFirst(new LinkedList<>(Arrays.asList(3,4,5,17,6,83,1,84,16,37)))); 
        System.out.println(queueProbs.numPalindrome(new LinkedList<>(Arrays.asList(3,4,5,6,5,4,3)))); 
        System.out.println(queueProbs.primeNumsToN(50)); 
    }
}
