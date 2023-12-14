import java.util.EmptyStackException;

public class MyStack {
    private Integer[] stack;
    private int size;

    public MyStack(){
        stack = new Integer[2];
        size = 0;
    }

    public MyStack(int initCap) {
        this();
        stack = new Integer[initCap];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer peek() {
        if (size != 0) {
            return stack[0];
        } else {
            throw new EmptyStackException();
        }
    }

    public Integer pop() {
        if (size != 0) {
            Integer popped = stack[0];
            stack[0] = null;
            for (int i = 0; i < size - 1; i++) {
                stack[i] = stack[i+1];
            }
            size--;
            return popped;
        } else {
            throw new EmptyStackException();
        }
    }

    public void push(Integer item) {
        if (size == stack.length) {doubleCapacity();}
        for (int i = size - 1; i >= 0; i--) {
            Integer current = stack[i];
            stack[i+1] = current;
        }
        stack[0] = item;
        size++;
    }

    private void doubleCapacity() {
        Integer[] newArr = new Integer[stack.length * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = stack[i];
        }
        stack = newArr;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < size; i++) {
            output += "\n" + stack[i];
        }
        return output;
    }
}
