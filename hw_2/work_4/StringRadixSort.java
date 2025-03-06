package work_4;

import java.util.LinkedList;
import java.util.Queue;

public class StringRadixSort {
    public static void sort(String[] arr, int width) {
        for (int pos = width - 1; pos >= 0; pos--) {
            @SuppressWarnings("unchecked")
            //按ASCII码排序
            Queue<String>[] buckets = new LinkedList[256];

            for (int i = 0; i < 256; i++)
                buckets[i] = new LinkedList<>();
            for (String s : arr)
                buckets[s.charAt(pos)].offer(s);
            int idx = 0;
            for (Queue<String> bucket : buckets)
                while (!bucket.isEmpty())
                    arr[idx++] = bucket.poll();
        }
    }
}