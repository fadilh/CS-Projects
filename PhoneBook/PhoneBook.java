public class PhoneBook implements IMap {
    private class Entry {
        Person key;
        PhoneNumber value;
        Entry next;

        public Entry(Person key, PhoneNumber value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    int size;
    Entry[] entries;

    public PhoneBook(int capacity) {
        size = 0;
        entries = new Entry[capacity];
    }

    @Override
    public PhoneNumber put(Person person, PhoneNumber phone) {
        size++;
        if (entries[person.hashCode()] == null) {
            entries[person.hashCode()] = new Entry(person, phone, null);
            return null;
        } else if (entries[person.hashCode()].next == null) {
            entries[person.hashCode()].next = new Entry(person, phone, null);
            return entries[person.hashCode()].value;
        } else {
            Entry curr = entries[person.hashCode()];
            while (curr.next != null) {
                curr = curr.next;
            }
            entries[person.hashCode()].next = new Entry(person, phone, null);
            return entries[person.hashCode()].value;
        }
    }

    @Override
    public PhoneNumber get(Person person) {
        Entry curr = entries[person.hashCode()];
        while (curr != null) {
            if (curr.key.equals(person)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public PhoneNumber remove(Person person) {
        int hash = person.hashCode();
        Entry curr = entries[hash];
        Entry prev = null;
        while (curr != null) {
            if (curr.key.equals(person)) {
                size--;
                if (prev == null) {
                    entries[hash] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;

    }
}
