import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] nums;
    static boolean[][] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        dp = new boolean[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) nums[i] = Integer.parseInt(st.nextToken());
    }   

    static void pre() {
        int size = 2;
        while (size <= n) {
            for (int start = 1; start + size - 1 <= n; start++) {
                int end = start + size - 1;
                
                if (nums[start] == nums[end]) {
                    if (size == 2) dp[start][end] = true;
                    if (size > 2 && dp[start + 1][end - 1])
                        dp[start][end] = true;
                }
            }
            size += 2;
        }
    }

    static void solve() {
        pre();

        int start = 1, end = 2;
        int count = 0;
        while (end <= n) {
            while (!dp[start][end]) {
                end += 2;
                if (end > n) {
                    System.out.println(-1);
                    return;
                }
            }
            count++;
            start = end + 1;
            end = start + 1;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}