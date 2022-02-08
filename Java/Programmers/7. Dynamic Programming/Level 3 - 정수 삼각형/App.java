class Solution {
    public int solution(int[][] triangle) {

        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n][n];

        dp[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][j];
                else if (j == i) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);

                dp[i][j] += triangle[i][j];
                if (dp[i][j] > answer) answer = dp[i][j];
            }
        }
        return answer;
    }
}
