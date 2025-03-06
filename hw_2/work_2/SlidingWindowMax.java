//将双端队列变为单调递减数列
public class SlidingWindowMax {
    public static int[] maxSlidingWindow(int[] nums, int k) throws ListException {
        if (nums == null || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        DQueue<Integer> q = new LinkedListDQueue();
        for (int i = 0; i < nums.length; i++) {
            if (i<k-1) {
                while (q.isEmpty()==true && q.getRear() < nums[i]) {
                    q.dequeueFromRear();
                }
                q.enqueueToRear(nums[i]);
            } else {
                while (q.isEmpty()==true && q.getRear() < nums[i]) {
                    q.dequeueFromRear();
                }
                q.enqueueToRear(nums[i]);
                result[i-k+1] = q.getFront();
                if (q.isEmpty()==true && q.getFront() == nums[i-k+1]) q.dequeueFromFront();
            }
        }
        return result;
    }
}

public static int[] randomArray(int size){
    	
    int[] array = new int[size];
    for(int i = 0; i<array.length;i++){
        array[i] = (int)(Math.random()*20);//0~1乘数1000，内容变为0~1000，强转为整型
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
    for (int i = 0; i < 10; i++){
        int[] arr0 = randomArray(20);
        System.out.print("随机数组为：");
        printArray(arr0);
        System.out.print("结果为：");
        printArray(SlidingWindowMax.maxSlidingWindow(arr0, 5));
    }

}