import java.util.*;
import java.io.*;

public class Main {
    static final int R = 0, G = 1, B = 2;
    static int n, max = 1001;
    static int[][] cost;
    static int[][] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cost = new int[n + 1][3];
        dp = new int[max][3];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][R] = Integer.parseInt(st.nextToken());
            cost[i][G] = Integer.parseInt(st.nextToken());
            cost[i][B] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve() {
        dp[1][R] = cost[1][R];
        dp[1][G] = cost[1][G];
        dp[1][B] = cost[1][B];
        dp[2][R] = Integer.min(cost[1][G], cost[1][B]) + cost[2][R];
        dp[2][G] = Integer.min(cost[1][R], cost[1][B]) + cost[2][G];
        dp[2][B] = Integer.min(cost[1][G], cost[1][R]) + cost[2][B];

        for (int i = 3; i <= n; i++) {
            dp[i][R] = Integer.min(dp[i-1][G], dp[i-1][B]) + cost[i][R];
            dp[i][G] = Integer.min(dp[i-1][R], dp[i-1][B]) + cost[i][G];
            dp[i][B] = Integer.min(dp[i-1][G], dp[i-1][R]) + cost[i][B];
        }

        int ans = Integer.min(dp[n][R], Integer.min(dp[n][G], dp[n][B]));
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}