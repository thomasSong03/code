import java.io.PrintWriter;

public class DoublyLinkedList implements List<Character> {
    // 定义一个内部静态类Node，用于存储链表节点
    private static class Node {
        Character data; // 节点存储的数据
        Node prev, next; // 前驱节点和后继节点
        Node(Character data) {
            this.data = data;
        }  
    }

    private Node head, tail, cursor;
    private int capacity, length;

    public DoublyLinkedList() {
        head = tail = cursor = null;
        capacity =512; 
        length = 0;
    }

    public void insert(Character newElement) throws ListException {
        if (newElement == null) throw new ListException("Element is null");
        if (length == capacity) throw new ListException("List is full");
        Node newNode = new Node(newElement);
        if (isEmpty()==true) {
            head = tail = cursor = newNode;
        } else {
            newNode.prev = cursor;
            newNode.next = cursor.next;
            if (cursor.next != null) cursor.next.prev = newNode;
            else tail = newNode;
            cursor.next = newNode;
            cursor = newNode;
        }
        length++;
    }

    public void remove() {
        if (isEmpty()==true) return;
        if (cursor.prev != null) cursor.prev.next = cursor.next;
        else head = cursor.next;
        if (cursor.next != null) cursor.next.prev = cursor.prev;
        else tail = cursor.prev;
        cursor = cursor.next != null ? cursor.next : head;
        length--;
    }

    public void replace(Character newElement) {
        Node newNode = new Node(newElement);
        if (isEmpty()==true) return;
        if (cursor == head) {
            newNode.next = cursor.next;
            head = newNode;
            cursor = newNode;
        }
        else {
            cursor.prev.next = newNode;
            newNode.next = cursor.next;
            newNode.prev= cursor.prev;
            if (cursor == tail) tail = newNode;
            else cursor.next.prev = newNode;
            cursor = newNode;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        cursor = null;
        length = 0;
    }

    public boolean isEmpty() {
        if (length == 0) return true;
        else return false;
    }

    public boolean isFull() {
        if (length == capacity) return true;
        else return false;
    }

    public boolean gotoBeginning() {
        Node newNode = new Node(null);
        if (isEmpty()==true) return false;
        newNode=cursor;
        cursor = head;
        return true;
    }
    
    public boolean gotoEnd() {
        Node newNode = new Node(null);
        if (isEmpty()==true) return false;
        newNode=cursor;
        cursor.prev.next = newNode;
        if (cursor == tail) return true;
        else cursor.next.prev = newNode;
            cursor = tail;
            return true;
    }

    public boolean gotoNext() {
        Node newNode = new Node(null);
        if (isEmpty()==true || cursor == tail) return false;
        newNode=cursor;
        cursor = cursor.next;
        return true;
    }
    public boolean gotoPrev() {
        Node newNode = new Node(null);
        if (isEmpty()==true || cursor == head) return false;
        newNode=cursor;
        cursor=cursor.prev;
        return true;
    }

    public Character getCursor() {
        // 如果数组为空，则返回null
        return isEmpty()==true ? null : cursor.data;
    }

    public void showStructure(PrintWriter pw) {
        if (isEmpty()==false) {
            Node newNode = new Node(null);
            newNode=head;
            int j=-1,k=0;
            for (int i = 0; i < length; i++) {
                pw.print(newNode.data+" ");
                if (i < length - 1) pw.print(" \\ ");
                if (newNode==cursor) {k=1;}
                if (newNode!=cursor && k==0) {j++;}
                newNode = newNode.next;
            }
            pw.println(" {capacity = " + capacity + ", length = " + length + ", cursor = " + j + "}");
        } else {
            pw.println("Empty list");
        }
    }

    public void moveToNth(int n) {
        if (n < 0 || n >= length) return;
        Node newNode=head;
        Character temp = cursor.data;
        int i=1;
        while (i<n) {
            newNode = newNode.next;
            i++;
        }
        Node prev = head;
        while (prev.next != cursor) prev = prev.next;
        prev.next=cursor.next;
        cursor.next.prev=prev;
        newNode.data=temp;
        cursor=newNode;
    }

    public boolean find(Character searchElement) {
        if (isEmpty()==true) return false;
        Node current = cursor;
        while(current != null) {
            if (current.data.equals(searchElement)) return true;
            current = current.next;
        }
        return false;
    }
}
