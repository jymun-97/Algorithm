import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static boolean[] visit;
    static int[][] graph;
    static int[][] answer;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n];
        graph = new int[n][n];
        answer = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs(int start, int target) {
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        // visit[start] = true;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to = 0; to < n; to++) {
                if (!visit[to] && graph[from][to] == 1) {
                    if (to == target) {
                        answer[start][to] = 1;
                        return;
                    }
                    que.add(to);
                    visit[to] = true;
                }
            }
        }
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visit = new boolean[n];
                bfs(i, j);
                sb.append(answer[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
