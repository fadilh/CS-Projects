public class Runner {
    public static void main(String[] args) {
        PhoneBook table = new PhoneBook(10);
        System.out.println(table.put(new Person("Mary"), new PhoneNumber("1234567890")));
        PhoneNumber p = table.put(new Person("Mary"), new PhoneNumber("1234567890"));
        System.out.println(p.number);
    }
}
