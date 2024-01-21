package string.n20210;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static final int MAX = 10000;
    static int N;
    static ArrayList<String>[] strList = new ArrayList[MAX];

    public static void main(String[] args) throws Exception {
        init();
        Arrays.sort(strList, 0, N, (o1, o2) -> compare(o1, o2));
        print();
    }

    private static void print() {
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (String s : strList[i]) sb.append(s);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        String s;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            strList[i] = new ArrayList<>();

            for (int j = 0; j < s.length(); j++) {
                sb = new StringBuilder();
                boolean flag = true;
                while (j < s.length() && '0' <= s.charAt(j) && s.charAt(j) <= '9') {
                    flag = false;
                    sb.append(s.charAt(j++));
                }
                if (!flag) j--;
                else sb.append(s.charAt(j));

                strList[i].add(sb.toString());
            }
        }
    }

    private static int compare(ArrayList<String> arr1, ArrayList<String> arr2) {
        int size1 = arr1.size();
        int size2 = arr2.size();

        int i = 0, j = 0;

        for (; i < size1 && j < size2; i++, j++) {
            String str1 = arr1.get(i);
            String str2 = arr2.get(j);

            if (str1.equals(str2)) continue;

            boolean isNum1 = isNum(str1);
            boolean isNum2 = isNum(str2);

            // 둘다 숫자인 경우
            if (isNum1 && isNum2) {
                String newStr1 = str1.replaceAll("^0+", "");
                String newStr2 = str2.replaceAll("^0+", "");

                // 길이가 긴 것이 더 큰 숫자
                if (newStr1.length() != newStr2.length())
                    return newStr1.length() - newStr2.length();

                // 길이가 같다면 값이 작은게 우선순위가 높음
                for (int a = 0, b = 0; a < newStr1.length() && b < newStr2.length(); a++, b++) {
                    if (newStr1.charAt(a) != newStr2.charAt(b)) return newStr1.charAt(a) - newStr2.charAt(b);
                }

                // 숫자가 같다면 0의 개수가 작은것이 앞순위
                return str1.length() - str2.length();
            }

            // 둘다 문자인 경우
            if (!isNum1 && !isNum2) {
                char c1 = str1.charAt(0);
                char c2 = str2.charAt(0);

                boolean isUpper1 = c1 - 'a' >= 0 ? false : true;
                boolean isUpper2 = c2 - 'a' >= 0 ? false : true;

                int n1 = c1 - 'a' >= 0 ? c1 - 'a' : c1 - 'A';
                int n2 = c2 - 'a' >= 0 ? c2 - 'a' : c2 - 'A';

                if ((isUpper1 && isUpper2) || (!isUpper1 && !isUpper2)) return n1 - n2;

                if (!isUpper1 && isUpper2) {
                    //c1,c2가 같은 문자일 경우
                    if (n1 == n2)
                        return 1;

                    //다른 문자일 경우
                    return n1 - n2;
                }
                if (isUpper1 && !isUpper2) {
                    //c1,c2가 같은 문자일 경우
                    if (n1 == n2)
                        return -1;

                    //다른 문자일 경우
                    return n1 - n2;
                }
            }

            // 한쪽만 숫자, 한쪽만 문자
            if (!isNum1 && isNum2) return 1;
            if (isNum1 && !isNum2) return -1;
        }

        // 같은 문자열이 포함되지만 뒤에 다른 문자열이 더 붙는 경우 후순위
        if(size1 != i) {
            return 1;
        }
        if(size2 != j) {
            return -1;
        }
        return 0;
    }

    private static boolean isNum(String s) {
        if ('0' <= s.charAt(0) && s.charAt(0) <= '9') return true;
        return false;
    }
}
