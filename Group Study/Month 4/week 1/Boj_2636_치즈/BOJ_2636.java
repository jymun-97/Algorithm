import java.util.*;
import java.io.*;

public class BOJ_2636 {
    static int n, m, remain;
    static int[][] dir = {{1,0}, {0,1}, {-1, 0}, {0, -1}};
    static int[][] map;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 2][m + 2];

        for (int i = 0; i <= n + 1; i++) {
            if (i == 0 || i == n + 1) {
                Arrays.fill(map[i], 2);
                continue;
            }
        
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= m + 1; j++) {
                if (j == 0 || j == m + 1) {
                    map[i][j] = 2;
                    continue;
                }
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) remain++;
            }
        }
    }

    static void solve() {
        int ans = 0, last = 0;
        while (remain > 0) {
            last = remain;

            boolean[][] visit = new boolean[n + 2][m + 2];
            Queue<Integer> que = new LinkedList<>();
            que.add(0);
            que.add(0);
            visit[0][0] = true;

            while (!que.isEmpty()) {
                int row = que.poll();
                int col = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = row + dir[i][0];
                    int nc = col + dir[i][1];

                    if (nr < 0 || nr > n + 1 || nc < 0 || nc > m + 1) continue;
                    if (visit[nr][nc]) continue;
                    
                    if (map[nr][nc] == 1) {
                        remain--;
                    }
                    else {
                        que.add(nr);
                        que.add(nc);
                    }
                    visit[nr][nc] = true;
                    map[nr][nc] = 2;
                }
            }
            ans++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n').append(last);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}