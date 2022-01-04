import java.util.*;

class Solution {

    int[][] map;
    int r, c;

    ArrayList<Integer>[] pair;
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] board, int r, int c) {
        int answer = 0;

        map = new int[4][4];
        HashSet<Integer> exist = new HashSet<>();
        HashSet<String> set = new HashSet<>();

        pair = new ArrayList[7];
        for (int i = 0; i < 7; i++) pair[i] = new ArrayList<>();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] != 0) {
                    answer++;
                    pair[board[row][col]].add(row);
                    pair[board[row][col]].add(col);
                    exist.add(board[row][col]);
                }
            }
        }

        int size = fact(exist.size());
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<>(exist);
        while (true) {
            if (set.size() == size) break;

            for (int i = 0; i < 4; i++) {
                map[i] = board[i].clone(); 
            }
            String temp = "";
            for (int target : list) {
                temp += Integer.toString(target);
            }
            if (!set.contains(temp)) {
                set.add(temp);
                int cand = 0;
                int row = r;
                int col = c;

                for (int target : list) {
                    int[] result = remove(target, row, col);
                    cand += result[0];
                    row = result[1];
                    col = result[2];

                    map[pair[target].get(0)][pair[target].get(1)] = 0;
                    map[pair[target].get(2)][pair[target].get(3)] = 0;
                }
                min = Math.min(min, cand);
            }
            Collections.shuffle(list);
        }

        return answer + min;
    }

    int[] remove(int target, int sr, int sc) {
        int case1 = bfs(sr, sc, pair[target].get(0), pair[target].get(1)) + bfs(pair[target].get(0), pair[target].get(1), pair[target].get(2), pair[target].get(3));
        int case2 = bfs(sr, sc, pair[target].get(2), pair[target].get(3)) + bfs(pair[target].get(2), pair[target].get(3), pair[target].get(0), pair[target].get(1));
        
        if (case1 < case2) return new int[] {case1, pair[target].get(2), pair[target].get(3)};
        else return new int[] {case2, pair[target].get(0), pair[target].get(1)};
    }

    int bfs(int sr, int sc, int er, int ec) {
        int[][] dist = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        que.add(sr);
        que.add(sc);
        dist[sr][sc] = 0;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (!inRange(nr, nc)) continue;
                if (dist[nr][nc] > dist[row][col] + 1) {
                    que.add(nr);
                    que.add(nc);
                    dist[nr][nc] = dist[row][col] + 1;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = row, nc = col;

                while (inRange(nr, nc)) {
                    nr += dir[i][0];
                    nc += dir[i][1];

                    if (inRange(nr, nc) && map[nr][nc] != 0) break;
                }
                if (nr < 0) nr = 0;
                if (nr >= 4) nr = 3;
                if (nc < 0) nc = 0;
                if (nc >= 4) nc = 3;

                if (dist[nr][nc] > dist[row][col] + 1) {
                    que.add(nr);
                    que.add(nc);
                    dist[nr][nc] = dist[row][col] + 1;
                }
            }
        }
        return dist[er][ec];
    }

    boolean inRange(int row, int col) {
        return row >= 0 && row < 4 && col >= 0 && col < 4;
    }

    int fact(int n) {
        if (n == 1) return 1;
        return n * fact(n - 1);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
        int r = 1;
        int c = 0;

        System.out.println(s.solution(board, r, c));
    }
}
