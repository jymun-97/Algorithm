import java.util.*;
import java.io.*;

public class Main {
    static int n, r, q;
    static int[] queries, ans;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        queries = new int[q];
        ans = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 0; i < q; i++) {
            queries[i] = Integer.parseInt(br.readLine());

        }
    }

    static void solve() {
        dfs(r);
        StringBuilder sb = new StringBuilder();
        for (int root : queries) {
            sb.append(ans[root]).append('\n');
        }
        System.out.println(sb);
    }

    static int dfs(int from) {
        ans[from] = 1;
        for (int to : graph[from]) {
            graph[to].remove(graph[to].indexOf(from));
            ans[from] += dfs(to); 
        }
        return ans[from];
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}