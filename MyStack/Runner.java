import java.util.*;

public class Runner {
    public static void main(String[] args) {
        StackProbs stackProbs = new StackProbs();

        System.out.println(stackProbs.doubleUp(makeStack(new int[] {1,2,3,4})));
        System.out.println(stackProbs.posAndNeg(makeStack(new int[] {2, 9, -4, 3, -1, 0, -6})));
        System.out.println(stackProbs.shiftByN(makeStack(new int[] {7,23,-7,0,22,-8,4,5}), 3));
        System.out.println(stackProbs.reverseVowels("hello how are you"));
        System.out.println(stackProbs.bracketBalance("(([]()[([])]))"));
    }

    public static Stack<Integer> makeStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums)
            stack.push(num);
        return stack;
    }
}
