class Solution {

    int[][] board;
    int[][] key, lock;
    int lock_row, lock_col;
    int key_row, key_col;

    public boolean solution(int[][] key, int[][] lock) {
        lock_row = lock_col = lock.length;
        key_row = key.length;
        key_col = key[0].length;
        board = new int[lock_row + 2 * key_row][lock_col + 2 * key_col];

        this.key = copyOf(key);
        this.lock = copyOf(lock);

        for (int row = key_row; row < key_row + lock_row; row++) {
            for (int col = key_col; col < key_col + lock_col; col++) {
                board[row][col] = lock[row - key_row][col - key_col];
            }
        }

        int nRotate = 4;
        while (nRotate --> 0) {
            for (int i = 0; i <= board.length - key_row; i++) {
                for (int j = 0; j <= board[0].length - key_col; j++) {
                    if (isPossible(i, j)) 
                        return true;
                }
            }
            rotate();
        }

        return false;
    }

    boolean isPossible(int sr, int sc) {
        int[][] map = copyOf(board);

        for (int row = sr; row < sr + key_row; row++) {
            for (int col = sc; col < sc + key_col; col++) {
                map[row][col] += key[row - sr][col - sc];
            }
        }
        for (int row = key_row; row < key_row + lock_row; row++) {
            for (int col = key_col; col < key_col + lock_col; col++) {
                if (map[row][col] != 1) return false;
            }
        }
        return true;
    }

    int[][] copyOf(int[][] map) {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }

    void rotate() {
        int[][] map = new int[key_col][key_row];

        for (int i = 0; i < key_row; i++) {
            for (int j = 0; j < key_col; j++) {
                map[j][key_col - i - 1] = key[i][j];
            }
        }

        int temp = key_row;
        key_row = key_col;
        key_col = temp;
        
        key = new int[key_row][key_col];
        key = copyOf(map);
    }

    void print(int[][] map) {
        System.out.println("============================");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] key = {{0,0,0}, {1,0,0}, {0,1,1}};
        int[][] lock = {{1,1,1}, {1,1,0}, {1,0,1}};

        System.out.println(s.solution(key, lock));
    }
}
