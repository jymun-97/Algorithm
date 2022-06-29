import java.util.*;
import java.io.*;


class Edge {
    int to, weight;
    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
class Info {
    int index, dist;
    public Info(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
}
public class Main {
    static int n, m, start, end;
    static int[] dist;
    static ArrayList<Edge>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        while (m --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(start, 0));
        dist[start] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();
            if (info.dist > dist[info.index]) continue;

            for (Edge e : graph[info.index]) {
                if (dist[e.to] <= dist[info.index] + e.weight) continue;

                dist[e.to] = dist[info.index] + e.weight;
                que.add(new Info(e.to, dist[e.to]));
            }
        }
        System.out.println(dist[end]);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}