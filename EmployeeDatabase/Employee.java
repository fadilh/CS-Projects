public class Employee {
    private int id;
    private String name;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Math.abs(id * name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        Employee e = (Employee) obj;
        return id == e.id && name.equals(e.getName());
    }
}
