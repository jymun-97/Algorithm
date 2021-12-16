import java.util.*;

class Solution {

    final Integer PILLAR = Integer.valueOf(0);
    final Integer BOARD = Integer.valueOf(1);
    ArrayList<Integer>[][] map;
    int n;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        map = new ArrayList[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int target = frame[2];
            
            if (frame[3] == 0) remove(x, y, target);
            else create(x, y, target);
        }
        
        ArrayList<int[]> result = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                Collections.sort(map[x][y]);
                for (int target : map[x][y]) {
                    result.add(new int[] {x, y, target});
                }
            }
        }
        
        int size = result.size();
        int[][] answer = new int[size][size];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i).clone();
        }

        return answer;
    }

    void remove(int x, int y, int target) {
        int[][] dir = {{0, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        map[x][y].remove(Integer.valueOf(target));

        for (int i = 0; i <= 8; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx < 0 || nx > n || ny < 0 || ny > n) continue;
            for (int temp : map[nx][ny]){
                if (!isSafe(nx, ny, temp)) {
                    map[x][y].add(target);
                    return;
                }
            }
        }
    }

    void create(int x, int y, int target) {
        if (isSafe(x, y, target)) 
            map[x][y].add(target);
    }

    boolean isSafe(int x, int y, int target) {
        if (target == 0)
            return isSafePillar(x, y);
        else 
            return isSafeBoard(x, y);
    }

    boolean isSafePillar(int x, int y) {
        if (y == 0 || map[x][y - 1].contains(PILLAR)) return true;
        if (map[x][y].contains(BOARD)) return true;
        if (x > 0 && map[x - 1][y].contains(BOARD)) return true;

        return false;
    }

    boolean isSafeBoard(int x, int y) {
        if (map[x][y - 1].contains(PILLAR)) return true;
        if (map[x + 1][y - 1].contains(PILLAR)) return true;
        if (x > 0 && map[x - 1][y].contains(BOARD) && map[x + 1][y].contains(BOARD)) return true;

        return false;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] build_frame = {{2, 0, 0, 1}, {100, 0, 0, 1}, {100, 1, 1, 1}, {99, 1, 1, 1}, {99, 1, 0, 1}, {99, 0, 0, 1}};
        int[][] result = s.solution(100, build_frame);

        for (int[] line : result) {
            System.out.println(Arrays.toString(line));
        }
    }
}
