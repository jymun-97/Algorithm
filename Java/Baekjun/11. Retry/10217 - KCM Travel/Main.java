import java.util.*;
import java.io.*;

class Info {
    int idx, dist, cost;
    public Info(int i, int d, int c) {
        idx = i;
        dist = d;
        cost = c;
    }
}
class Edge {
    int to, weight, cost;
    public Edge(int t, int w, int c) {
        to = t;
        weight = w;
        cost = c;
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Edge>[] graph;
    static int[][] dp;
    static int n, m;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            if (c > m) continue;
            graph[from].add(new Edge(to, weight, c));
        }
    }

    static void solve() {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        dp[1][0] = 0;
        que.add(new Info(1, 0, 0));

        while (!que.isEmpty()) {
            Info info = que.poll();
            if (info.idx == n) {
                sb.append(info.dist).append('\n');
                return;
            }

            for (Edge e : graph[info.idx]) {
                int cost = e.cost + info.cost;
                if (cost > m) continue;

                if (dp[e.to][cost] > e.weight + info.dist) {
                    dp[e.to][cost] = e.weight + info.dist;
                    que.add(new Info(e.to, dp[e.to][cost], cost));
                }    
            }
        }

        sb.append("Poor KCM").append('\n');
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            input();
            solve();
        }
        System.out.println(sb.toString());
    }
}