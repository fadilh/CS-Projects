import java.util.*;

public class QueueProbs {
    public Queue<Integer> evenFirst(Queue<Integer> nums) {
        Queue<Integer> output = new LinkedList<>();
        Queue<Integer> odd = new LinkedList<>();

        while (!nums.isEmpty()) {
            if (nums.peek() % 2 == 0) {
                output.offer(nums.poll());
            } else {
                odd.offer(nums.poll());
            }
        }
        while (!odd.isEmpty()) {
            output.offer(odd.poll());
        }

        return output;
    }

    public boolean numPalindrome(Queue<Integer> nums) {
        int firstHalfSize = nums.size() / 2;
        int secondHalfSize = nums.size() - firstHalfSize;
        Stack<Integer> firstHalf = new Stack<>();
        Queue<Integer> firstHalfQ = new LinkedList<>();
        Queue<Integer> secondHalf = new LinkedList<>();
        while (!nums.isEmpty()) {
            while (firstHalfSize != 0) {
                firstHalf.push(nums.poll());
                firstHalfSize--;
            } 
            while (secondHalfSize != 0 && firstHalfSize == 0) {
                secondHalf.offer(nums.poll());
                secondHalfSize--;
            } 
        }
        while (!firstHalf.isEmpty()) {
            firstHalfQ.offer(firstHalf.pop());
        }
        secondHalf.poll();
        return firstHalfQ.equals(secondHalf);
    }

    public Queue<Integer> primeNumsToN(int n) {
        Queue<Integer> numsToN = new LinkedList<>();
        Queue<Integer> primes = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            numsToN.offer(i);
        }
        int p = 0;
        int temp = 0;
        do {
            p = numsToN.poll();
            temp = p;
            primes.offer(p);
            int numSize = numsToN.size();
            for (int i = 0; i < numSize; i++) {
                temp = numsToN.poll();
                if (temp % p != 0) {
                    numsToN.offer(temp);
                }
            }
        } while (!numsToN.isEmpty());
        return primes;
    }
}
