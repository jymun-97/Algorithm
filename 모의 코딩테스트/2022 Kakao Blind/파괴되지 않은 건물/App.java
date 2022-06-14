class Solution {

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        for (int[] s : skill) {
            int sr = s[1], sc = s[2], er = s[3], ec = s[4];
            int degree = (s[0] == 1) ? -s[5] : s[5];

            sum[sr][sc] += degree;
            sum[er + 1][ec + 1] += degree;
            sum[sr][ec + 1] -= degree; 
            sum[er + 1][sc] -= degree;
        }

        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[0].length - 1; j++) {  
                sum[i][j + 1] += sum[i][j];
            }
        }

        for (int j = 0; j < sum[0].length; j++) {
            for (int i = 0; i < sum.length - 1; i++) {
                sum[i + 1][j] += sum[i][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};

        System.out.println(s.solution(board, skill));
    }
}
