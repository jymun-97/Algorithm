class Solution {

    int[][] map;
    int[] answer;
    int index = 0;

    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        map = new int[rows + 1][columns + 1];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = (i - 1) * columns + j;
            }
        }

        for (int[] query : queries) rotate(query[0], query[1], query[2], query[3]);

        return answer;
    }

    void rotate(int r1, int c1, int r2, int c2) {
        int[] corners = { map[r1][c1], map[r1][c2], map[r2][c2], map[r2][c1] };
        int min = Math.min(Math.min(corners[0], corners[1]), Math.min(corners[2], corners[3]));

        for (int i = c2; i > c1; i--) {
            map[r1][i] = map[r1][i - 1];
            if (min > map[r1][i]) min = map[r1][i];
        }
        for (int i = r2; i > r1; i--) {
            map[i][c2] = map[i - 1][c2];
            if (min > map[i][c2]) min = map[i][c2];
        }
        for (int i = c1; i < c2; i++) {
            map[r2][i] = map[r2][i + 1];
            if (min > map[r2][i]) min = map[r2][i];
        }
        for (int i = r1; i < r2; i++) {
            map[i][c1] = map[i + 1][c1];
            if (min > map[i][c1]) min = map[i][c1];
        }
        
        map[r1 + 1][c2] = corners[1];
        map[r2][c2 - 1] = corners[2];
        map[r2 - 1][c1] = corners[3];

        answer[index++] = min;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        System.out.println(s.solution(rows, columns, queries));
    }
}
