import java.io.PrintWriter;
//import java.util.List;

public class SinglyLinkedList implements List<Character> {
    private static class Node {
        Character data;
        Node next;
        Node(Character data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head, cursor, tail;
    private int capacity, length;

    public SinglyLinkedList() {
        head = cursor = tail = null;
        capacity = 512;
        length = 0;
    }
    
    public void insert(Character newElement) throws ListException {
        if (newElement == null) throw new ListException("Element is null");
        if (length == capacity) throw new ListException("List is full");
        Node newNode = new Node(newElement);
        if (isEmpty()==true) {
            head = tail = cursor = newNode;
        } else {
            newNode.next = cursor.next;
            cursor.next = newNode;
            cursor = newNode;
            if (newNode.next == null) tail = newNode;
        }
        length++;
    }


    // 移除游标指向的节点
    public void remove() {
        // 如果链表为空，则直接返回
        if (isEmpty()==true) return;
        // 如果头节点等于游标，则将头节点指向下一个节点
        if (head == cursor) {
            head = head.next;
            // 如果头节点不为空，则将游标指向头节点，否则将游标置为空
            cursor = head != null ? head : null;
            // 如果头节点为空，则将尾节点置为空，否则将尾节点指向原来的尾节点
            tail = head == null ? null : tail;
        } else {
            // 否则，将前驱节点的next指针指向游标节点的下一个节点
            Node prev = head;
            while (prev.next != cursor) prev = prev.next;
            prev.next = cursor.next;
            // 如果游标等于尾节点，则将尾节点指向前驱节点
            if (cursor == tail) tail = prev;
            // 如果前驱节点的next指针不为空，则将游标指向前驱节点的next节点，否则将游标指向头节点
            cursor = prev.next != null ? prev.next : head;
        }
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
            Node prev = head;
            while (prev.next != cursor) prev = prev.next;
            prev.next = newNode;
            newNode.next = cursor.next;
            if (cursor == tail) tail = newNode;
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
        if (isEmpty()==true) return false;
        else
            cursor = head;
            cursor.next=head.next;
            return true;
    }
    
    public boolean gotoEnd() {
        if (isEmpty()==true) return false;
        cursor = tail;
        cursor.next=null;
        return true;
    }

    public boolean gotoNext() {
        if (isEmpty()==true || cursor == tail) return false;
        cursor=cursor.next;
        cursor.next=cursor.next.next;
        return true;
    }
    
    public boolean gotoPrev() {
        Node prev = head;
        while (prev.next != cursor) prev = prev.next;
        if (isEmpty()==true || cursor == head) return false;
        cursor.next=cursor;
        cursor=prev;
        return true;
    }

    public Character getCursor() {
        return isEmpty()==true ? null : cursor.data;
    }

    public void showStructure(PrintWriter pw) {
        Node current = head;
        int i=0; // met cursor or not
        int j=0; // counter
        if (isEmpty()==false) {
            while (current != null) {
                if (current !=cursor && i==0) j++;
                if (current.next ==cursor) i=1;
                pw.print(current.data + " ");
                current = current.next;
            }
            pw.println(" {capacity = " + capacity + ", length = " + length + ", cursor = " + j + "}");
        } else{
            pw.println("Empty list");
        }
    }

    public void moveToNth(int n) {
        if (n < 0 || n >= length) return;
        Node N = head;
        int i=1;
        while (i<n) {
            N = N.next;
            i++;
        }

        Character temp = cursor.data;
        Node prev = head;
        while (prev.next != cursor) prev = prev.next;
        prev.next = cursor.next;

        N.data= temp;
        cursor= N;
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