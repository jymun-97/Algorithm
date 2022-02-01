import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int k; // 3~500
    static int[] files; // 1~10000 for each
    static int[][] sum; 
    static int[][] dp;

    static void input() throws IOException {
        k = Integer.parseInt(br.readLine());
        files = new int[k + 1];
        sum = new int[k + 1][k + 1];
        dp = new int[k + 1][k + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= k; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve() {
        prep();

        int end = 1;
        while (end <= k) {
            for (int i = 1, j = end; j <= k; i++, j++) {
                if (i == j) continue;

                int min = Integer.MAX_VALUE;
                for (int x = i; x < j; x++) {
                    min = min > dp[i][x] + dp[x + 1][j] ? dp[i][x] + dp[x + 1][j] : min;
                }
                dp[i][j] = (min == Integer.MAX_VALUE) ? sum[i][j] : min + sum[i][j];
            }
            end++;
        }
        sb.append(dp[1][k]).append('\n');
    }

    static void prep() {
        for (int i = 1; i < k; i++) {
            sum[i][i] = files[i];
            for (int j = i + 1; j <= k; j++) {
                sum[i][j] = sum[i][j - 1] + files[j];
            }
        }
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