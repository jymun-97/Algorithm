import java.util.*;
import java.io.*;

class Info {
    int idx, dist;
    public Info(int i, int d) {
        idx = i;
        dist = d;
    }
}
class Edge {
    int to, cost, dist;
    public Edge(int t, int c, int d) {
        to = t;
        cost = c;
        dist = d;
    }
}
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m, k;
    static int[] dist, cost;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, c, d));
        }
    }

    static void solve() {
        dijkstra();
        sb.append(dist[n] == Integer.MAX_VALUE ? "Poor KCM" : dist[n]).append('\n');
    }

    static void dijkstra() {
        cost = new int[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            cost[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(1, 0));
        dist[1] = 0;
        cost[1] = 0;
        
        while (!que.isEmpty()) {
            Info info = que.poll();
            if (dist[info.idx] < info.dist) continue;

            for (Edge e : graph[info.idx]) {
                if (e.cost + cost[info.idx] <= k) {
                    dist[e.to] = dist[info.idx] + e.dist;
                    cost[e.to] = cost[info.idx] + e.cost;

                    que.add(new Info(e.to, dist[e.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}