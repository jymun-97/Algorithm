import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int max = 100001;
    static final int mod = 1000000009;
    static int n;
    static long[][] dp;

    static void pre() {
        dp = new long[max][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < max; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % mod;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % mod;
        }
    }
    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
    }

    static void solve() {
        long ans = (dp[n][1] + dp[n][2] + dp[n][3]) % mod;
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        pre();

        while (t --> 0) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}