import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, count;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visit = new boolean[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        int start = 1;
        dfs(start);

        System.out.println(count - 1);
    }

    static void dfs(int from) {
        visit[from] = true;
        count++;

        for (int to : graph[from]) {
            if (!visit[to]) {
                dfs(to);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
