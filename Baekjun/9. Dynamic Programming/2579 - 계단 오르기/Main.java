import java.util.*;
import java.io.*;

public class Main {
    static int n, max = 301;
    static int[] score;
    static int[][] dp;
    static int[][][] come;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        score = new int[max + 1];
        dp = new int[max][2];

        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        dp[1][0] = 0;
        dp[1][1] = score[1];

        dp[2][0] = score[2];
        dp[2][1] = score[1] + score[2];

        for (int i = 3; i <= n; i++) {
            dp[i][0] = Integer.max(dp[i - 2][0], dp[i - 2][1]) + score[i];
            dp[i][1] = dp[i - 1][0] + score[i];
        }
        System.out.println(Integer.max(dp[n][0], dp[n][1]));

        int i = n;
        System.out.println("Back Track");
        while (i != 0) {
            System.out.println(i);
            int from = (dp[i][0] > dp[i][1]) ? i - 2 : i - 1;
            i = from;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}