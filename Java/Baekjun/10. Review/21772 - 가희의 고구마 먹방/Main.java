import java.util.*;
import java.io.*;

public class Main {
    static int r, c, t, ans, total;
    static int sr, sc, count;
    static char[][] map;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, -1}, {0, 1} };

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new char[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            String line = br.readLine();
            for (int j = 1; j <= c; j++) {
                map[i][j] = line.charAt(j - 1);
                if (map[i][j] == 'G') {
                    sr = i;
                    sc = j;
                }
                if (map[i][j] == 'S') total++;
            }
        }
    }

    static void solve() {
        dfs(sr, sc, 0);
        System.out.println(ans);
    }

    static void dfs(int row, int col, int move) {
        if (move > t || count == total) {
            ans = Math.max(ans, count);
            return;
        }

        boolean flag = false;
        if (map[row][col] == 'S') {
            count++;
            map[row][col] = 's';
            flag = true;
        }
        for (int i = 0; i < 4; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (nr < 1 || nr > r || nc < 1 || nc > c) continue;
            if (map[nr][nc] == '#') continue;

            dfs(nr, nc, move + 1);
        }
        if (flag) {
            count--;
            map[row][col] = 'S';
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}