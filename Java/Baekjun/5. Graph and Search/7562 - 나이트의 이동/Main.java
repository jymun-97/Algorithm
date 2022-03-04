import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int size, startRow, startCol, destRow, destCol;
    static int[][] dist;
    static int[][] dir = {
        {1, 2}, {2, 1}, {2, -1}, {1, -2},
        {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
    };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        size = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        startRow = Integer.parseInt(st.nextToken());
        startCol = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        destRow = Integer.parseInt(st.nextToken());
        destCol = Integer.parseInt(st.nextToken());

        dist = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = -1;
            }
        }
    }

    static void solve() {
        bfs();
        sb.append(dist[destRow][destCol]).append('\n');
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(startRow);
        que.add(startCol);
        dist[startRow][startCol] = 0;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
                if (dist[nr][nc] < 0) {
                    que.add(nr);
                    que.add(nc);
                    dist[nr][nc] = dist[row][col] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int nCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < nCase; i++) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}
