import java.util.*;
import java.io.*;

public class Main {
    static int n, m, s, e, money;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[][] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        visit = new boolean[n + 1];
        a = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
            a[from][to] = a[to][from] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        if (n == 1) {
            System.out.println(-1);
            return;
        }

        visit[s] = true;
        dfs(s, money, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void dfs(int from, int remain, int max) {
        if (from == e) {
            ans = Integer.min(ans, max);
            return;
        }

        for (int to : graph[from]) {
            if (!visit[to] && a[from][to] <= remain) {
                visit[to] = true;
                dfs(to, remain - a[from][to], Integer.max(max, a[from][to]));
                visit[to] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}