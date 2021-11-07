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
    static int n, m, k;
    static int[][] dist;
    static int[] a;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
        }

        k = Integer.parseInt(br.readLine());
        a = new int[k + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= k; i++) a[i] = Integer.parseInt(st.nextToken());

    }

    static void solve() {
        for (int i = 1; i <= n; i++) dijkstra(i);

        int min = Integer.MAX_VALUE;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean flag = false;
            int cand = Integer.MIN_VALUE;
            for (int loc : a) {
                if (dist[loc][i] == Integer.MAX_VALUE || dist[i][loc] == Integer.MAX_VALUE) flag = true;
                if (dist[loc][i] + dist[i][loc] > cand) 
                    cand = dist[loc][i] + dist[i][loc];
            }
            if (flag) continue;

            if (cand < min) {
                ans = new ArrayList<>();
                ans.add(i);
                min = cand;
            }
            else if (cand == min) ans.add(i);
            else continue;
        }

        StringBuilder sb = new StringBuilder();
        for (int answer : ans) sb.append(answer).append(' ');
        System.out.println(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(start, 0));
        dist[start][start] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();

            if (dist[start][info.idx] < info.dist) continue;

            for (Edge e : graph[info.idx]) {
                if (dist[start][e.to] >= dist[start][info.idx] + e.weight) {
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