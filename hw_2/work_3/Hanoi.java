package work_3;

import java.util.*;

public class Hanoi {
    public static void move(int n, String source, String target, String auxiliary) {
        Stack<Object[]> stack = new Stack<>();
        stack.push(new Object[]{source, Plate(n), target, auxiliary});
        
        while (!stack.isEmpty()) {
            Object[] current = stack.pop();
            source = (String) current[0];
            List<Integer> plate = (List<Integer>) current[1];
            target = (String) current[2];
            auxiliary = (String) current[3];
            
            if (plate.size() > 1) {
                // 将前n-1个盘子从B移动到C
                stack.push(new Object[]{auxiliary, Plate(plate.size() - 1), target, source});
                // 将第n个盘子从A移动到C
                stack.push(new Object[]{source, Collections.singletonList(plate.get(plate.size() - 1)), target, auxiliary});
                // 将前n-1个盘子从A移动到B
                stack.push(new Object[]{source, Plate(plate.size() - 1), auxiliary, target});
            } else if (plate.size() == 1) {
                System.out.println("从" + source + "移到" + target);
            }
        }
    }

    private static List<Integer> Plate(int n) {
        List<Integer> plates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            plates.add(i);
        }
        return plates;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入递归层数: ");
        int n = scanner.nextInt();
        move(n, "A", "C", "B");
    }
}

