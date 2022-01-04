import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[] parentOf;


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n + 1];
        parentOf = new int[n + 1];

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(parentOf[i]).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int from) {
        visit[from] = true;

        for (int to : graph[from]) {
            if (!visit[to]) {
                parentOf[to] = from;
                dfs(to);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
