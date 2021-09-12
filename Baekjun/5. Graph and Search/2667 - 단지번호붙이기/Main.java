import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int n, count;
    static int[][] map;
    static boolean[][] visit;
    static ArrayList<Integer> counts = new ArrayList<>();
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(Character.toString(line.charAt(j)));
            }
        }
    }

    static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    count = 0;
                    dfs(i, j);
                    counts.add(count);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(counts.size()).append('\n');
        Collections.sort(counts);
        for (int num : counts) {
            sb.append(num).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (isValuable(nx, ny) && !visit[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny); 
            }
        }
    }

    static boolean isValuable(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
