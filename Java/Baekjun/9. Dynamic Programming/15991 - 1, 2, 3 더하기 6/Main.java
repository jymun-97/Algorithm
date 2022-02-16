import java.util.*;
import java.io.*;

public class Main {
    static int max = 100001;
    static long[] dp = new long[max];

    static void pre() {
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;

        for (int i = 4; i < max; i++) {
            if (i - 2 >= 0) {
                dp[i] += dp[i - 2];
                if (i - 4 >= 0) {
                    dp[i] += dp[i - 4];
                    if (i - 6 >= 0) {
                        dp[i] += dp[i - 6];
                    }
                }
            }
            dp[i] %= 1000000009;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        pre();

        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append('\n');
        }
        System.out.println(sb);
    }
}