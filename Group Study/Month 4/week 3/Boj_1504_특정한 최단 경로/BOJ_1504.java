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

public class BOJ_1504 {

    static final int MAX = 200000000;
    static int n, m;
    static ArrayList<Edge>[] graph;
    static int[] targets;
    static int[][] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        targets = new int[2];
        dist = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
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

        st = new StringTokenizer(br.readLine());
        targets[0] = Integer.parseInt(st.nextToken());
        targets[1] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        dijkstra(1);
        dijkstra(targets[0]);
        dijkstra(targets[1]);

        int case_1 = dist[1][targets[0]] + dist[targets[0]][targets[1]] + dist[targets[1]][n];
        int case_2 = dist[1][targets[1]] + dist[targets[1]][targets[0]] + dist[targets[0]][n];
        int answer = Math.min(case_1, case_2);

        System.out.println(answer >= MAX ? -1 : answer);
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