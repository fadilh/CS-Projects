import java.util.Stack;

public class RecursionProbs {
    

    public static double sumReciprocals(int n) {
        return n == 1 ? 1 : sumReciprocals(n - 1) + (1.0 / n);
    }

    public static int productOfEvens(int n) {
        return n == 0 ? 1 : (2 * n) * productOfEvens(n - 1);
    }

    public static String conversion(int num, int base) {
        return num == 0 ? "" : conversion(num / base, base) + (num % base);
    }

    public static int matchingDigits(int a, int b) {
        return (a == 0 || b == 0 ? (a % 10 == b % 10 ? 1 : 0) 
                : (a / 10 == 0 || b / 10 == 0) ? (a % 10 == b % 10 ? 1 : 0) 
                : (a % 10 == b % 10 ? 1 : 0) + matchingDigits(a / 10, b / 10));
    }
    

    public static void doubleUp(Stack<Integer> nums) {
        int num = nums.pop();
        if (nums.size() > 0) {
            doubleUp(nums);
        }
        nums.push(num);
        nums.push(num);
    }

    public static void printThis(int n) {
        if (n == 1) {
            System.out.print("*");
        } 
        else if (n == 2) {
            System.out.print("**");
        } 
        else {
            System.out.print("<");
            printThis(n-2);
            System.out.print(">");
        }
    }

    public static void printThis2(int n) {
        if (n == 1) {
            System.out.print(" 1 ");
        } 
        else if (n == 2) {
            System.out.print("1 1 ");
        } 
        else {
            System.out.print("" + n/2 + " ");
            printThis2(n-2);
            System.out.print("" + n/2 + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println(sumReciprocals(10));
        System.out.println(productOfEvens(4));
        System.out.println(conversion(10,2));
        System.out.println(conversion(1453,8));
        System.out.println(matchingDigits(1000, 0));
        System.out.println(matchingDigits(298892, 7892));
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        doubleUp(stack);
        printThis(10);
        System.out.println();
        printThis2(10);
        System.out.println();

    }
}
