package work_3;

import java.util.*;
public class arithmetic {

    public static double evaluate(String expression) {

        Stack<Double> values = new Stack<>();//操作数栈
        Stack<Character> ops = new Stack<>();//操作符栈
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.') {//如果是数字或小数点，则直接入栈
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(sb.toString()));
            }else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop(); // 弹出 '('
            } else if (isOperator(c)) {
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            }

        }
        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '^') && (op2 == '^')) {
            return false; // 右结合
        }
        if (op1 == '^') {
            return false; // ^的优先级最高，除了右结合的情况
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        }
        return true;
    }

    private static double applyOp(char op, double b, double a) {
        switch (op) {
        case '+': return a + b;
        case '-': return a - b;
        case '*': return a * b;
        case '/': return a / b;
        case '^': return Math.pow(a, b);
        default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}