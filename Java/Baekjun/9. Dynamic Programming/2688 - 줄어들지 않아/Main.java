import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, max = 65;
    static long[][] dp;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][10];
    }

    static void solve() {
        for (int i = 0; i < 10; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++)
            ans += dp[n][i];
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            input();
            solve();
        }

        System.out.println(sb);
    }
}