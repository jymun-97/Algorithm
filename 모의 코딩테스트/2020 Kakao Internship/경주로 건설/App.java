import java.util.*;

class Info {
    int row, col, cost, dir;
    public Info(int row, int col, int cost, int dir) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.dir = dir;
    }
}

class Solution {

    int[][] map;
    int[][][] cost;
    int n;

    public int solution(int[][] map) {
        this.map = map;
        n = map.length;

        bfs();

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) answer = Math.min(answer, cost[n-1][n-1][i]);

        return answer;
    }

    void bfs() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        cost = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        que.add(new Info(0, 0, 0, -1));
        for (int i = 0; i < 4; i++) cost[0][0][i] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();

            if (info.dir != -1 && cost[info.row][info.col][info.dir] < info.cost) continue;
            for (int i = 0; i < 4; i++) {
                int nr = info.row + dr[i];
                int nc = info.col + dc[i];
                int nd = i;

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                int cand = (info.dir == -1 || info.dir == nd) ? info.cost + 100 : info.cost + 600;
                if (cost[nr][nc][nd] > cand && map[nr][nc] != 1) {
                    cost[nr][nc][nd] = cand;
                    que.add(new Info(nr, nc, cost[nr][nc][nd], nd));
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] map = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};

        System.out.println(s.solution(map));
    }
}
