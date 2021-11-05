import java.util.*;
import java.io.*;

class Edge {
    int from, to;
    int weight;

    public Edge(int f, int t, int w) {
        from = f;
        to = t;
        weight = w;
    }
}
public class Main {
    static int n, m;
    static long total;
    static PriorityQueue<Edge> que;
    static int[] parentOf;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parentOf = new int[n + 1];
        for (int i = 1; i <= n; i++) parentOf[i] = i;
        que = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            que.add(new Edge(from, to, weight));
            total += weight;
        }
    }

    static void solve() {
        ArrayList<Edge> selected = new ArrayList<>();
        while (!que.isEmpty()) {
            Edge edge = que.poll();

            if (find(edge.from) == find(edge.to)) continue;

            union(edge.from, edge.to);
            selected.add(edge);
        }

        if (selected.size() != n - 1) {
            System.out.println(-1);
            return;
        }

        long sum = 0L;
        for (Edge e : selected) {
            sum += e.weight;
        }
        System.out.println(total - sum);
    }

    static int find(int x) {
        if (parentOf[x] == x) return x;
        return find(parentOf[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) parentOf[y] = x;
        else parentOf[x] = y;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}