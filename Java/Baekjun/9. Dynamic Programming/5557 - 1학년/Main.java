import java.util.*;
import java.io.*;


public class Main {

    static int n;
    static int[] a;
    static long[][] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        dp = new long[n][21];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        dp[1][a[1]] = 1;

        for (int i = 2; i < n; i++) {
            for (int x = 0; x <= 20; x++) {
                if (x - a[i] >= 0) {
                    dp[i][x] += dp[i - 1][x - a[i]];
                }
                if (x + a[i] < 21) {
                    dp[i][x] += dp[i - 1][x + a[i]];
                }
            }
        }
        System.out.println(dp[n - 1][a[n]]);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}