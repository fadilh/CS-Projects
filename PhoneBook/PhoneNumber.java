public class PhoneNumber {
    String number;

    PhoneNumber(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        PhoneNumber p = (PhoneNumber) obj;
        return number.equals(p.number);
    }
}