import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans;
    static ArrayList<Integer>[] graph;


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        int min = Integer.MAX_VALUE;

        for (int base = 1; base <= n; base++) {
            int[] dist = new int[n + 1];
            for (int i = 1; i <= n; i++) dist[i] = -1;

            Queue<Integer> que = new LinkedList<>();
            que.add(base);
            int sum = dist[base] = 0;
            
            while (!que.isEmpty()) {
                int from = que.poll();

                for (int to : graph[from]) {
                    if (dist[to] < 0) {
                        que.add(to);
                        dist[to] = dist[from] + 1;
                        sum += dist[to];
                    }
                }
            }
            if (min > sum) {
                ans = base;
                min = sum;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
