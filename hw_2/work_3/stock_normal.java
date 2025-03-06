package work_3;

public class stock_normal {
    public static int maxIncreasingSubarray(int[] prices) {
        int[] s = new int[prices.length];
        int maxLen = 1;
        for (int i = 0; i < prices.length; i++) {
            s[i]=0;
            for (int j = i; j >=0 ; j--) {
                if (prices[j] <= prices[i]) {
                    s[i]++;
                } else {
                    break;
                }
            }
            if (i > 0 && s[i-1] < s[i]) {
                maxLen = s[i];
            }
        }
        return maxLen;
    }


    static int l=20;//随机数长度
        public static int[] randomArray(int size){
            int[] array = new int[size];
            for(int i = 0; i<array.length;i++){
                array[i] = (int)(Math.random()*l);//0~1乘数1000，内容变为0~1000，强转为整型
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
        int[] arr0 = randomArray(l);
        System.out.print("随机数组为：");
        printArray(arr0);
        System.out.print("结果为：");
        System.out.print(stock_normal.maxIncreasingSubarray(arr0));
    }
}
