package divideAndConquer.n2447;

import java.io.*;
public class Main {
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        draw(0, 0, N,false);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void draw(int r, int c, int n, boolean isCenter) {
        if(n == 1) {
            if(isCenter) map[r][c] = ' ';
            else map[r][c] = '*';
        } else {
            int cut = n/3;
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    boolean flag = isCenter;
                    if(!isCenter) flag = i==1 && j== 1 ? true : false;
                    draw(r+(i*cut), c+(j*cut), cut, flag);
                }
            }
        }
    }
}
