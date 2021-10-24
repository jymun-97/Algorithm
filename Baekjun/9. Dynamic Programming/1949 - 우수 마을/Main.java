import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static int[] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        dp = new int[n + 1][2];
        a = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) 
            a[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        dfs(1);
        System.out.println(Integer.max(dp[1][0], dp[1][1]));

        for (int i = 1; i <= n; i++) {
            System.out.println("dp[" + i + "][0]: " + dp[i][0]);
            System.out.println("dp[" + i + "][1]: " + dp[i][1]);
            System.out.println();
        }
    }

    static void dfs(int from) {
        if (graph[from].size() == 0) {
            dp[from][0] = 0;
            dp[from][1] = a[from];
        }
        boolean flag = false;
        for (int to : graph[from]) {
            graph[to].remove(graph[to].indexOf(from));

            dfs(to);
            dp[from][0] += Integer.max(dp[to][0], dp[to][1]);
            dp[from][1] += dp[to][0];
            if (!flag) dp[from][1] += a[from];
            flag = true;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}