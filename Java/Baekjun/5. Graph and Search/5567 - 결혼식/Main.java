import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, count;
    static ArrayList<Integer>[] graph;
    static int[] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        dist = new int[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        bfs();
        System.out.println(count);
    }

    static void bfs() {
        int start = 1;
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        dist[start] = 0;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (dist[to] < 0) {
                    que.add(to);
                    dist[to] = dist[from] + 1;

                    if (dist[to] <= 2) count++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
