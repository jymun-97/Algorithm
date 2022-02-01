import java.util.*;
import java.io.*;

public class Main {
    static int n, max = 1001;
    static long[] ans;
    static long[][] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = new long[max];
        dp = new long[max][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
            ans[1] += 1;
        }
    }

    static void solve() {
        if (n == 1) {
            System.out.println(ans[1]);
            return;
        }

        for (int x = 2; x <= n; x++) {
            for (int y = 0; y < 10; y++) {
                for (int k = y; k < 10; k++) {
                    dp[x][y] += dp[x - 1][k] % 10007;
                }
                ans[x] += dp[x][y];
            }
            ans[x] %= 10007;
        }

        System.out.println(ans[n]);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}