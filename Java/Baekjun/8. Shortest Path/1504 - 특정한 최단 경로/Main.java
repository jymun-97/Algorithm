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

    static int n, m, v1, v2, MAX = 2_00_000_000;
    static int[][] dist;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        dist = new int[3][n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[0][i] = Integer.MAX_VALUE;
            dist[1][i] = Integer.MAX_VALUE;
            dist[2][i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
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
        if (m == 0) {
            System.out.println(-1);
            return;
        }
        dijkstra(1);
        dijkstra(v1);
        dijkstra(v2);

        int cand1 = dist[0][v1] + dist[1][v2] + dist[2][n];
        int cand2 = dist[0][v2] + dist[2][v1] + dist[1][n];
        int ans = Math.min(cand1, cand2);
        System.out.println(ans >= MAX ? -1 : ans);
    }
    
    static void dijkstra(int start) {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        int idx = getStartId(start);
        dist[idx][start] = 0;
        que.add(new Info(start, 0));
        
        while (!que.isEmpty()) {
            Info info = que.poll();
            if (dist[idx][info.index] < info.dist) continue;

            for (Edge e : graph[info.index]) {
                if (dist[idx][e.to] > dist[idx][info.index] + e.weight) {
                    dist[idx][e.to] = dist[idx][info.index] + e.weight;
                    que.add(new Info(e.to, dist[idx][e.to]));
                }
            }
        }
    }

    static int getStartId(int idx) {
        if (idx == 1) return 0;
        if (idx == v1) return 1;
        return 2;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}