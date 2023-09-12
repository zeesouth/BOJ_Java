package stack.n1918;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = new Scanner(System.in).next();

        StringBuilder sb = new StringBuilder();
        Stack<Character> op = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'A' && c <= 'Z') sb.append(c);
            else if (c == '(') op.push(c);
            else if (c == ')') {
                while (!op.isEmpty()) {
                    char currOP = op.pop();
                    if (currOP == '(') break;
                    sb.append(currOP);
                }
            } else {
                while (!op.isEmpty() && precedence(op.peek()) >= precedence(c)) {
                    sb.append(op.pop());
                }
                op.push(c);
            }
        }

        while (!op.isEmpty()) {
            sb.append(op.pop());
        }

        System.out.println(sb);
    }

    static int precedence(char op) {
        if (op == '*' || op == '/') return 2;
        else if (op == '+' || op == '-') return 1;
        else return 0;  // (처리
    }


}
