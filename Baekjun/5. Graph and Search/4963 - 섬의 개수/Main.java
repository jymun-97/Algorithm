import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][] map;
    static boolean[][] visit;
    static int[][] dir = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();


    static void input() throws IOException {
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        int count = 0;   
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    dfs(i, j);
                    count ++;
                }
            }
        }
        sb.append(count).append('\n');
    }

    static void dfs(int y, int x) {
        visit[y][x] = true;

        for (int i = 0; i < dir.length; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
            if (map[ny][nx] == 1 && !visit[ny][nx]) {
                dfs(ny, nx);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) break;

            map = new int[h][w];
            visit = new boolean[h][w];

            input();
            solve();
        }

        System.out.println(sb);
    }
}
