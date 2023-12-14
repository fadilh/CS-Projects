import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<String> list = new ArrayList<>(Arrays.asList("hello", "world"));

        list.forEach(System.out::println);
        nums.removeIf(a -> a % 2 == 0);

    }
}