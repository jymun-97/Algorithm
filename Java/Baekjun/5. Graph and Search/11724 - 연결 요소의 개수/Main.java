import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, count;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
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
        for (int vertex = 1; vertex <= n; vertex++) {
            if (!visit[vertex]) {
                dfs(vertex);
                count++;
            }
        }
        System.out.println(count);
    }
    
    static void dfs(int from) {
        visit[from] = true;
        
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
