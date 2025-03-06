import java.io.PrintWriter;

public class ArrayList implements List<Character> {
    private Character[] array;
    private int capacity;
    private int length;
    private int cursor;

    public ArrayList() {
        this.capacity = 512;
        this.array = new Character[capacity];
        this.length = 0;
        this.cursor = -1;
    }


    public void insert(Character newElement) throws ListException {
        if (isFull()) {
            throw new ListException("List is full");
        }
        if (length == 0) {
            array[0] = newElement;
            cursor = 0;
        } else {
            for (int i = length; i > cursor + 1; i--) {
                array[i] = array[i - 1];
            }
            array[cursor + 1] = newElement;
            cursor++;
        }
        length++;
    }


    public void remove() {
        if (isEmpty()) return;
        for (int i = cursor; i < length - 1; i++) {
            array[i] = array[i + 1];
        }
        length--;
        if (length == 0) cursor = -1;
        else if (cursor == length) cursor = 0;
    }


    public void replace(Character newElement) {
        array[cursor] = newElement;
    }


    public void clear() {
        length = 0;
        cursor = -1;
    }


    public boolean isEmpty() {
        return length == 0;
    }


    public boolean isFull() {
        return length == capacity;
    }


    public boolean gotoBeginning() {
        if (isEmpty()) return false;
        cursor = 0;
        return true;
    }

    public boolean gotoEnd() {
        if (isEmpty()) return false;
        cursor = length - 1;
        return true;
    }


    public boolean gotoNext() {
        if (isEmpty() || cursor == length - 1) return false;
        cursor++;
        return true;
    }


    public boolean gotoPrev() {
        if (isEmpty() || cursor == 0) return false;
        cursor--;
        return true;
    }


    public Character getCursor() {
        return array[cursor];
    }


    public void showStructure(PrintWriter pw) {
        if (isEmpty()) {
            pw.println("Empty list {capacity = " + capacity + ", length = 0, cursor = -1}");
            return;
        }
        for (int i = 0; i < length; i++) {
            pw.print(array[i] + " ");
        }
        pw.println("{capacity = " + capacity + ", length = " + length + ", cursor = " + cursor + "}");
    }


    public void moveToNth(int n) {
        if (n < 0 || n >= length) return;
        Character temp = array[cursor];
        remove();
        for (int i = length; i > n; i--) {
            array[i] = array[i - 1];
        }
        array[n] = temp;
        length++;
        cursor = n;
    }


    public boolean find(Character searchElement) {
        if (isEmpty()) return false;
        for (int i = cursor; i < length; i++) {
            if (array[i].equals(searchElement)) {
                cursor = i;
                return true;
            }
        }
        cursor = length - 1;
        return false;
    }
}