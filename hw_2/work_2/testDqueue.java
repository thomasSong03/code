public static int[] randomArray(int size){
    	
    int[] array = new int[size];
    for(int i = 0; i<array.length;i++){
        array[i] = (int)(Math.random()*1000);//0~1乘数1000，内容变为0~1000，强转为整型
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
    int[] arr0 = randomArray(20);
    System.out.print("随机数组为：");
    printArray(arr0);
    System.out.print("结果为：");
    SlidingWindowMax.maxSlidingWindow(arr0, 3);
    //一次打印输出：随机数组为：482 227 574 562 361 149 35 221 963 696 199 728 990 725 901 319 154 587 923 588 

}
