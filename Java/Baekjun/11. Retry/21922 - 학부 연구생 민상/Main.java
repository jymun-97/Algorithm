import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static boolean[][][] visit;
    static boolean[][] check;
    static int n, m, ans = 0;
    static Queue<Integer> start = new LinkedList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m][5];
        check = new boolean[n][m];
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    start.add(i);
                    start.add(j);
                }
            }
        }
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        while (!start.isEmpty()) {
            int sr = start.poll();
            int sc = start.poll();
            check[sr][sc] = true;
            ans++;

            for (int i = 1; i <= 4; i++) {
                que.add(sr);
                que.add(sc);
                que.add(i);
                visit[sr][sc][i] = true;
            }
        }

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            int dir = que.poll();

            int[] next_loc = getNextLoc(row, col, dir);
            int nr = next_loc[0];
            int nc = next_loc[1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            int nd = getNextDir(nr, nc, dir);

            if (visit[nr][nc][nd]) continue;

            que.add(nr);
            que.add(nc);
            que.add(nd);
            visit[nr][nc][nd] = true;
            if (!check[nr][nc]) {
                ans++;
                check[nr][nc] = true;
            }
        }
        
        System.out.println(ans);
    }

    static int[] getNextLoc(int row, int col, int dir) {
        switch (dir) {
            case 1:
            return new int[] {row, col + 1};

            case 2:
            return new int[] {row, col - 1};

            case 3:
            return new int[] {row - 1, col};

            default:
            return new int[] {row + 1, col};
        }
    }

    static int getNextDir(int row, int col, int dir) {
        if (map[row][col] == 0 || map[row][col] == 9) return dir;

        if (map[row][col] == 1) {
            if (dir == 1 || dir == 2) return 3 - dir;
            return dir;
        }
        else if (map[row][col] == 2) {
            if (dir == 3 || dir == 4) return 7 - dir;
            return dir;
        }
        else if (map[row][col] == 3) {
            if (dir == 1) return 3;
            else if (dir == 2) return 4;
            else if (dir == 3) return 1;
            else return 2;
        }
        else {
            if (dir == 1) return 4;
            else if (dir == 2) return 3;
            else if (dir == 3) return 2;
            else return 1;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}