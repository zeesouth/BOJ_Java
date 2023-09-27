package simulation.n10875;

import java.io.*;
import java.util.*;

public class Main {
    static final int VERTICAL = 0, HORIZON = 1;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static ArrayList<Line> lines = new ArrayList<>();
    static long L, x, y, ans;
    static int d;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        L = Long.parseLong(br.readLine());

        lines.add(new Line(-L - 1, L + 1, L + 1, L + 1));
        lines.add(new Line(L + 1, -L - 1, L + 1, L + 1));
        lines.add(new Line(-L - 1, -L - 1, L + 1, -L - 1));
        lines.add(new Line(-L - 1, -L - 1, -L - 1, L + 1));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            long time = Long.MAX_VALUE;
            char dir = 'L';
            if (i < N) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                time = Long.parseLong(st.nextToken());
                dir = st.nextToken().charAt(0);
            }
            if (!simulate(time, dir)) break;
        }
        System.out.println(ans);
    }

    private static boolean simulate(long time, char dir) {
        // 부딪치기 전까지 움직일 수 있는 거리
        long dist = Long.MAX_VALUE;

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);

            if (line.dir == HORIZON) {
                // 오른
                if (d == 0) {
                    if (y == line.y1 && x < line.x1) {
                        dist = Math.min(dist, line.x1 - x);
                    }
                }
                // 아래
                else if (d == 1) {
                    if (line.x1 <= x && x <= line.x2 && line.y1 < y) {
                        dist = Math.min(dist, y - line.y1);
                    }
                }
                // 왼
                else if (d == 2) {
                    if (y == line.y1 && line.x2 < x) {
                        dist = Math.min(dist, x - line.x2);
                    }
                }
                // 위
                else {
                    if (line.x1 <= x && x <= line.x2 && y < line.y1) {
                        dist = Math.min(dist, line.y1 - y);
                    }
                }
            } else {
                // 오
                if (d == 0) {
                    if (line.y1 <= y && y <= line.y2 && x < line.x1) {
                        dist = Math.min(dist, line.x1 - x);
                    }
                }
                // 아래
                else if (d == 1) {
                    if (x == line.x1 && line.y2 < y) {
                        dist = Math.min(dist, y - line.y2);
                    }
                }
                // 왼
                else if (d == 2) {
                    if (line.y1 <= y && y <= line.y2 && line.x1 < x) {
                        dist = Math.min(dist, x - line.x1);
                    }
                }
                // 위
                else {
                    if (x == line.x1 && y < line.y1) {
                        dist = Math.min(dist, line.y1 - y);
                    }
                }
            }
        }

        if (time < dist) {
            lines.add(new Line(x, y, x + dx[d] * time, y + dy[d] * time));
            x = x + dx[d] * time;
            y = y + dy[d] * time;
            ans += time;

            if (dir == 'L') d = (d - 1 + 4) % 4;
            else d = (d + 1) % 4;

            return true;
        } else {
            ans += dist;

            return false;
        }
    }

    static class Line {
        long x1, y1, x2, y2;
        int dir;

        Line(long x1, long y1, long x2, long y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;

            if (x1 == x2) dir = VERTICAL;
            else dir = HORIZON;

            setPoint();
        }

        void setPoint() {
            if (this.x2 < this.x1) {
                long tmp = this.x2;
                this.x2 = this.x1;
                this.x1 = tmp;
            }

            if (this.y2 < this.y1) {
                long tmp = this.y2;
                this.y2 = this.y1;
                this.y1 = tmp;
            }
        }
    }
}
