import java.util.*;
import java.io.*;

public class BOJ_2176 {

    static class Info {
        int idx, dist;
        public Info(int i, int d) {
            idx = i;
            dist = d;
        }
    }
    static class Edge {
        int to, weight;
        public Edge(int t, int w) {
            to = t;
            weight = w;
        }
    }
    static int n, m;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    static int[] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        dp = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
    }

    static void solve() {
        dijkstra();

        // System.out.println(Arrays.toString(dist));
        // System.out.println(Arrays.toString(dp));
        System.out.println(dp[1]);
    }

    static void dijkstra() {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(2, 0));
        dist[2] = 0;
        dp[2] = 1;

        while (!que.isEmpty()) {
            Info info = que.poll();
            int from = info.idx;

            if (dist[from] < info.dist) continue;

            for (Edge e : graph[info.idx]) {
                if (dist[e.to] > dist[from] + e.weight) {
                    dist[e.to] = dist[from] + e.weight;
                    que.add(new Info(e.to, dist[e.to]));
                }
                if (dist[e.to] > dist[from]) dp[e.to] += dp[from];
            }
        }

    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}