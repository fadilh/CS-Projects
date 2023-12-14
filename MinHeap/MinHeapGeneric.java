public class MinHeapGeneric<T extends Comparable<T>> {
	private T[] heap;
	int size;
	static final int DEFAULT_CAPACITY = 2;

	public MinHeapGeneric() {
		this.heap = (T[]) new Comparable[DEFAULT_CAPACITY];
	}

	public MinHeapGeneric(int capacity) {
		this.heap = (T[]) new Comparable[capacity];
	}

	public MinHeapGeneric(T... nums) {
		heap = (T[]) new Comparable[nums.length];
		for (int i = 0; i < nums.length; i++) {
			heap[i] = nums[i];
		}

	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size <= 0;
	}

	public T peekMinimum() {
		return heap[1];
	}

	public int getLeftChildIndex(int index) {
		return 2 * index;
	}

	public int getRightChildIndex(int index) {
		return 2 * index;
	}

	public int getParentIndex(int index) {
		return index / 2;
	}

	private void doubleCapacity() {
		T[] newHeap = (T[]) new Comparable[heap.length * 2];
		for (int i = 1; i <= size; i++) {
			newHeap[i] = heap[i];
		}

		heap = newHeap;
	}

	public void insert(T value) {
		if (heap.length <= size + 1) {
			doubleCapacity();
		}
		size++;
		heap[size] = value;
		bubbleUp(size);
	}

	private void bubbleUp(int index) {
		if (index > 1 && heap[getParentIndex(index)].compareTo(heap[index]) > 0) {
			T temp = heap[index];
			heap[index] = heap[getParentIndex(index)];
			heap[getParentIndex(index)] = temp;
			bubbleUp(getParentIndex(index));
		}
	}

	public T popMinimum() {
		T out = peekMinimum();
		if (size > 1) {
			heap[size] = heap[1];
			heap[size] = null;
			size--;
			siftDown(1);
		}
		return out;
	}

	private void siftDown(int index) {

		if (getLeftChildIndex(index) > size || getRightChildIndex(index) > size) {
			return;
		} else if (getRightChildIndex(index) > size && getLeftChildIndex(index) <= size) {
			if (heap[index].compareTo(heap[getLeftChildIndex(index)]) > 0) {
				T temp = heap[index];
				heap[index] = heap[getLeftChildIndex(index)];
				heap[getLeftChildIndex(index)] = temp;
			}
		} else {
			T temp = heap[index];
			heap[index] = heap[getLeftChildIndex(index)].compareTo(heap[getRightChildIndex(index)]) > 0
					? heap[getLeftChildIndex(index)]
					: heap[getRightChildIndex(index)];
			if (heap[getLeftChildIndex(index)].compareTo(heap[getRightChildIndex(index)]) < 1) {
				heap[getLeftChildIndex(index)] = temp;
			} else {
				heap[getRightChildIndex(index)] = temp;
			}
			siftDown(getLeftChildIndex(index));
			siftDown(getRightChildIndex(index));
		}

	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 1; i <= getSize(); i++)
			output += heap[i] + ", ";
		return output.substring(0, output.lastIndexOf(","));
	}

	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);
		while (j <= this.getSize()) {
			if (column == 0)
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');
			System.out.print((heap[j] == null) ? "" : heap[j]);
			if (++column == itemsPerRow) {
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			} else
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
			j++;
		}
		System.out.println("\n" + dots + dots);
	}
}
