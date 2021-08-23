import java.util.*;
import java.io.*;

public class Main {
    static final int UP = 0, DOWN = 1;
    static int n, m;
    static int[][] a;
    static long[][] dp_as;
    static long[][] dp_des;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n + 1][m + 1];
        dp_as = new long[n + 1][m + 1];
        dp_des = new long[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        if (n == 1 && m == 1) {
            System.out.println(a[n][m] * 2);
            return;
        }
        ascendFlight();
        descentFlight();

        long ans = 0L;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                ans = (ans < dp_as[i][j] + dp_des[i][j]) ? dp_as[i][j] + dp_des[i][j] : ans;
            }
        }

        System.out.println(ans);
    }

    static void ascendFlight() {
        int[][] dir = { {-1, 0}, {0, 1} };
        Queue<Integer> que = new LinkedList<>();
        que.add(n);
        que.add(1);
        dp_as[n][1] = a[n][1];

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            
            for (int i = 0; i < 2; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
                if (nr == n && nc == m) continue;

                if (dp_as[nr][nc] <= dp_as[row][col] + a[nr][nc]) {
                    dp_as[nr][nc] = dp_as[row][col] + a[nr][nc];
                    que.add(nr);
                    que.add(nc);
                }
            }
        } 
    }

    static void descentFlight() {
        int[][] dir = { {-1, 0}, {0, -1} };
        Queue<Integer> que = new LinkedList<>();
        que.add(n);
        que.add(m);
        dp_des[n][m] = a[n][m];

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            
            for (int i = 0; i < 2; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
                if (nr == n && nc == 1) continue;

                if (dp_des[nr][nc] <= dp_des[row][col] + a[nr][nc]) {
                    dp_des[nr][nc] = dp_des[row][col] + a[nr][nc];
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