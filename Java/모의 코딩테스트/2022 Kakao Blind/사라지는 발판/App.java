class Result {
    boolean win;
    int nMove;
    public Result(boolean win, int nMove) {
        this.win = win;
        this.nMove = nMove;
    }
}

class Solution {

    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int n, m;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;

        return move(board, aloc[0], aloc[1], bloc[0], bloc[1]).nMove;
    }

    Result move(int[][] map, int row, int col, int other_row, int other_col) {
        if (cantWin(map, row, col)) return new Result(false, 0);
        if (row == other_row && col == other_col) return new Result(true, 1);

        boolean flag = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (!inRange(nr, nc) || map[nr][nc] == 0) continue;

            map[row][col] = 0;
            Result result = move(map, other_row, other_col, nr, nc);
            map[row][col] = 1;

            if (!result.win) {
                flag = true;
                min = Math.min(min, result.nMove);
            }
            else {
                max = Math.max(max, result.nMove);
            }
        }

        return flag ? new Result(flag, min + 1) : new Result(flag, max + 1);
    }

    boolean cantWin(int[][] map, int row, int col) {
        for (int i = 0; i < 4; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (inRange(nr, nc) && map[nr][nc] == 1) return false;
        }
        return true;
    }

    boolean inRange(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}
