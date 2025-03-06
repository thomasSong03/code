import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMax1 {
    // 定义一个方法，用于计算滑动窗口中的最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 如果输入的数组为空或者窗口大小小于等于0，则返回一个空数组
        if (nums == null || k <= 0) return new int[0];
        // 定义一个数组，用于存储滑动窗口中的最大值
        int[] result = new int[nums.length - k + 1];
        // 定义一个双端队列，用于存储滑动窗口中的最大值的索引
        Deque<Integer> q = new ArrayDeque<>();
        // 遍历输入的数组
        for (int i = 0; i < nums.length; i++) {
            // 如果队列不为空，且队列中的第一个元素的索引小于当前元素的索引减去窗口大小加1，则将队列中的第一个元素出队
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) q.pollFirst();
            // 如果队列不为空，且队列中的最后一个元素对应的值小于当前元素的值，则将队列中的最后一个元素出队
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.pollLast();
            // 将当前元素的索引入队
            q.offerLast(i);
            // 如果当前元素的索引大于等于窗口大小减1，则将队列中的第一个元素对应的值存入结果数组中
            if (i >= k - 1) result[i - k + 1] = nums[q.peekFirst()];
        }
        // 返回结果数组
        return result;
    }
}


public class SlidingWindowMax {
    public static int[] maxSlidingWindow(int[] nums, int k) throws ListException {
        if (nums == null || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        DQueue<Integer> q = new LinkedListDQueue();
        for (int i = 0; i < nums.length; i++) {
            if (q.isEmpty()==true && q.size()>=k) q.dequeueFromFront();        // 若q中已有k个元素，则移除队首元素
            if (q.isEmpty()==false && q.getFront() < nums[i]) q.dequeueFromFront();    // 若q中已有元素，且队首元素小于当前元素，则移除队首元素
            q.enqueueToRear(nums[i]);
            if (i >= k - 1) result[i - k + 1] = q.getFront();
        }
        return result;
    }
}

public static int[] randomArray(int size){
    	
    int[] array = new int[size];
    for(int i = 0; i<array.length;i++){
        array[i] = (int)(Math.random()*4);//0~1乘数1000，内容变为0~1000，强转为整型
    }  
    return array;
}

public static void printArray(int[] arr){
    for(int i= 0 ; i<arr.length; i++){
           System.out.print(arr[i] + " ");
       }
    System.out.println();
}

public static void main(String arr[]) throws ListException{		
    int[] arr0 = randomArray(4);
    System.out.print("随机数组为：");
    printArray(arr0);
    System.out.print("结果为：");
    printArray(SlidingWindowMax.maxSlidingWindow(arr0, 3));
    //一次打印输出：随机数组为：482 227 574 562 361 149 35 221 963 696 199 728 990 725 901 319 154 587 923 588 

}