import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, ans = -1;
    static boolean flag;
    static char[][] map;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R + 1][C + 1];

        int count = 0;
        for (int i = 1; i <= R; i++) {
            String line = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = line.charAt(j - 1);

                if (map[i][j] == '0') count++;
            }
        }
        if (count < R + C - 1) flag = true;
    }

    static void solve() {
        if (flag) {
            System.out.println(-1);
            return;
        }
        
        bfs();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == '1') {
                    map[i][j] = '0';
                    bfs();
                    map[i][j] = '1';
                }
            }
        } 
        
        System.out.println(ans);
    }

    static void bfs() {
        int[][] dist = new int[R + 1][C + 1];
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        que.add(1);
        dist[1][1] = 1;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr <= 0 || nr > R || nc <= 0 || nc > C) continue;
                if (dist[nr][nc] == 0 && map[nr][nc] == '0') {
                    que.add(nr);
                    que.add(nc);
                    dist[nr][nc] = dist[row][col] + 1;
                }
            }
        }

        if (dist[R][C] == R + C) {
            ans = R + C;
            return;
        }
        if (dist[R][C] != 0) {
            if (ans == -1) ans = dist[R][C];
            else ans = Integer.min(ans, dist[R][C]);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
