import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 1000000000;
    static int n;
    static long[][][] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][10][1 << 10];
    }

    static void solve() {
        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int end = 0; end < 10; end++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int bit = k | (1 << end);

                    if (end == 0) {
                        dp[i][end][bit] = (dp[i][end][bit] + dp[i - 1][end + 1][k]) % MOD;
                    } else if (end == 9) {
                        dp[i][end][bit] = (dp[i][end][bit] + dp[i - 1][end - 1][k]) % MOD;
                    } else {
                        dp[i][end][bit] = (dp[i][end][bit] + dp[i - 1][end - 1][k] + dp[i - 1][end + 1][k]) % MOD;
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[n][i][1023]) % MOD;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}