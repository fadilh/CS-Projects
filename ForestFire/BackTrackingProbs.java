import java.util.Arrays;
import java.util.List;

public class BackTrackingProbs {
    public static void printBinary(int digits) {
        printBinaryHelper(digits, "");
    }
    private static void printBinaryHelper(int digits, String soFar) {
        if (soFar.length() == digits) {
            System.out.print(soFar + " ");
        }
        else {
            printBinaryHelper(digits, soFar + "0");
            printBinaryHelper(digits, soFar + "1");
        }
    }

    public static void climbStairs(int steps) {
        climbStairsHelper(steps, "", steps);
    }

    private static void climbStairsHelper(int steps, String soFar, int stepsLeft) {
        if (stepsLeft == 0) {
            System.out.println(soFar.substring(0,soFar.length()-2));
            return;
        }
        else if (stepsLeft < 0) {
            return;
        }
        // else if (stepsLeft == 1) {
        //     climbStairsHelper(steps, soFar + "1", stepsLeft - 1);
        // }
        // else if (stepsLeft == 2) {
        //     climbStairsHelper(steps, soFar + "1, ", stepsLeft - 1);
        //     climbStairsHelper(steps, soFar + "2", stepsLeft - 2);

        // }
        else{
            climbStairsHelper(steps, soFar + "1, ", stepsLeft - 1);
            climbStairsHelper(steps, soFar + "2, ", stepsLeft - 2);
        }
    }

    public static void campsite(int x, int y) {
        campsiteHelper(x, y, "", 0, 0);
    }

    private static void campsiteHelper(int x, int y, String soFar, int xMoved, int yMoved) {
        if (xMoved == x && yMoved == y) {
            System.out.println(soFar); return;
        }
        if (xMoved > x || yMoved > y) {
            return;
        }
        else {
            campsiteHelper(x, y, soFar + "E ", xMoved+1, yMoved);
            campsiteHelper(x, y, soFar + "N ", xMoved, yMoved+1);
            campsiteHelper(x, y, soFar + "NE ", xMoved+1, yMoved+1);

        }
    }

    public static int getMax(List<Integer> nums, int limit) {
        return getMaxHelper(nums, limit, 0,0);
    }

    public static int getMaxHelper(List<Integer> nums, int limit, int sum, int index) {
        if (sum > limit) {
            return Integer.MIN_VALUE;
        } else if (index == nums.size() || limit == sum) {
            return sum;
        } else {
            int x = getMaxHelper(nums, limit, sum, index+1);
            int y = getMaxHelper(nums, limit, sum+nums.get(index), index+1);
            return Math.max(x, y);
        }

    }

    public static void main(String[] args) {
        printBinary(3);
        System.out.println();
        System.out.println();
        climbStairs(4);
        System.out.println();
        campsite(2, 1);
        System.out.println();
        System.out.println(getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));
    }
}
