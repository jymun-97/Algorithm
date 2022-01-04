import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int max = 11;
    static int n;
    static int[] dp;

    static void pre() {
        dp = new int[max];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < max; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

    }

    static void solve() {
        sb.append(dp[n]).append('\n');
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        pre();

        while (t-- > 0) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}