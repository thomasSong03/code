public class ArrayDQueue implements DQueue<Integer> {
    private Integer[] array;
    private int front, rear, size, capacity;

    public ArrayDQueue() {
        capacity = 128;
        array = new Integer[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    
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
        else if (isFull()==true) throw new ListException("Queue is full");
        else {
            if (isEmpty()==false) {
                front=0;
                rear=0;
            }else rear = (rear + 1) % capacity;//使用模运算，防止数组越界
            array[rear] = element;
            size++;
        }
    }

    @Override
    public void enqueueToFront(Integer element) throws ListException {
        if (element==null) return;
        else if (isFull()==true) throw new ListException("Queue is full");
        else {
            if (isEmpty()==false) {
                front=0;
                rear=0;
            }else front = (front - 1 + capacity) % capacity; 
            array[front] = element;
            size++;
        }

        
    }

    @Override
    public Integer dequeueFromFront() {
        if (isEmpty()==false) return null;
        int tmp=array[front];
        if (front==rear) {
            front=-1;
            rear=-1;
            return null;
        }else front = (front + 1) % capacity;
        size--;
        return tmp;
        
    }
    
    @Override
    public Integer dequeueFromRear() {
        if (isEmpty()==false) return null;
        int tmp=array[rear];
        if (front==rear) {
            front=-1;
            rear=-1;
            return null;
        }else rear = (rear - 1 + capacity) % capacity;
        size--;
        return tmp;
    }

    @Override
    public Integer getFront() {
        if (isEmpty()==false) return null;
        return array[front];
    }

    @Override
    public Integer getRear() {
        if (isEmpty()==false) return null;
        return array[rear];
    }

}