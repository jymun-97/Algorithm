import java.util.*;
import java.io.*;

public class Main {
    static int n, max = 91;
    static long[][] dp = new long[max][2];
    // dp[i][0] : i자리가 0일때 이천수 개수
    // dp[i][1] : i자리가 1일때 이천수 개수

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
    }

    static void solve() {
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[n][0] + dp[n][1]);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}