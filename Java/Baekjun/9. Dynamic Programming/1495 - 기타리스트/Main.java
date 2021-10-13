import java.util.*;
import java.io.*;

public class Main {
    static int n, s, m;
    static boolean[][] dp;
    static int[] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new boolean[n + 1][m + 1];
        a = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        if (s - a[1] >= 0) {
            dp[1][s - a[1]] = true;
        }
        if (s + a[1] <= m) {
            dp[1][s + a[1]] = true;
        }

        for (int i = 2; i <= n; i++) {
            boolean flag = false;
            for (int x = 0; x <= m; x++) {
                if (dp[i - 1][x]) {
                    flag = true;

                    if (x - a[i] >= 0) {
                        dp[i][x - a[i]] = true;
                    }
                    if (x + a[i] <= m) {
                        dp[i][x + a[i]] = true;
                    }
                }
            }
            if (!flag) {
                System.out.println(-1);
                return; 
            }
        }

        for (int x = m; x >= 0; x--) {
            if (dp[n][x]) {
                System.out.println(x);
                return;
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}