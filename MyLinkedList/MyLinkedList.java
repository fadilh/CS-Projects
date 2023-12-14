public class MyLinkedList {
    private class ListNode {
        int val;
        ListNode next;
    
        public ListNode(int val) { this.val = val; }
    
        @Override
        public String toString() {
            return "" + this.val;
        }
        
    }

    ListNode head;
    ListNode tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedList(int val) {
        head = new ListNode(val);
        tail = head;
        size = 1;
    }

    public void add(int val) {
        size++;
        if (head == null) {
            head = new ListNode(val);
            tail = head;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(val);
            tail = current.next;
        }
    }

    public boolean contains(int target) {
        ListNode current = head;
        while (current != null) {
            if (current.val == target) { return true; }
            current = current.next;
        }
        return false;
    }

    public int get(int index) {
        if (index > size()) {throw new IndexOutOfBoundsException();}
        ListNode current = head;
        int output = -1, i = 0;
        while (current != null) {
            if (i == index) { output = current.val; break; }
            i++;
            current = current.next;
        }
        return output;
    }

    public int indexOf(int target) {
        ListNode current = head;
        int index = -1, i = 0;
        while (current != null) {
            if (current.val == target) { index = i; break; }
            i++;
            current = current.next;
        }
        return index;
    }

    public void set(int newVal, int index) {
        ListNode current = head;
        int i = 0;
        while (current != null) {
            if (i == index) { current.val = newVal; break; }
            i++;
            current = current.next;
        }
        if (index > i) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    public int sizeRecursive() {
        return sizeRecursiveInner(head);
    }

    private int sizeRecursiveInner(ListNode current) {
        if (current == null) {
            return 0;
        } 
        else {
            return 1 + sizeRecursiveInner(current.next);
        }
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }

    public int remove(int index) {
        if (index >= size()) {throw new IndexOutOfBoundsException();}
        size--;
        int i = 0, output = 0;
        if (index == 0) {
            output = this.head.val;
            this.head = this.head.next;
        } else {
            ListNode current = this.head;
            while (i != index - 1) {
                i++;
                current = current.next;
            }
            output = current.next.val;
            current.next = current.next.next;
        }
        return output;
    }

    public void add(int newVal, int index) {
        if (index > size()) {throw new IndexOutOfBoundsException();}
        if (index == 0) { 
            ListNode newNode = new ListNode(newVal);
            newNode.next = this.head;
            head = newNode;
        } else {
            ListNode current = this.head;
            int i = 0;
            while (i != index - 1) {
                i++;
                current = current.next;
            }
            ListNode newNode = new ListNode(newVal);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public String toString() {
        ListNode current = head;
        String output = "[";
        while (current != null) {
            output += (current.next == null ? current.val : current.val + ", ");
            current = current.next;
        }
        output += "]";
        return output;
    }

}

