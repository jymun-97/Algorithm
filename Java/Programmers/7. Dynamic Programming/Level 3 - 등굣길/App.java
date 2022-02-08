class Solution {
    int[][] map;
    int[][] dp;
    int n, m;
    final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        this.n = n;
        this.m = m;

        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        for (int[] puddle : puddles) map[puddle[1]][puddle[0]] = 1;

        return dfs(1, 1);
    }

    int dfs(int row, int col){
        if (row == n && col == m) return 1;
        
        for (int i = 0; i < 2; i++) {
            int nr = row + 1 - i;
            int nc = col + i;

            if (nr > n || nc > m) continue;
            if (map[nr][nc] != 1) {
                if (dp[nr][nc] > 0) dp[row][col] = (dp[row][col] + dp[nr][nc]) % MOD;
                else dp[row][col] = (dp[row][col] + dfs(nr, nc)) % MOD;
            }
        }

        return dp[row][col];
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};

        System.out.println(s.solution(m, n, puddles));
    }
}
