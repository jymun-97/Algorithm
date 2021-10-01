import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, sum;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        visit = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        dfs(1, 0);

        System.out.println((sum % 2 == 0) ? "No" : "Yes");
    }

    static void dfs(int from, int depth) {
        if (graph[from].size() == 0) {
            sum += depth;
            return;
        }

        visit[from] = true;
        for (int to : graph[from]) {
            if (!visit[to]) {
                visit[to] = true;
                graph[to].remove(graph[to].indexOf(from));
                dfs(to, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
