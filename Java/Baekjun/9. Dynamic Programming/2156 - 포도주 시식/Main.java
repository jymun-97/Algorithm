import java.util.*;
import java.io.*;

public class Main {
    static int n, max = 10001;
    static int[] cost;
    static int[][] dp = new int[max][2];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        // dp[i][0] : i번째 와인 O, i-1번째 와인 O
        // dp[i][1] : i번째 와인 O, i-1번째 와인 X

        dp[1][0] = dp[1][1] = cost[1];

        if (n > 1) {
            dp[2][0] = cost[1] + cost[2];
            dp[2][1] = cost[2];
        }

        for (int i = 3; i <= n; i++) {
            dp[i][0] = dp[i - 1][1] + cost[i];
            dp[i][1] = Integer.max(dp[i - 2][0], dp[i - 2][1]) + cost[i];
            dp[i][1] = Integer.max(dp[i][1], Integer.max(dp[i-3][0], dp[i-3][1]) + cost[i]);
        }

        int cand1 = Integer.max(dp[n - 1][0], dp[n - 1][1]);
        int cand2 = Integer.max(dp[n][0], dp[n][1]);

        System.out.println(Integer.max(cand1, cand2));
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}