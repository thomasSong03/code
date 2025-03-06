package work_3;
import java.util.*;

class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
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

    public int getMin() {
        if (heap.isEmpty()) throw new NoSuchElementException();
        return heap.get(0);
    }

    private void swim(int index) {
        while (index > 0 && heap.get((index - 1) / 2) > heap.get(index)) {
            swap((index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    private void sink(int index) {
        while (2 * index + 1 < heap.size()) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = left;
            if (right < heap.size() && heap.get(right) < heap.get(left)) {
                smallest = right;
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

    //用堆排序
    public static void heapSort(int[] arr) {
        MinHeap heap = new MinHeap();
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
