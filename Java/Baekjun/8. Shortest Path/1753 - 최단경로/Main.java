import java.util.*;
import java.io.*;

class Info {
    int index, dist;
    public Info(int i, int d) {
        index = i;
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

    static int n, m, s;
    static int[] dist;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(br.readLine());

        dist = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
        }
    }

    static void solve() {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        dist[s] = 0;
        que.add(new Info(s, 0));

        while (!que.isEmpty()) {
            Info info = que.poll();
            if (dist[info.index] < info.dist) continue;

            for (Edge e : graph[info.index]) {
                if (dist[e.to] > e.weight + dist[info.index]) {
                    dist[e.to] = e.weight + dist[info.index];
                    que.add(new Info(e.to, dist[e.to]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}