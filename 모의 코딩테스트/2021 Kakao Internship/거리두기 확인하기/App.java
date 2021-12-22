import java.util.*;
class Solution {
    int[][] map, info;
    int[][][] dist;
    int n;

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;
        for (String[] place : places) {
            map = new int[5][5];
            n = 0;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    char c = place[row].charAt(col);
                    if (c == 'P') {
                        n++;
                        map[row][col] = n;
                    }
                    else if (c == 'X') map[row][col] = -1;
                    else map[row][col] = 0;
                }
            }
            init();
            for (int i = 1; i <= n; i++) {
                int sr = info[i][0];
                int sc = info[i][1];

                bfs(i, sr, sc);
            }
            if (isPossible()) answer[idx++] = 1;
            else answer[idx++] = 0;
        }

        return answer;
    }

    boolean isPossible() {
        for (int from = 1; from < n; from++) {
            for (int to = from + 1; to <= n; to++) {
                if (dist[from][info[to][0]][info[to][1]] <= 2) 
                    return false;
            }
        }
        return true;
    }

    void init() {
        info = new int[n + 1][2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] > 0) {
                    int target = map[i][j];
                    info[target][0] = i;
                    info[target][1] = j;
                }
            }
        }
        dist = new int[n + 1][5][5];
        for (int k = 1; k <= n; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dist[k][i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    void bfs(int start, int sr, int sc) {
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<Integer> que = new LinkedList<>();
        que.add(sr);
        que.add(sc);
        dist[start][info[start][0]][info[start][1]] = 0;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                
                if (dist[start][nr][nc] == Integer.MAX_VALUE && map[nr][nc] != -1) {
                    dist[start][nr][nc] = dist[start][row][col] + 1;  
                    que.add(nr);
                    que.add(nc);
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        System.out.println(Arrays.toString(s.solution(places)));
    }
}
