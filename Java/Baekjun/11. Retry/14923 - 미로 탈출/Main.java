import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int sr, sc, er, ec;
    static int[][] map;
    static int[][][] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1][2];

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j][0] = Integer.MAX_VALUE;
                dist[i][j][1] = Integer.MAX_VALUE;
            }
        }
    }

    static void solve() {
        int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        Queue<Integer> que = new LinkedList<>();
        que.add(sr);
        que.add(sc);
        que.add(0);
        dist[sr][sc][0] = dist[sr][sc][1] = 0;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            int used = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
                
                if (map[nr][nc] == 1) {
                    if (used == 1 || dist[nr][nc][1] != Integer.MAX_VALUE) continue;

                    que.add(nr);
                    que.add(nc);
                    que.add(1);
                    dist[nr][nc][1] = dist[row][col][0] + 1;
                }
                else {
                    if (dist[nr][nc][used] != Integer.MAX_VALUE) continue;
                    
                    que.add(nr);
                    que.add(nc);
                    que.add(used);
                    dist[nr][nc][used] = dist[row][col][used] + 1;
                }
            }
        }

        int ans = Math.min(dist[er][ec][0], dist[er][ec][1]);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}