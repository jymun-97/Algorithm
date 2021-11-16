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

    static HashSet<Edge>[] graph;
    static int[][] dist;
    static int n, e, v1, v2;
    static final int MAX = 200_000_000;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist = new int[n + 1][n + 1];
        graph = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
            for (int j = 1; j <= n; j++) dist[i][j] = Integer.MAX_VALUE;
        }

        while (e --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        dijkstra(1);
        dijkstra(v1);
        dijkstra(v2);

        int cand1 = dist[1][v1] + dist[v1][v2] + dist[v2][n];
        int cand2 = dist[1][v2] + dist[v2][v1] + dist[v1][n];
        int answer = Math.min(cand1, cand2);
        System.out.println(answer > MAX ? -1 : answer);
    }

    static void dijkstra(int start) {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(start, 0));
        dist[start][start] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();
            
            if (dist[start][info.idx] < info.dist) continue;

            for (Edge e : graph[info.idx]) {
                if (dist[start][e.to] > dist[start][info.idx] + e.weight) {
                    dist[start][e.to] = dist[start][info.idx] + e.weight;
                    que.add(new Info(e.to, dist[start][e.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}