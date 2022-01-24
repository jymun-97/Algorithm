import java.util.*;

class Solution {

    public int solution(int[][] board, int[] moves) {
        int n = board.length;
        Queue<Integer>[] lines = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) lines[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 0) continue;
                lines[i + 1].add(board[j][i]);
            }
        }

        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            if (lines[move].isEmpty()) continue;

            int target = lines[move].poll();
            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                answer += 2;
            }
            else stack.push(target);
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};

        System.out.println(s.solution(board, moves));
    }
}
