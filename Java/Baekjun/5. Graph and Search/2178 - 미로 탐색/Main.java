import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static int[][] map;
    static boolean[][] visit;
    static int[][] distance;

    static class Point {
        int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;

            visit[row][col] = true;
        }
    }
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];
        distance = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();

            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(Character.toString(line.charAt(j - 1)));
            }
        }
    }

    static void solve() {
        bfs(1, 1);

        System.out.println(distance[N][M] + 1);
    }

    static void bfs(int startRow, int startCol) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(startRow, startCol));
        
        while (!que.isEmpty()) {
            Point point = que.poll();
            int row = point.row;
            int col = point.col;

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 1 || nr > N || nc < 1 || nc > M) continue;
                if (!visit[nr][nc] && map[nr][nc] == 1) {
                    que.add(new Point(nr, nc));
                    distance[nr][nc] = distance[row][col] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
