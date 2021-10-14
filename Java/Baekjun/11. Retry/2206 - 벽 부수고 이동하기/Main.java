import java.util.*;
import java.io.*;

public class Main {


    static int n, m;
    static char[][] map;
    static int[][][] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n + 1][m + 1];
        dist = new int[2][n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = line.charAt(j - 1);
                dist[0][i][j] = dist[1][i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static void solve() {
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        que.add(1);
        que.add(0);
        dist[0][1][1] = 1;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            int used = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];
                
                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;

                if (map[nr][nc] == '0') {
                    if (dist[used][nr][nc] != Integer.MAX_VALUE) continue;

                    que.add(nr);
                    que.add(nc);
                    que.add(used);
                    dist[used][nr][nc] = dist[used][row][col] + 1;
                }
                else {
                    if (used == 1 || dist[1][nr][nc] != Integer.MAX_VALUE) continue;

                    que.add(nr);
                    que.add(nc);
                    que.add(1);
                    dist[1][nr][nc] = dist[0][row][col] + 1;
                }
            }
        }
        int ans = Math.min(dist[0][n][m], dist[1][n][m]);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}