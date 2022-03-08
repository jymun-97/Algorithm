import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int size, nEnemies;
    static int startR, startC;
    static int[][] targets;
    static int[][] dist;
    static int[][] dir = { {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2} };
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        nEnemies = Integer.parseInt(st.nextToken());

        targets = new int[nEnemies][2];
        dist = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                dist[i][j] = -1;
            }
        }

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());

        for (int i = 0; i < nEnemies; i++) {
            st = new StringTokenizer(br.readLine());
            targets[i][0] = Integer.parseInt(st.nextToken());
            targets[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        bfs();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < targets.length; i++) {
            sb.append(dist[targets[i][0]][targets[i][1]]).append(' ');
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(startR);
        que.add(startC);
        dist[startR][startC] = 0;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr <= 0 || nr > size || nc <= 0 || nc > size) continue;
                if (dist[nr][nc] < 0) {
                    que.add(nr);
                    que.add(nc);
                    dist[nr][nc] = dist[row][col] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
