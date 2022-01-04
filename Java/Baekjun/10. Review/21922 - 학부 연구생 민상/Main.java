import java.util.*;
import java.io.*;

public class Main {
    static final int up = 1, down = 2, left = 3, right = 4;

    static int n, m, count;
    static ArrayList<Integer> startR = new ArrayList<>();
    static ArrayList<Integer> startC = new ArrayList<>();
    static int[][] map;
    static boolean[][] ans;
    static boolean[][] visit;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        ans = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    startR.add(i);
                    startC.add(j);
                }
            }
        }
    }

    static void solve() {
        for (int i = 0; i < startR.size(); i++) {
            int r = startR.get(i);
            int c = startC.get(i);
            for (int x = 1; x <= 4; x++) {
                visit = new boolean[n + 1][m + 1];
                dfs(r, c, x);
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ans[i][j]) count++;
            }
        }
        System.out.println(count);
        // print();
    }

    static void print() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ans[i][j]) System.out.print("O ");
                else System.out.print("X ");
            }
            System.out.println();
        }
    }

    static void dfs(int row, int col, int dir) {
        visit[row][col] = true;
        ans[row][col] = true;

        if (dir == 0) return;

        int nr = row, nc = col, nd = dir;
        switch (dir) {
            case up: nr--; break;
            case down: nr++; break;
            case left: nc--; break;
            case right: nc++; break;

            default: break; 
        }
        if (!isValuable(nr, nc) || visit[nr][nc]) return;

        if (map[nr][nc] == 1 && (dir == left || dir == right)) nd = 0;
        if (map[nr][nc] == 2 && (dir == up || dir == down)) nd = 0;
        if (map[nr][nc] == 3) {
            switch (dir) {
                case up: nd = right; break;
                case down: nd = left; break;
                case right: nd = up; break;
                default: nd = down; break;
            }
        }
        if (map[nr][nc] == 4) {
            switch (dir) {
                case up: nd = left; break;
                case down: nd = right; break;
                case right: nd = down; break;
                default: nd = up; break;
            }
        }

        dfs(nr, nc, nd);
    }

    static boolean isValuable(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= m; 
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}