import java.util.*;
import java.io.*;

public class Main {

    static int r, c, h;
    static int[][][] map;
    static int[][][] dist;
    static Queue<Integer> que = new LinkedList<>();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[r][c][h];
        dist = new int[r][c][h];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < c; k++) {
                    map[j][k][i] = Integer.parseInt(st.nextToken());
                    if (map[j][k][i] == 1) {
                        que.add(j);
                        que.add(k);
                        que.add(i);
                        dist[j][k][i] = 1;
                    }
                }
            }
        }
    }

    static void solve() {
        int[][] dir = { {0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0} };

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            int hei = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];
                int nh = hei + dir[i][2];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c || nh < 0 || nh >= h) continue;
                if (map[nr][nc][nh] == 0 && dist[nr][nc][nh] == 0) {
                    map[nr][nc][nh] = 1;
                    dist[nr][nc][nh] = dist[row][col][hei] + 1;

                    que.add(nr);
                    que.add(nc);
                    que.add(nh);
                } 
            }
        }

        int ans = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < h; k++) {
                    if (map[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    if (ans < dist[i][j][k]) ans = dist[i][j][k];
                }
            }
        }
        System.out.println(ans - 1);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}