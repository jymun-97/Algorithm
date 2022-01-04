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
    int to, weight;
    public Edge(int t, int w) {
        to = t;
        weight = w;
    }
}

public class Main {
    static int n, m, c, s, e;
    static int[] dist;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
    }

    static boolean dijkstra(int limit) {
        for (int i = 1; i <= n; i++) dist[i] = Integer.MAX_VALUE;
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(s, 0));
        dist[s] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();
            if (dist[info.idx] < info.dist) continue;

            for (Edge e : graph[info.idx]) {
                if (e.weight <= limit && dist[e.to] > dist[info.idx] + e.weight) {
                    dist[e.to] = dist[info.idx] + e.weight;
                    que.add(new Info(e.to, dist[e.to]));
                }
            }
        }

        return dist[e] <= c;
    }

    static void solve() {
        for (int i = 1; i <= 20; i++) {
            if (dijkstra(i)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}