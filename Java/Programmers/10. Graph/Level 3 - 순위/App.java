class Solution {

    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] connect = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            connect[result[0]][result[1]] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (connect[i][k] && connect[k][j]) {
                        connect[i][j] = true;
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (connect[i][j] || connect[j][i]) count++;
            }
            if (count == n - 1) answer++;
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        System.out.println(s.solution(n, results));
    }
}
