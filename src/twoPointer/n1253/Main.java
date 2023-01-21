package twoPointer.n1253;

import java.io.*;
import java.util.*;

public class Main {

    static int N, ans;
    static HashSet<Integer> goodNumber;
    static HashMap<Integer, Integer> count;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        count = new HashMap<>();
        arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int currNum = Integer.parseInt(st.nextToken());
            if(arr.contains(currNum)) count.replace(currNum, count.get(currNum)+1);
            else count.put(currNum, 1);
            arr.add(currNum);
        }
        Collections.sort(arr);
        goodNumber = new HashSet<>();

        ans = 0;
        bs(0, arr.size()-1);
        bw.write(ans+"");
        bw.flush();
    }

    static void bs(int start, int end) {
        if(start >= end) return;

        int currNum = arr.get(start) + arr.get(end);
        if(arr.contains(currNum) && !goodNumber.contains(currNum)) {
            if(arr.indexOf(currNum) == start || arr.indexOf(currNum) == end) {
                if(arr.get(start) == arr.get(end)) {
                    if(count.get(currNum) > 2) {
                        ans += count.get(currNum);
                        goodNumber.add(currNum);
                    }
                } else {
                    if(count.get(currNum) > 1) {
                        ans += count.get(currNum);
                        goodNumber.add(currNum);
                    }
                }
            } else {
                ans += count.get(currNum);
                goodNumber.add(currNum);
            }
        }
        bs(start, end-1);
        bs(start+1, end);
    }
}
