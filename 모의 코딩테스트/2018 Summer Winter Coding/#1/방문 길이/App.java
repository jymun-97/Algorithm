class Solution {

    int row, col;
    public int solution(String dirs) {
        int answer = 0;

        row = col = 5;
        boolean[][][][] visit = new boolean[11][11][11][11];

        for (char dir : dirs.toCharArray()) {
            int[] newLoc = move(dir);
            int nr = newLoc[0];
            int nc = newLoc[1];

            if (row == nr && col == nc) continue;

            if (!visit[row][col][nr][nc] && !visit[nr][nc][row][col]) {
                answer++;
                visit[row][col][nr][nc] = visit[nr][nc][row][col] = true;
            }
            row = nr;
            col = nc;
        }

        return answer;
    }

    int[] move(int dir) {
        int nr = row;
        int nc = col;

        switch (dir) {
            case 'U':
            dir = 0;
            nr--;
            break;
            
            case 'D':
            dir = 1;
            nr++;
            break;
            
            case 'R':
            dir = 2;
            nc++;
            break;
            
            default:
            dir = 3;
            nc--;
            break;
        }

        if (nr < 0 || nr > 10 || nc < 0 || nc > 10) return new int[] {row, col};
        return new int[] {nr, nc};
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String dirs = "LULLLLLLU";

        System.out.println(s.solution(dirs));
    }
}
