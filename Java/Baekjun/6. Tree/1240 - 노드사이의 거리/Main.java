import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] graph;
    static int[] arrFrom, arrTo;
    static int[] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][n + 1];
        dist = new int[n + 1];
        arrFrom = new int[m];
        arrTo = new int[m];

        for (int i = 0; i < n - 1; i++) {
            st  = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from][to] = dist;
            graph[to][from] = dist;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arrFrom[i] = Integer.parseInt(st.nextToken());
            arrTo[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            bfs(arrFrom[i]);
            sb.append(dist[arrTo[i]]).append('\n');
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        for (int i = 1; i <= n; i++) dist[i] = -1;
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        dist[start] = 0;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to = 1; to <= n; to++) {
                if (graph[from][to] > 0 && dist[to] == -1) {
                    que.add(to);
                    dist[to] = graph[from][to] + dist[from];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
