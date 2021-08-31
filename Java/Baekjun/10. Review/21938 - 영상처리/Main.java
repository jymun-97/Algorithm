import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static boolean[][] visit;
    static int[][][] info;
    static int n, m, t;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];
        info = new int[n + 1][m + 1][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++) {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                info[i][j][0] = r;
                info[i][j][1] = g;
                info[i][j][2] = b;
            }
        }

        t = Integer.parseInt(br.readLine());
    }

    static void solve() {
        initMap();

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);
    }

    static void initMap() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int r = info[i][j][0];
                int g = info[i][j][1];
                int b = info[i][j][2];

                map[i][j] = ((r + g + b) / 3 >= t) ? 1 : 0; // 1 -> 물체
            }
        }
    }

    static void bfs(int r, int c) {
        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        Queue<Integer> que = new LinkedList<>();
        que.add(r);
        que.add(c);
        visit[r][c] = true;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
                if (!visit[nr][nc] && map[nr][nc] == 1) {
                    visit[nr][nc] = true;
                    que.add(nr);
                    que.add(nc);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}