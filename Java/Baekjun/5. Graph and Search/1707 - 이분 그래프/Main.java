import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int v, e;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static boolean[] colors;
    static boolean isValuable = true;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visit = new boolean[v + 1];
        colors = new boolean[v + 1];

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= e; i++) {
            st= new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }   
    }

    static void solve() {
        dfs(1, true);
        if (isValuable) sb.append("YES").append('\n');
        else sb.append("NO").append('\n');
    }

    static void dfs(int from, boolean color) {
        if (!isValuable) return;

        visit[from] = true;
        colors[from] = color;

        for (int to : graph[from]) {
            if (!visit[to]) {
                dfs(to, !color);
            }
            else {
                if (colors[to] == color) {
                    isValuable = false;
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        int k = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < k; i++) {
            isValuable = true;
            input();
            solve();
        }
        System.out.println(sb);
    }
}
