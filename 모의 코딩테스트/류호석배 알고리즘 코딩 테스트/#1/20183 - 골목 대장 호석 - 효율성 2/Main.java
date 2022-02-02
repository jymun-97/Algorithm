import java.util.*;
import java.io.*;

class Info implements Comparable<Info> {
    int idx;
    long dist;
    public Info(int i, long d) {
        idx = i;
        dist = d;
    }
    @Override
    public int compareTo(Info other) {
        if (dist < other.dist) return -1;
        else if (dist == other.dist) return 0;
        else return 1;
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
    static int n, m, s, e;
    static long c;
    static long[] dist;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());

        dist = new long[n + 1];
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

    static boolean dijkstra(long limit) {
        for (int i = 1; i <= n; i++) dist[i] = Long.MAX_VALUE;
        PriorityQueue<Info> que = new PriorityQueue<>();
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
        long left = 1, right = 1000000000;
        long ans = -1;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (!dijkstra(mid)) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}