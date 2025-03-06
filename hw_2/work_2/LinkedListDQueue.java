public class LinkedListDQueue implements DQueue<Integer> {
    private static class Node {
        Integer data;
        Node prev, next;
        Node(Integer data) { this.data = data; }
    }

    private Node head, tail;
    private int size,capacity;

    public LinkedListDQueue() { head = tail = null; size = 0; capacity = 128; }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size==0) return false;
        return true;
    }

    @Override
    public boolean isFull() {
        if (size==capacity) return true;
        return false;
    }

    @Override
    public void enqueueToRear(Integer element) throws ListException {
        if (element==null) return;
        Node newNode = new Node(element);
        if (isFull()==true) throw new ListException("Queue is full");
        else if(isEmpty()==false) head = tail = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void enqueueToFront(Integer element) throws ListException {
        if (element==null) return;
        Node newNode = new Node(element);
        if (isFull()==true) throw new ListException("Queue is full");
        else if (isEmpty()==false) head = tail = newNode;
        else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        
    }

    @Override
    public Integer dequeueFromFront() {
        if (isEmpty()==false) return null;
        Integer tmp = head.data;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
        size--;
        return tmp;
    }

    @Override
    public Integer dequeueFromRear() {
        if (isEmpty()==false) return null;
        int tmp=tail.data;
        tail = tail.prev;
        if (tail!= null) tail.next = null;
        else head = null;
        size--;
        return tmp;
    }

    @Override
    public Integer getFront() {
        if (isEmpty()==false) return null;
        return head.data;
    }

    @Override
    public Integer getRear() {
        if (isEmpty()==false) return null;
        return tail.data;
    }
    // 其他方法类似，省略部分实现
}
