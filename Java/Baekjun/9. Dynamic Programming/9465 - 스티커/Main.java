import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, max = 100001;
    static int[][] cost;
    static int[][] dp;

    static void input() throws IOException {

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        cost = new int[n + 1][2];

        for (int row = 0; row < 2; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= n; col++) {
                cost[col][row] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        if (n > 1) {
            dp[2][0] = cost[1][1] + cost[2][0];
            dp[2][1] = cost[1][0] + cost[2][1];
        }

        for (int i = 3; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 2][1]) + cost[i][0];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 2][0]) + cost[i][1];
        }

        int cand1 = Math.max(dp[n][0], dp[n][1]);
        int cand2 = Math.max(dp[n - 1][0], dp[n - 1][1]);
        sb.append(Math.max(cand1, cand2)).append('\n');
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