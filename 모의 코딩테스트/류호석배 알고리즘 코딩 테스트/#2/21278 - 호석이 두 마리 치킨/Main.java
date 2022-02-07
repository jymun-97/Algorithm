import java.util.*;
import java.io.*;
public class Main {
    static int n, m, ans1, ans2, sum = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static int[] dist;
    static boolean[] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                dist = new int[n + 1];
                bfs(i);
                bfs(j);
                
                dist[i] = dist[j] = 0;
                updateAns(i, j);
            }
        }
        System.out.println(ans1 + " " + ans2 + " " + sum * 2);
    }

    static void bfs(int start) {
        visit = new boolean[n + 1];
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (!visit[to]) {
                    que.add(to);
                    dist[to] = (dist[to] == 0) ? dist[from] + 1 : Math.min(dist[to], dist[from] + 1);
                    visit[to] = true;
                }
            }
        }
    }

    static void updateAns(int a, int b) {
        int cand = 0;
        for (int i = 1; i <= n; i++) {
            cand += dist[i];
        }
        if (cand < sum) {
            ans1 = a;
            ans2 = b;
            sum = cand;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}