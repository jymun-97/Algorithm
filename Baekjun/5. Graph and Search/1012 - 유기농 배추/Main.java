import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, M, N, K, count;
    static int[][] dir = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    static int[][] map;
    static boolean[][] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visit = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }
    }

    static void solve() {
        count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    count++;
                    dfs(i, j);
                }
            }
        }
        sb.append(count).append('\n');
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            
            int ny = y + dir[i][1];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            if (!visit[nx][ny] && map[nx][ny] == 1) 
                dfs(nx, ny);
        }
    }
    
    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}
