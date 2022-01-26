class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        int[][] dp = new int[n][2];
        
        if (n == 1) return sticker[0];
        dp[0][0] = 0;
        dp[0][1] = sticker[0];
        
        if (n > 1) {
            dp[1][0] = sticker[1];
            dp[1][1] = dp[0][1];

            if (n == 2) return Math.max(dp[1][0], dp[1][1]);
        }

        for (int i = 2; i < n - 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + sticker[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + sticker[i]);
        }

        dp[n - 1][0] = Math.max(dp[n - 2][0], dp[n - 3][0] + sticker[n - 1]);
        dp[n - 1][1] = Math.max(dp[n - 2][1], dp[n - 3][1]);

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
