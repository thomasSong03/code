package work_3;
import java.util.*;

class tri_heap {
    private List<Integer> heap;

    public tri_heap() {
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        swim(heap.size() - 1);
    }

    public int deleteMin() {
        if (heap.isEmpty()) throw new NoSuchElementException();
        int min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        sink(0);
        return min;
    }

    private void swim(int index) {
        while (index > 0 && heap.get((index - 1) / 3) > heap.get(index)) {
            swap((index - 1) / 3, index);
            index = (index - 1) / 3;
        }
    }

    private void sink(int index) {
        while (3 * index + 1 < heap.size()) {
            int smallest = 3 * index + 1;
            for (int i = 2; i <= 3; i++) {
                int child = 3 * index + i;
                if (child < heap.size() && heap.get(child) < heap.get(smallest)) {
                    smallest = child;
                }
            }
            if (heap.get(index) <= heap.get(smallest)) break;
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void heapSort(int[] arr) {
        tri_heap heap = new tri_heap();
        for (int num : arr) {
            heap.insert(num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.deleteMin();
        }
    }

    //生成一个随机数组进行排序
    public static int[] randomArray(int size){
    	
        int[] array = new int[size];
        for(int i = 0; i<array.length;i++){
            array[i] = (int)(Math.random()*50);
        }  
        return array;
    }

    public static void printArray(int[] arr){
        for(int i= 0 ; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String arr[]) {		
        int[] arr0 = randomArray(50);
        System.out.print("随机数组为：");
        printArray(arr0);
        heapSort(arr0);
        System.out.print("排序结果为：");
        printArray(arr0);
    }
}