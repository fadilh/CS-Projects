public class EmployeeDatabase {
	private Employee[] employees;
	int size;

	public EmployeeDatabase() {
		employees = new Employee[11];
		size = 0;
	}

	public EmployeeDatabase(int cap) {
		employees = new Employee[cap];
	}

	public Employee find(Employee e) {
		return employees[e.hashCode() % 10];
	}

	public int hash(Employee e) {
		return e.hashCode() % 10;
	}

	public boolean add(Employee e) {
		int hashIdx = hash(e);
		if (employees[hashIdx] == null) {
			employees[hashIdx] = e;
			size++;
			return true;
		}

		if (employees[hashIdx].equals(e)) {
			return false;
		}

		int startIdx = hashIdx;
		do {
			hashIdx = (hashIdx + 1) % 10;
			if (employees[hashIdx] == null) {
				employees[hashIdx] = e;
				size++;
				return true;
			}
		} while (hashIdx != startIdx);

		return false;
	}

	public boolean contains(Employee e) {
		int index = hash(e);
		Employee curr = employees[index];
		while (curr != null) {
			if (curr.equals(e)) {
				return true;
			}
			curr = employees[index + 1];
			index++;
		}
		return false;
	}

	public boolean remove(Employee e) {
		int index = hash(e);
		Employee curr = employees[index], prev = null;

		while (curr != null) {
			if (curr.equals(e)) {
				if (prev == null) {
					employees[index] = employees[index + 1];
				} else {
					prev = employees[index + 1];
				}
				size--;
				return true;
			}
			prev = curr;
			curr = employees[index + 1];
			index++;
		}
		return false;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				out += "Name: " + employees[i].getName() + ", ";
			}
		}
		return out.substring(0, out.length() - 2);
	}
}