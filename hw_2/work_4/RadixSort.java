package work_4;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSort {
    public static void sort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, exp);
    }

    private static void countSort(int[] arr, int exp) {
        Queue<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++)
            buckets[i] = new LinkedList<>();
        for (int num : arr)
            buckets[(num / exp) % 10].offer(num);
        int idx = 0;
        for (Queue<Integer> bucket : buckets)
            while (!bucket.isEmpty())
                arr[idx++] = bucket.poll();
    }
}
