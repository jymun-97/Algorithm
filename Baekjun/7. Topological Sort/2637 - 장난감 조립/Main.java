import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] counts;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        counts = new int[n];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            while (k --> 0) graph[from].add(to);
        }
    }

    static void solve() {
        dfs(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            if (counts[i] > 0) {
                sb.append(i).append(' ').append(counts[i]).append('\n');
            }
        }
        System.out.println(sb);
    }

    static void dfs(int from) {
        if (graph[from].size() == 0) {
            counts[from]++;
            return;
        }

        for (int to : graph[from]) {
            dfs(to);
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
