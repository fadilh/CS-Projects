import java.util.*;

public class StackProbs {
    public Stack<Integer> doubleUp(Stack<Integer> nums) {
        Stack<Integer> doubledStack = new Stack<>();
        for (Integer integer : nums) {
            doubledStack.push(integer);
            doubledStack.push(integer);
        }
        return doubledStack;
    }

    public Stack<Integer> posAndNeg(Stack<Integer> nums) {
        Stack<Integer> positive = new Stack<>();
        Stack<Integer> negative = new Stack<>();
        Stack<Integer> output = new Stack<>();
        while (!nums.isEmpty()) {
            if (nums.peek() < 0)
                negative.push(nums.pop());
            else
                positive.push(nums.pop());
        }
        while (!negative.isEmpty()) {
            output.push(negative.pop());
        }
        while (!positive.isEmpty()) {
            output.push(positive.pop());
        }
        return output;
    }

    public Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
        Stack<Integer> output = new Stack<>();
        Stack<Integer> shiftedNums = new Stack<>();
        Stack<Integer> restNums = new Stack<>();
        while (!nums.isEmpty()) {
            if (nums.size() > n) {
                shiftedNums.push(nums.pop());
            } else {
                restNums.push(nums.pop());
            }
        }
        while (!shiftedNums.isEmpty()) {
            output.push(shiftedNums.pop());   
        }
        while (!restNums.isEmpty()) {
            output.push(restNums.pop());   
        }
        return output;
    }

    public String reverseVowels(String str) {
        Stack<Character> noVowelStr = new Stack<>();
        Stack<Character> noVowelStrReversed = new Stack<>();
        Stack<Character> vowelOrder = new Stack<>();
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' 
                || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                vowelOrder.push(str.charAt(i));
            } else {
                noVowelStr.push(str.charAt(i));
            }
        }
        while (!noVowelStr.isEmpty()) {
            noVowelStrReversed.push(noVowelStr.pop());
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' 
                || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                output += vowelOrder.pop(); 
            } else {
                output += noVowelStrReversed.pop();
            }
        }
        return output;
    }

    public boolean bracketBalance(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) 
        {
            char currentChar = s.charAt(i);
  
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') 
            {
                stack.push(currentChar);
                continue;
            }
            if (stack.isEmpty())
                return false;
            char check;
            switch (currentChar) {
            case ')':
                check = stack.pop();
                if (check == '{' || check == '[')
                    return false;
                break;

            case '}':
                check = stack.pop();
                if (check == '(' || check == '[')
                    return false;
                break;

            case ']':
                check = stack.pop();
                if (check == '(' || check == '{')
                    return false;
                break;
            }
        }
        return stack.isEmpty();
    }
}
