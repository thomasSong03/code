package work_3;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockIntervalFinder {

    public static int findMaxContinuousRise(double[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[] s = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }

            int j = stack.isEmpty() ? 0 : stack.peek() + 1;
            s[i] = i - j + 1;
            stack.push(i);
        }

        int max = 0;
        for (int length : s) {
            if (length > max) {
                max = length;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        double[] prices = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int maxLength = findMaxContinuousRise(prices);
        System.out.println("最大连续上涨区间长度: " + maxLength); // 输出: 9
    }
}
