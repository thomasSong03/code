package work_4;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;


public class MainStart1{
    public static void main(String args[]) throws IOException{
        String filePath = "work_4\\radixSort1.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        
        String strTmp = "";
        String[] S_Array= new String[40];
        int length=0,i=0;
        
        while((strTmp = buffReader.readLine())!=null){
            S_Array[i]=strTmp;
            String[] strs = S_Array[i].split(" ");
            int[] num = Arrays.stream(strs).mapToInt(Integer::parseInt).toArray();
            
            System.out.print("数组为：");
            printArray(num);
            System.out.print("基数排序为：");
            RadixSort.sort(num);
            printArray(num);
            length++;
        }
        buffReader.close();
    }

    public static String getType(Object obj){            
        return obj.getClass().toString();
    }

    public static void printArray(int[] arr){
        for(int i= 0 ; i<arr.length; i++){
               System.out.print(arr[i] + " ");
           }
        System.out.println();
    }
}

