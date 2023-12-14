public class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode() % 10;
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person) obj;
        return name.equals(p.name);
    }
}
