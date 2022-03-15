import java.util.*;
import java.io.*;

public class BOJ_17086 {

    static int n, m;
    static int[][] map;
    static int[][] dist;
    static Queue<Integer> start = new LinkedList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    start.add(i);
                    start.add(j);
                }
            }
        }
    }

    static void solve() {
        int[][] dir = { { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { -1, 1 } };
        Queue<Integer> que = new LinkedList<>();
        while (!start.isEmpty()) {
            que.add(start.poll());
            que.add(start.poll());
        }

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc] == 1) continue;

                if (dist[nr][nc] == 0) {
                    dist[nr][nc] = dist[row][col] + 1;
                    que.add(nr);
                    que.add(nc);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, dist[i][j]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}